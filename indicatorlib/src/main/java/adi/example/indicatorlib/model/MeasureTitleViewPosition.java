package adi.example.indicatorlib.model;

/**
 * 测量页面标题
 *
 * @author zhoudq
 * @version 1.0
 * @time 2020-7-27 18:05
 **/
public interface MeasureTitleViewPosition {
    /**
     * 返回内容区域左坐标
     *
     * @return
     */
    int getContentLeft();

    /**
     * 返回内容区域右坐标
     *
     * @return
     */
    int getContentRight();

    /**
     * 返回内容区域上坐标
     *
     * @return
     */
    int getContentTop();

    /**
     * 返回内容区域下坐标
     *
     * @return
     */
    int getContentBottom();

}