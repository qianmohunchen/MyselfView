package adi.example.indicatorlib.title;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

import adi.example.indicatorlib.adapter.GreatIndicatorAdapter;
import adi.example.indicatorlib.listener.OnScrollResultListener;
import adi.example.indicatorlib.title.badge.BadgeConfig;

/**
 * 纯文字标题
 *
 * @author liangzx
 * @version 1.0
 * @time 2019-12-13 16:44
 **/
public class TextTitleView extends View implements OnScrollResultListener {

    /**
     * 配置信息
     */
    private TextTitleConfig mConfig;
    /**
     * 标题名称
     */
    private String mName;
    /**
     * 位置
     */
    private int mPosition;
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 字体最大宽度
     */
    private int mTxtMaxWidth;
    /**
     * 字体最大高度
     */
    private int mTxtMaxHeight;
    //用于Clip效果
    private boolean mLeftToRight;
    private float mClipPercent;
    /**
     * 记录选中与未选中的字体大小相差值，用于大小渐变
     */
    private int mDiffSize;
    /**
     * 是否需要监听滑动
     */
    private boolean mIsNeedListenSlide;

    private Context mContext;

    /**
     * 角标配置
     */
    private BadgeConfig mBadgeConfig;
    /**
     * 角标画笔
     */
    private Paint mBadgePaint;
    /**
     * 角标数字
     */
    private String mBadgeNumber;
    /**
     * 角标区域
     */
    private RectF mBadgeRect;
    /**
     * 角标数字
     */
    private int mBadgeNumer;


    public TextTitleView(Context context) {
        super(context);
        mContext = context;
    }

    public void init(GreatIndicatorAdapter adapter, int position) {
        mConfig = (TextTitleConfig) adapter.getTitleConfig();
        mName = adapter.getTitleName(position);
        mPosition = position;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //position=0,默认选中效果
        mPaint.setTextSize(mConfig.getNormalSize());
        mPaint.setColor(mConfig.getNormalColorByPosition(position));
        setPadding(mConfig.getPendingLeft(), 0, mConfig.getPendingRight(), 0);
        //预测量多种状态字体最大宽度
        mTxtMaxWidth = getTxtWidth();
        mTxtMaxHeight = getTxtHeight();
        //测量选中字体的大小
        mPaint.setTextSize(mConfig.getSelectedSize());
        int txtSelectedWidth = getTxtWidth();
        int txtSelectedHeight = getTxtHeight();
        if (txtSelectedWidth > mTxtMaxWidth) {
            mTxtMaxWidth = txtSelectedWidth;
        }
        if (txtSelectedHeight > mTxtMaxHeight) {
            mTxtMaxHeight = txtSelectedHeight;
        }
        //还原设置
        mPaint.setTextSize(mConfig.getNormalSize());

        mBadgePaint = new Paint();
        mBadgePaint.setAntiAlias(true);
        mBadgeConfig = adapter.bindBageConfig(position);
        mBadgeNumer = adapter.getBadgeNumer(position);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        int x = (getWidth() - getTxtWidth()) / 2;
        int y = (int) ((getHeight() - fontMetrics.bottom - fontMetrics.top) / 2);
        canvas.drawText(mName, x, y, mPaint);

        drawBadge(canvas);
        //画clip层
//        if (mConfig.isUseColorClip()) {
//            canvas.save();
//            if (mLeftToRight) {
//                canvas.clipRect(0, 0, getWidth() * mClipPercent, getHeight());
//            } else {
//                canvas.clipRect(getWidth() * (1 - mClipPercent), 0, getWidth(), getHeight());
//            }
//            mPaint.setColor(mConfig.getNormalColorByPosition(mPosition));
//            canvas.drawText(mName, x, y, mPaint);
//            canvas.restore();
//        }
    }

    private void drawBadge(Canvas canvas) {
        if (mBadgeConfig == null) {
            return;
        }
        float cx, cy;
        float radius = mBadgeConfig.getRadius();
        Paint.FontMetrics fontMetrics = mBadgePaint.getFontMetrics();
        float txtHeight = fontMetrics.descent - fontMetrics.ascent;

        cx = getWidth() - radius;
        cy = getHeight() / 2 - txtHeight / 2;
        mBadgePaint.setStyle(Paint.Style.FILL);
        mBadgePaint.setColor(mBadgeConfig.getBadgeColor());
        canvas.drawCircle(cx, cy, radius, mBadgePaint);

        //基线中间点的y轴计算公
        int baseLineY = (int) (cy - fontMetrics.top / 2 - fontMetrics.bottom / 2);
        mBadgePaint.setColor(mBadgeConfig.getTxtColor());
        mBadgePaint.setTextSize(mBadgeConfig.getTxtColor());
        mBadgePaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(String.valueOf(mBadgeNumer), cx, baseLineY, mBadgePaint);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }


    /**
     * 获取文本宽度
     *
     * @return
     */
    private int getTxtWidth() {
        return (int) mPaint.measureText(mName);
    }

    /**
     * 获取文本高度
     *
     * @return
     */
    private int getTxtHeight() {
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        return (int) (fontMetrics.bottom - fontMetrics.top);
    }

    /**
     * 获取文本最大宽度
     *
     * @return
     */
    private int getTxtMaxWidth() {
        return mTxtMaxWidth;
    }

    /**
     * 获取文本最大高度
     *
     * @return
     */
    private int getTxtMaxHeight() {
        return mTxtMaxHeight;
    }

    /**
     * 测量宽度
     *
     * @param widthMeasureSpec
     * @return
     */
    private int measureWidth(int widthMeasureSpec) {
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int result = size;

        switch (mode) {
            case MeasureSpec.AT_MOST://wrap_content
                int width = getTxtMaxWidth() + getPaddingLeft() + getPaddingRight();
                result = Math.min(width, size);
                break;
            case MeasureSpec.UNSPECIFIED://无限制，View对尺寸没有任何限制，View设置为多大就应当为多大
                result = getTxtMaxWidth() + getPaddingLeft() + getPaddingRight();
                break;
        }
        return result;
    }

    /**
     * 测量高度
     *
     * @param heightMeasureSpec
     * @return
     */
    private int measureHeight(int heightMeasureSpec) {
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int result = size;

        switch (mode) {
            case MeasureSpec.AT_MOST://wrap_content
                int height = getTxtMaxHeight() + getPaddingTop() + getPaddingBottom();
                result = Math.min(height, size);
                break;
            case MeasureSpec.UNSPECIFIED://无限制，View对尺寸没有任何限制，View设置为多大就应当为多大
                result = getTxtMaxHeight() + getPaddingTop() + getPaddingBottom();
                break;
        }
        return result;
    }

    @Override
    public void onEntering(int index, int totalCount, float enterPercent, boolean leftToRight) {

    }

    @Override
    public void onLeaving(int index, int totalCount, float leavePercent, boolean leftToRight) {

    }

    @Override
    public void onSelected(int index, int totalCount) {
        mPaint.setColor(mConfig.getSelectedColorByPosition(index));
        mPaint.setTextSize(mConfig.getSelectedSize());
        invalidate();
    }

    @Override
    public void onCancelSelected(int index, int totalCount) {
        mPaint.setColor(mConfig.getNormalColorByPosition(index));
        mPaint.setTextSize(mConfig.getNormalSize());
        invalidate();
    }
}
