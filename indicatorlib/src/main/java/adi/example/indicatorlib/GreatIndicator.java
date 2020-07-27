package adi.example.indicatorlib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import adi.example.indicatorlib.adapter.BaseIndicatorAdapter;
import adi.example.indicatorlib.adapter.GreatIndicatorAdapter;
import adi.example.indicatorlib.indicator.LineIndicatorView;
import adi.example.indicatorlib.indicator.config.AbstractIndicatorConfig;
import adi.example.indicatorlib.indicator.config.LineIndicatorConfig;
import adi.example.indicatorlib.listener.OnPageChangedListener;
import adi.example.indicatorlib.listener.OnScrollResultListener;
import adi.example.indicatorlib.listener.OnTitleClickListener;
import adi.example.indicatorlib.model.PositionInfo;
import adi.example.indicatorlib.title.AbstractTitleConfig;
import adi.example.indicatorlib.title.TextTitleView;
import adi.example.indicatorlib.util.CalculationHelper;

public class GreatIndicator extends FrameLayout implements View.OnClickListener, OnPageChangedListener, OnScrollResultListener {

    /**
     * 当非自适应模式会初始化该容器，用于包裹全部控件，可横向滚动
     */
    HorizontalScrollView mScrollView;
    /**
     * 内容容器，包含标题和指示器
     */
    FrameLayout mContentContainer;
    /**
     * 标题容器
     */
    LinearLayout mTitleContainer;
    /**
     * 适配器
     */
    BaseIndicatorAdapter mAdapter;
    /**
     *
     */
    private ViewPager mViewPager;
    /**
     * 标题坐标信息列表
     */
    List<PositionInfo> mTitlePositions;
    /**
     *
     */
    CalculationHelper mCalculationHelper;
    /**
     *
     */
    LineIndicatorView mLineIndicatorView;


    /**
     * 点击事件监听
     */
    OnTitleClickListener mOnTitleClickListener = new OnTitleClickListener() {
        @Override
        public void onTitleClick(int position) {
            if (null != mViewPager) {
                mViewPager.setCurrentItem(position);
            }
        }
    };

    public GreatIndicator(@NonNull Context context) {
        super(context);
        init();
    }

    public GreatIndicator(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GreatIndicator(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        removeAllViews();
        initGlobalConfig();
        initContainter();
        initTitleViews();
        initIndicatorView();
    }

    /**
     * 初始化全局配置
     */
    private void initGlobalConfig() {
        if (mCalculationHelper == null) {
            mCalculationHelper = CalculationHelper.factory(this);
        }
    }

    public void setAdapter(BaseIndicatorAdapter baseIndicatorAdapter) {
        this.mAdapter = baseIndicatorAdapter;
        init();
    }

    /**
     * 初始化容器
     */
    private void initContainter() {
        mScrollView = new HorizontalScrollView(getContext());
        mScrollView.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        mScrollView.setFillViewport(true);
        mScrollView.setHorizontalScrollBarEnabled(false);
        addView(mScrollView);

        mContentContainer = new FrameLayout(getContext());
        mContentContainer.setLayoutParams(new HorizontalScrollView.LayoutParams(
                HorizontalScrollView.LayoutParams.WRAP_CONTENT
                , HorizontalScrollView.LayoutParams.MATCH_PARENT));
        mScrollView.addView(mContentContainer);
    }


    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        if (null != mOnTitleClickListener) {
            mOnTitleClickListener.onTitleClick(position);
        }
    }


