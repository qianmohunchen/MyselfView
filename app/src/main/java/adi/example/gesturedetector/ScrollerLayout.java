package adi.example.gesturedetector;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

public class ScrollerLayout extends ViewGroup {

    Scroller scroller;
    /**
     * 最小滑动距离
     */
    int scaledPagingTouchSlop;
    /**
     * 左右边界
     */
    int leftBorder, rightBorder;
    /**
     * 对屏幕操作的位置记录 主要为了获取移动的偏移量
     */
    float mDownX, mMoveX, mLastMoveX;

    public ScrollerLayout(Context context) {
        super(context);
        init(context);
    }

    public ScrollerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ScrollerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        scroller = new Scroller(context);
        scaledPagingTouchSlop = ViewConfiguration.get(context).getScaledPagingTouchSlop();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            childView.layout(i * childView.getMeasuredWidth(), 0, (i + 1) * childView.getMeasuredWidth()
                    , childView.getMeasuredHeight());
        }
        leftBorder = getChildAt(0).getLeft();
        rightBorder = getChildAt(childCount - 1).getRight();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = ev.getRawX();
                mLastMoveX = mDownX;
                break;
            case MotionEvent.ACTION_MOVE:
                mMoveX = ev.getRawX();
                mLastMoveX = mMoveX;
                int diff = (int) Math.abs(mMoveX - mDownX);
                //如果偏移量大于最小的移动值 就拦截触摸事件
                if (diff > scaledPagingTouchSlop) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    int scrollX=0;
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                //让页面平滑滚动
                mMoveX = ev.getRawX();
                 scrollX = (int) (mLastMoveX - mMoveX);
                Log.d("scroller22", "getScrollX=" + getScrollX() + " scrollX=" + scrollX + " rightBorder=" + rightBorder);
                if (getScrollX() + scrollX < leftBorder) {
                    scrollTo(leftBorder, 0);
                    return true;
                }
                if (getScrollX() + scrollX + getWidth() > rightBorder) {
                    scrollTo(rightBorder - getWidth(), 0);
                    return true;
                }
                scrollBy(scrollX, 0);
                mLastMoveX = mMoveX;
                break;
            case MotionEvent.ACTION_UP:
                // 当手指抬起时，根据当前的滚动值来判定应该滚动到哪个子控件的界面
                int targetIndex;
                if (scrollX >= 0) {
                    targetIndex = (getScrollX() + getWidth() * 3 / 4) / getWidth();
                } else {
                    targetIndex = (getScrollX() + getWidth() * 1 / 4) / getWidth();
                }
                int dx = targetIndex * getWidth() - getScrollX();
                // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
                scroller.startScroll(getScrollX(), 0, dx, 0);
                Log.d("scroller", "getScrollX=" + getScrollX() + " dx=" + dx + " targetIndex=" + targetIndex
                        +" scrollX="+scrollX);
                invalidate();
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        // 第三步，重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
        if (scroller.computeScrollOffset()) {
            Log.d("scroller44", "getCurrX=" + scroller.getCurrX() + " getCurrY=" + scroller.getCurrY());
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate();
        }
    }
}
