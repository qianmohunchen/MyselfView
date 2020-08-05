package adi.example.indicatorlib.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

import java.util.List;

import adi.example.indicatorlib.GlobalConfig;
import adi.example.indicatorlib.indicator.config.LineIndicatorConfig;
import adi.example.indicatorlib.listener.OnScrollResultListener;
import adi.example.indicatorlib.model.IndicatorShapeType;
import adi.example.indicatorlib.model.IndicatorWidthModel;
import adi.example.indicatorlib.model.PositionInfo;
import adi.example.indicatorlib.util.CalculationHelper;

/**
 * 线性指示器
 *
 * @author liangzx
 * @version 1.0
 * @time 2019-12-17 12:43
 **/
public class LineIndicatorView extends View implements OnScrollResultListener {

    /**
     * 指示器配置
     */
    private LineIndicatorConfig mIndicatorConfig;
    /**
     * 绘制区域
     */
    private RectF mLineRect = new RectF();
    /**
     * 标题位置信息列表
     */
    private List<PositionInfo> mPositions;
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     *
     */
    private GlobalConfig mGlobalConfig;

    /**
     * 贝塞尔曲线绘制路径
     */
    private Path mBezierPath;
    //贝塞尔曲线所用变量
    private float mLeftCircleRadius;
    private float mLeftCircleX;
    private float mRightCircleRadius;
    private float mRightCircleX;
    private int mMaxCircleRadius;
    private int mMinCircleRadius;

    public LineIndicatorView(Context context) {
        super(context);
    }