    public void bindViewPager(ViewPager viewPager) {
        this.mViewPager = viewPager;
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                GreatIndicator.this.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                GreatIndicator.this.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                GreatIndicator.this.onPageScrollStateChanged(state);
            }
        });

    }

    /**
     * 初始化标题
     */
    private void initTitleViews() {
        mTitleContainer = new LinearLayout(getContext());
        mTitleContainer.setOrientation(LinearLayout.HORIZONTAL);
        if (mAdapter instanceof GreatIndicatorAdapter) {
            AbstractTitleConfig titleConfig = ((GreatIndicatorAdapter) mAdapter).getTitleConfig();
            mTitleContainer.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, titleConfig
                    == null ? 0 : LayoutParams.MATCH_PARENT));
            mContentContainer.addView(mTitleContainer);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                    null == titleConfig ? 0 : LayoutParams.MATCH_PARENT);
            int count = mAdapter.getCount();
            for (int i = 0; i < count; i++) {
                TextTitleView textTitleView = new TextTitleView(getContext());
                textTitleView.setLayoutParams(layoutParams);
                textTitleView.init((GreatIndicatorAdapter) mAdapter, i);
                layoutParams.gravity = Gravity.CENTER_VERTICAL;
                mTitleContainer.addView(textTitleView);
                textTitleView.setTag(i);
                textTitleView.setOnClickListener(this);

            }
        }
    }

    /**
     * 初始化指示器<br>
     * 当没有配置时则不显示指示器
     */
    private void initIndicatorView() {
        if (null == mAdapter) {
            return;
        }
        AbstractIndicatorConfig indicatorConfig = mAdapter.getIndicatorConfig();
        if (null == indicatorConfig) {
            return;
        }
        if (indicatorConfig instanceof LineIndicatorConfig) {
            mLineIndicatorView = new LineIndicatorView(getContext());
        }
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        if (indicatorConfig instanceof LineIndicatorConfig) {
            if (((LineIndicatorConfig) indicatorConfig).isTop()) {
                lp.gravity = Gravity.TOP;
            } else {
                lp.gravity = Gravity.BOTTOM;
            }
        }
        mLineIndicatorView.init((LineIndicatorConfig) indicatorConfig, mAdapter.getCount(), 0);
        mContentContainer.addView(mLineIndicatorView, lp);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (null == mAdapter) {
            return;
        }
        measureTitleViewPosition();
        if (null != mLineIndicatorView) {
            mLineIndicatorView.updateTitlePositions(mTitlePositions);
        }
    }

    private void measureTitleViewPosition() {
        if (null == mAdapter || null == mTitleContainer) {
            return;
        }
        int childCount = mTitleContainer.getChildCount();
        if (null == mTitlePositions) {
            mTitlePositions = new ArrayList<>(childCount);
        } else {
            mTitlePositions.clear();
        }
        for (int i = 0; i < childCount; i++) {
            View child = mTitleContainer.getChildAt(i);
            PositionInfo item = new PositionInfo();
            item.left = child.getLeft();
            item.right = child.getRight();
            item.top = child.getTop();
            item.bottom = child.getBottom();

            int left = child.getLeft();
            int right = child.getRight();
            int top = child.getTop();
            int bottom = child.getBottom();
            child.layout(left, top, right, bottom);
            mTitlePositions.add(item);
        }
    }

    private int mCurIndex, mNextIndex;

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        final int curIndex = Math.min(mTitlePositions.size() - 1, position);
        final int nextIndex = Math.min(mTitlePositions.size() - 1, position + 1);
        mCurIndex = curIndex;
        mNextIndex = nextIndex;
        final PositionInfo curPosition = mTitlePositions.get(curIndex);
        final PositionInfo nextPosition = mTitlePositions.get(nextIndex);
        final float offset = mScrollView.getWidth() * 0.5f;
        final float preScroll = curPosition.horizontalCenter() - offset;
        final float nextScroll = nextPosition.horizontalCenter() - offset;
        final int distance = (int) (preScroll + (nextScroll - preScroll) * positionOffset);
        mScrollView.smoothScrollTo(distance, 0);
        if (null != mLineIndicatorView) {
            mLineIndicatorView.onPageScrolled(position,positionOffset,positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        mCalculationHelper.handlePageSelected(position);
        if (null != mLineIndicatorView) {
            mLineIndicatorView.onPageSelected(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onEntering(int index, int totalCount, float enterPercent, boolean leftToRight) {

    }

    @Override
    public void onLeaving(int index, int totalCount, float leavePercent, boolean leftToRight) {

    }

    @Override
    public void onSelected(int index, int totalCount) {
        if (null != mTitleContainer) {
            int childCount = mTitleContainer.getChildCount();
            View selectedView = mTitleContainer.getChildAt(index);
            if (selectedView instanceof TextTitleView) {
                ((TextTitleView) selectedView).onSelected(index, childCount);
            }
        }
    }

    @Override
    public void onCancelSelected(int index, int totalCount) {
        if (null != mTitleContainer) {
            int childCount = mTitleContainer.getChildCount();
            View beforeView = mTitleContainer.getChildAt(index);
            if (beforeView instanceof TextTitleView) {
                ((TextTitleView) beforeView).onCancelSelected(index, childCount);
            }
        }
    }
}
