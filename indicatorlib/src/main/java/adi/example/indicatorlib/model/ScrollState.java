package adi.example.indicatorlib.model;

/**
 * 页面滑动状态
 *
 * @author liangzx
 * @version 1.0
 * @time 2019-12-16 15:41
 **/
public interface ScrollState {

    /**
     * 当前页已经选定
     */
    int SCROLL_STATE_IDLE = 0;
    /**
     * 当前页面正在拖动
     */
    int SCROLL_STATE_DRAGGING = 1;
    /**
     * 当前页面正在拖动中，还没有到选定状态
     */
    int SCROLL_STATE_SETTING = 2;
}
