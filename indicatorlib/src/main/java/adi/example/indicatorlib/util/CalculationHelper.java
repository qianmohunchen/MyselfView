package adi.example.indicatorlib.util;

import android.util.SparseArray;
import android.util.SparseBooleanArray;

import java.util.List;

import adi.example.indicatorlib.listener.OnScrollResultListener;
import adi.example.indicatorlib.model.PositionInfo;
import adi.example.indicatorlib.model.ScrollState;

/**
 * 页面滑动计算辅助类
 *
 * @author liangzx
 * @version 1.0
 * @time 2019-12-13 16:44
 **/
public final class CalculationHelper {
    private SparseBooleanArray mDeselectedItems = new SparseBooleanArray();
    private SparseArray<Float> mLeavedPercents = new SparseArray<>();

    private int mTotalCount;
    private int mCurrentIndex;
    private int mLastIndex;
    private float mLastPositionOffsetSum;
    private int mScrollState;
    //是否跳过
    private boolean mSkimOver;
    //滑动时间监听
    private OnScrollResultListener mOnScrollResultListener;
    //是否是第一次
    private boolean mIsFirst = true;

    private CalculationHelper(OnScrollResultListener listener) {
        mOnScrollResultListener = listener;
    }

    public static CalculationHelper factory(OnScrollResultListener listener) {
        return new CalculationHelper(listener);
    }

    /**
     * 处理页面滑动事件，分发事件
     *
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    public void handlePageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        float currentPositionOffsetSum = position + positionOffset;
        boolean leftToRight = false;
        if (mLastPositionOffsetSum <= currentPositionOffsetSum) {
            leftToRight = true;
        }
        if (mScrollState != ScrollState.SCROLL_STATE_IDLE) {
            if (currentPositionOffsetSum == mLastPositionOffsetSum) {
                return;
            }
            int nextPosition = position + 1;
            boolean normalDispatch = true;
            if (positionOffset == 0.0f) {
                if (leftToRight) {
                    nextPosition = position - 1;
                    normalDispatch = false;
                }
            }
            if (normalDispatch) {
                if (leftToRight) {
                    dispatchOnLeaving(position, positionOffset, true, false);
                    dispatchOnEntering(nextPosition, positionOffset, true, false);
                } else {
                    dispatchOnLeaving(nextPosition, 1.0f - positionOffset, false, false);
                    dispatchOnEntering(position, 1.0f - positionOffset, false, false);
                }
            } else {
                dispatchOnLeaving(nextPosition, 1.0f - positionOffset, true, false);
                dispatchOnEntering(position, 1.0f - positionOffset, true, false);
            }
        }
        mLastPositionOffsetSum = currentPositionOffsetSum;
    }

    /**
     * 处理页面选中事件
     *
     * @param position
     */
    public void handlePageSelected(int position) {
        if (mIsFirst) {
            dispatchOnSelected(position);
            mCurrentIndex = position;
            mIsFirst = false;
            return;
        }
        if (mCurrentIndex == position) {
            return;
        }
        mLastIndex = mCurrentIndex;
        int direction = 0;//0无方向，1左到右，2右到左
        if (mLastIndex < position) {
            direction = 1;
        } else if (mLastIndex > position) {
            direction = 2;
        }
        mCurrentIndex = position;
        dispatchOnSelected(mCurrentIndex);
        if (0 != direction) {
            boolean deselected = mDeselectedItems.get(mLastIndex);
            if (!deselected) {
                dispatchOnCancelSelected(mLastIndex);
            }
        }
//        mLastIndex = mCurrentIndex;
//        mCurrentIndex = position;
//        dispatchOnSelected(mCurrentIndex);
//        for (int i = 0; i < mTotalCount; i++) {
//            if (i == mCurrentIndex) {
//                continue;
//            }
//            boolean deselected = mDeselectedItems.get(i);
//            if (!deselected) {
//                dispatchOnCancelSelected(i);
//            }
//        }
    }

    /**
     * 处理页面滑动状态事件
     *
     * @param state
     */
    public void handlePageScrollStateChanged(int state) {
        mScrollState = state;
    }

