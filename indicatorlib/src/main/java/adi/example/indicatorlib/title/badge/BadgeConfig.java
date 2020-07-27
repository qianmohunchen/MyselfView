package adi.example.indicatorlib.title.badge;

public class BadgeConfig {

    /**
     * 角标颜色
     */
    private int badgeColor;
    /**
     * 角标字体颜色
     */
    private int txtColor;
    /**
     * 角标字体大小
     */
    private float txtSize;
    /**
     * 圆形半径
     */
    private float radius;

    public int getBadgeColor() {
        return badgeColor;
    }

    public int getTxtColor() {
        return txtColor;
    }

    public float getTxtSize() {
        return txtSize;
    }

    public float getRadius() {
        return radius;
    }

    public BadgeConfig setBadgeColor(int badgeColor) {
        this.badgeColor = badgeColor;
        return this;
    }

    public BadgeConfig setTxtColor(int txtColor) {
        this.txtColor = txtColor;
        return this;
    }

    public BadgeConfig setTxtSize(float txtSize) {
        this.txtSize = txtSize;
        return this;
    }

    public BadgeConfig setRadius(float radius) {
        this.radius = radius;
        return this;
    }

    public static BadgeConfig build(){
        return new BadgeConfig();
    }

}
