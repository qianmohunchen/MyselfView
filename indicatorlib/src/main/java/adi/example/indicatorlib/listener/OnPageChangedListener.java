package adi.example.indicatorlib.listener;

/**
 * 页面滑动变化监听
 *
 * @author liangzx
 * @version 1.0
 * @time 2019-12-16 15:35
 **/
public interface OnPageChangedListener {

    /**
     * 页面正在滑动回调
     *
     * @param position             当前页码
     * @param positionOffset       页面偏移量（百分比） 当往右滑动时递增，往左滑动时递减
     * @param positionOffsetPixels 页面偏移量（百分比） 当往右滑动时递增，往左滑动时递减。
     */
    void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

    /**
     * 选中页面回调
     *
     * @param position
     */
    void onPageSelected(int position);

    /**
     * 页面滑动状态回调
     *
     * @param state
     */
    void onPageScrollStateChanged(int state);
}