    /**
     * 分发进去正在进入下一个页面的事件
     *
     * @param index
     * @param enterPercent
     * @param leftToRight
     * @param force
     */
    private void dispatchOnEntering(int index, float enterPercent, boolean leftToRight, boolean force) {
        if (mSkimOver || index == mCurrentIndex || mScrollState == ScrollState.SCROLL_STATE_DRAGGING || force) {
            if (mOnScrollResultListener != null) {
                mOnScrollResultListener.onEntering(index, mTotalCount, enterPercent, leftToRight);
            }
            mLeavedPercents.put(index, 1.0f - enterPercent);
        }
    }

    /**
     * 分发正在离开当前页面的事件
     *
     * @param index
     * @param leavePercent
     * @param leftToRight
     * @param force
     */
    private void dispatchOnLeaving(int index, float leavePercent, boolean leftToRight, boolean force) {
        if (mSkimOver || index == mLastIndex || mScrollState == ScrollState.SCROLL_STATE_DRAGGING || ((index == mCurrentIndex - 1 || index == mCurrentIndex + 1) && mLeavedPercents.get(index, 0.0f) != 1.0f) || force) {
            if (mOnScrollResultListener != null) {
                mOnScrollResultListener.onLeaving(index, mTotalCount, leavePercent, leftToRight);
            }
            mLeavedPercents.put(index, leavePercent);
        }
    }

    /**
     * 分发已选中某位置页面的事件
     *
     * @param index
     */
    private void dispatchOnSelected(int index) {
        if (mOnScrollResultListener != null) {
            mOnScrollResultListener.onSelected(index, mTotalCount);
        }
        mDeselectedItems.put(index, false);
    }

    /**
     * 分开已离开某个位置页面的事件
     *
     * @param index
     */
    private void dispatchOnCancelSelected(int index) {
        if (mOnScrollResultListener != null) {
            mOnScrollResultListener.onCancelSelected(index, mTotalCount);
        }
        mDeselectedItems.put(index, true);
    }

    /**
     * 是否跳过跨页面切换
     *
     * @param skimOver
     */
    public void setSkimOver(boolean skimOver) {
        mSkimOver = skimOver;
    }

    /**
     * 设置标题总数，并清空状态
     *
     * @param totalCount
     */
    public void setTotalCount(int totalCount) {
        mTotalCount = totalCount;
        mDeselectedItems.clear();
        mLeavedPercents.clear();
    }

    /**
     * IPagerIndicator支持弹性效果的辅助方法
     *
     * @param positionDataList
     * @param index
     * @return
     */
    public static PositionInfo getImitativePosition(List<PositionInfo> positionDataList, int index) {
        if (null == positionDataList || positionDataList.isEmpty()) {
            return null;
        }
        if (index >= 0 && index <= positionDataList.size() - 1) {
            return positionDataList.get(index);
        } else {
            PositionInfo result = new PositionInfo();
            PositionInfo referenceData;
            int offset;
            if (index < 0) {
                offset = index;
                referenceData = positionDataList.get(0);
            } else {
                offset = index - positionDataList.size() + 1;
                referenceData = positionDataList.get(positionDataList.size() - 1);
            }
            result.left = referenceData.left + offset * referenceData.getWidth();
            result.top = referenceData.top;
            result.right = referenceData.right + offset * referenceData.getWidth();
            result.bottom = referenceData.bottom;
            result.contentLeft = referenceData.contentLeft + offset * referenceData.getWidth();
            result.top = referenceData.top;
            result.right = referenceData.right + offset * referenceData.getWidth();
            result.contentBottom = referenceData.contentBottom;
            return result;
        }
    }

    /**
     * 获取总数
     *
     * @return
     */
    public int getTotalCount() {
        return mTotalCount;
    }

    /**
     * 获取当前选中的位置
     *
     * @return
     */
    public int getCurrentIndex() {
        return mCurrentIndex;
    }

    /**
     * 获取滑动状态
     *
     * @return
     */
    public int getScrollState() {
        return mScrollState;
    }

}