    public void init(GlobalConfig globalConfig, LineIndicatorConfig config, int totalCount, int position) {
        mGlobalConfig = globalConfig;
        mIndicatorConfig = config;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(config.getColor(position));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mIndicatorConfig.getShapeType() == IndicatorShapeType.ROUND_RECT) {
            canvas.drawRoundRect(mLineRect, mIndicatorConfig.getRoundRadius(), mIndicatorConfig.getRoundRadius(), mPaint);
        }
        if (mIndicatorConfig.getShapeType() == IndicatorShapeType.CIRCLE) {
            if (mIndicatorConfig.isUseBezierEffect() && null != mBezierPath) {
                float y = mLineRect.top + mLineRect.height() / 2;
                canvas.drawCircle(mLeftCircleX, y, mLeftCircleRadius, mPaint);
                canvas.drawCircle(mRightCircleX, y, mRightCircleRadius, mPaint);
                canvas.drawPath(mBezierPath, mPaint);
            } else {
                canvas.drawCircle(mLineRect.left + mLineRect.width() / 2, mLineRect.bottom - mLineRect.height(), mIndicatorConfig.getHeight(), mPaint);
            }
        }
    }


    /**
     * 更新绘制区域
     *
     * @param position
     */
    private void updateRect(int position) {
        mPaint.setColor(mIndicatorConfig.getColor(position));
        if (null == mPositions || mPositions.isEmpty()) {
            return;
        }
        final PositionInfo positionInfo;
        if (position >= mPositions.size()) {
            positionInfo = mPositions.get(mPositions.size() - 1);
        } else if (position < 0) {
            positionInfo = mPositions.get(0);
        } else {
            positionInfo = mPositions.get(position);
        }

        //线上下坐标
        if (mIndicatorConfig.isTop()) {
            mLineRect.top = positionInfo.top + mIndicatorConfig.getPendingTop();
            mLineRect.bottom = mLineRect.top + mIndicatorConfig.getHeight();
        } else {
            mLineRect.bottom = positionInfo.bottom - mIndicatorConfig.getPendingBottom();
            mLineRect.top = mLineRect.bottom - mIndicatorConfig.getHeight();
        }

        if (mIndicatorConfig.getWidthModel() == IndicatorWidthModel.MATCH_EDGE) {
            mLineRect.left = positionInfo.left + mIndicatorConfig.getPendingLeft();
            mLineRect.right = positionInfo.right - mIndicatorConfig.getPendingRight();
        } else if (mIndicatorConfig.getWidthModel() == IndicatorWidthModel.WARP_CONTENT) {
            mLineRect.left = positionInfo.contentLeft + mIndicatorConfig.getPendingLeft();
            mLineRect.right = positionInfo.contentRight - mIndicatorConfig.getPendingRight();
        } else {
            mLineRect.left = positionInfo.left + ((positionInfo.getWidth() - mIndicatorConfig.getWidth()) >> 1);
            mLineRect.right = mLineRect.left + mIndicatorConfig.getWidth();
        }

    }


    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // 计算颜色
        if (mIndicatorConfig.isMultiColors()) {
            int color = mIndicatorConfig.getColor(position);
//            if (mIndicatorConfig.isColorFollowSlideChange()) {
//                final ArrayList<Integer> colors = mIndicatorConfig.getColors();
//                int currentColor = colors.get(Math.abs(position) % colors.size());
//                int nextColor = colors.get(Math.abs(position + 1) % colors.size());
//                color = ArgbEvaluatorHolder.eval(positionOffset, currentColor, nextColor);
//            } else {
//                color = mIndicatorConfig.getColor(position);
//            }
            mPaint.setColor(color);
        }

        // 计算锚点位置
        PositionInfo current = CalculationHelper.getImitativePosition(mPositions, position);
        PositionInfo next = CalculationHelper.getImitativePosition(mPositions, position + 1);

        if (mIndicatorConfig.isTop()) {
            mLineRect.top = current.top + mIndicatorConfig.getPendingTop();
            mLineRect.bottom = mLineRect.top + mIndicatorConfig.getHeight();
        } else {
            mLineRect.bottom = current.bottom - mIndicatorConfig.getPendingBottom();
            mLineRect.top = mLineRect.bottom - mIndicatorConfig.getHeight();
        }
        float leftX;
        float nextLeftX;
        float rightX;
        float nextRightX;

        leftX = current.left + mIndicatorConfig.getPendingLeft();
        nextLeftX = next.left + mIndicatorConfig.getPendingLeft();
        rightX = current.right - mIndicatorConfig.getPendingRight();
        nextRightX = next.right - mIndicatorConfig.getPendingRight();
        mLineRect.left = leftX + (nextLeftX - leftX) * positionOffset;
        mLineRect.right = rightX + (nextRightX - rightX) * positionOffset;

        if (mIndicatorConfig.isUseBezierEffect()) {
            mBezierPath.reset();
            mMaxCircleRadius = getCircleDiameter() / 2;
            mMinCircleRadius = (int) (mMaxCircleRadius * 0.8);
            leftX = current.left + ((current.right - current.left) >> 1);
            rightX = next.left + ((next.right - next.left) >> 1);
            mLeftCircleX = leftX + (rightX - leftX) * mIndicatorConfig.getStartInterpolator().getInterpolation(positionOffset);
            mRightCircleX = leftX + (rightX - leftX) * mIndicatorConfig.getEndInterpolator().getInterpolation(positionOffset);
            mLeftCircleRadius = mMaxCircleRadius + (mMinCircleRadius - mMaxCircleRadius) * mIndicatorConfig.getEndInterpolator().getInterpolation(positionOffset);
            mRightCircleRadius = mMinCircleRadius + (mMaxCircleRadius - mMinCircleRadius) * mIndicatorConfig.getStartInterpolator().getInterpolation(positionOffset);

            float y = mLineRect.top + mLineRect.height() / 2;
//            mBezierPath.moveTo(mRightCircleX, y);
            mBezierPath.moveTo(mRightCircleX, y - mRightCircleRadius);
            mBezierPath.quadTo(mRightCircleX + (mLeftCircleX - mRightCircleX) / 2.0f, y, mLeftCircleX, y - mLeftCircleRadius);
            mBezierPath.lineTo(mLeftCircleX, y + mLeftCircleRadius);
            mBezierPath.quadTo(mRightCircleX + (mLeftCircleX - mRightCircleX) / 2.0f, y, mRightCircleX, y + mRightCircleRadius);
            mBezierPath.close();
        }

        if (mGlobalConfig.isFollowFinger()) {
            invalidate();
        }

    }

    public void onPageSelected(int position) {
        updateRect(position);
        invalidate();
    }

    /**
     * 返回圆的直径
     *
     * @return
     */
    private int getCircleDiameter() {
        int diameter;
        if (mIndicatorConfig.getWidth() == 0) {
            diameter = mIndicatorConfig.getHeight();
        } else if (mIndicatorConfig.getHeight() == 0) {
            diameter = mIndicatorConfig.getWidth();
        } else {
            diameter = Math.max(mIndicatorConfig.getHeight(), mIndicatorConfig.getWidth());
        }
        return diameter;
    }

    public void updateTitlePositions(List<PositionInfo> positions) {
        boolean isInit = null == mPositions || mPositions.isEmpty();
        mPositions = positions;
        if (isInit) {
            updateRect(0);
        }
        if (mIndicatorConfig.isUseBezierEffect()) {
            mBezierPath = new Path();
        }
    }

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
    }

    @Override
    public void onCancelSelected(int index, int totalCount) {
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (null != mPositions) {
            mPositions.clear();
        }
    }
}
