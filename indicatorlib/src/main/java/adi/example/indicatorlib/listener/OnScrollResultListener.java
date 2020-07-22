package adi.example.indicatorlib.listener;

/**
 * 内部使用的滑动变化监听
 *
 * @author liangzx
 * @version 1.0
 * @time 2019-12-16 15:35
 **/
public interface OnScrollResultListener {
    /**
     * 正在进入某个位置
     *
     * @param index
     * @param totalCount
     * @param enterPercent
     * @param leftToRight
     */
    void onEntering(int index, int totalCount, float enterPercent, boolean leftToRight);

    /**
     * 正在离开某个位置
     *
     * @param index
     * @param totalCount
     * @param leavePercent
     * @param leftToRight
     */
    void onLeaving(int index, int totalCount, float leavePercent, boolean leftToRight);

    /**
     * 已选中
     *
     * @param index
     * @param totalCount
     */
    void onSelected(int index, int totalCount);

    /**
     * 已被移除选中
     *
     * @param index
     * @param totalCount
     */
    void onCancelSelected(int index, int totalCount);
}