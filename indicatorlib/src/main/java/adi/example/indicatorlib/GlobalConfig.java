package adi.example.indicatorlib;

/**
 * 全局配置
 *
 * @author zhoudq
 * @version 1.0
 * @time 2020-7-27 16:49
 **/
public class GlobalConfig {

    /**
     * 是否可以超出屏幕
     */
    private boolean isCanOffScreen;
    /**
     * 左间距
     */
    private int pendingLeft;
    /**
     * 右间距
     */
    private int pendingRight;
    /**
     * 点击切换后，item位置到左边距离与整个可滑动区域的百分比(小数)
     */
    private float percentAfterScroll;
    /**
     * 是否跟随手指滚动
     */
    private boolean isFollowFinger;
    /**
     * 跨页时是否显示掠过
     */
    private boolean isFlit;

    private GlobalConfig() {
    }

    public static GlobalConfig factory() {
        GlobalConfig config = new GlobalConfig();
        config.setCanOffScreen(true)
                .setPercentAfterScroll(0.5F)
                .setFollowFinger(true)
                .setFlit(true);
        return config;
    }

    /**
     * 是否可以超出屏幕
     *
     * @param isCanOffScreen
     * @return
     */
    public GlobalConfig setCanOffScreen(boolean isCanOffScreen) {
        this.isCanOffScreen = isCanOffScreen;
        return this;
    }

    /**
     * 左间距
     *
     * @param pendingLeft
     * @return
     */
    public GlobalConfig setPendingLeft(int pendingLeft) {
        this.pendingLeft = pendingLeft;
        return this;
    }

    /**
     * 右间距
     *
     * @param pendingRight
     * @return
     */
    public GlobalConfig setPendingRight(int pendingRight) {
        this.pendingRight = pendingRight;
        return this;
    }

    /**
     * 点击切换后，item位置到左边距离与整个可滑动区域的百分比(小数)
     *
     * @param percentAfterScroll
     * @return
     */
    public GlobalConfig setPercentAfterScroll(float percentAfterScroll) {
        this.percentAfterScroll = percentAfterScroll;
        return this;
    }

    /**
     * 是否跟随手指滚动
     *
     * @param followFinger
     * @return
     */
    public GlobalConfig setFollowFinger(boolean followFinger) {
        isFollowFinger = followFinger;
        return this;
    }

    /**
     * 跨页时是否显示掠过
     *
     * @param flit
     * @return
     */
    public GlobalConfig setFlit(boolean flit) {
        isFlit = flit;
        return this;
    }

    public boolean isCanOffScreen() {
        return isCanOffScreen;
    }

    public int getPendingLeft() {
        return pendingLeft;
    }

    public int getPendingRight() {
        return pendingRight;
    }

    public float getPercentAfterScroll() {
        return percentAfterScroll;
    }

    public boolean isFollowFinger() {
        return isFollowFinger;
    }

    public boolean isFlit() {
        return isFlit;
    }
}
