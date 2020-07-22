package adi.example.indicatorlib.model;

/**
 * 控件坐标信息
 *
 * @author liangzx
 * @version 1.0
 * @time 2019-12-18 14:48
 **/
public class PositionInfo {
    /**
     * 整个控件左坐标
     */
    public int left;
    /**
     * 整个控件右坐标
     */
    public int right;
    /**
     * 整个控件上坐标
     */
    public int top;
    /**
     * 整个控件下坐标
     */
    public int bottom;
    /**
     * 内容区域左坐标
     */
    public int contentLeft;
    /**
     * 内容区域右坐标
     */
    public int contentRight;
    /**
     * 内容区域上坐标
     */
    public int contentTop;
    /**
     * 内容区域下坐标
     */
    public int contentBottom;

    /**
     * 返回控件宽度
     *
     * @return
     */
    public int getWidth() {
        return right - left;
    }

    /**
     * 返回控件高度
     *
     * @return
     */
    public int getHeight() {
        return top - bottom;
    }

    /**
     * 返回内容宽度
     *
     * @return
     */
    public int getContentWidth() {
        return contentRight - contentLeft;
    }

    /**
     * 返回内容高度
     *
     * @return
     */
    public int getContentHeight() {
        return contentTop - contentBottom;
    }

    /**
     * 横向中间坐标
     *
     * @return
     */
    public int horizontalCenter() {
        return left + getWidth() / 2;
    }

    /**
     * 竖向中间坐标
     *
     * @return
     */
    public int verticalCenter() {
        return top + getHeight() / 2;
    }

}
