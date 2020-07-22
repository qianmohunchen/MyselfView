package adi.example.indicatorlib.indicator.config;

import android.graphics.Color;

import java.util.ArrayList;

import adi.example.indicatorlib.model.IndicatorShapeType;
import adi.example.indicatorlib.model.IndicatorWidthModel;

/**
 * 线性指示器配置
 *
 * @author zhoudq
 * @version 1.0
 * @time 2020-7-10 17:37
 **/
public class LineIndicatorConfig extends AbstractIndicatorConfig<LineIndicatorConfig> {

    /**
     * 高度
     */
    private int height;
    /**
     * 宽度模式
     */
    private @IndicatorWidthModel
    int widthModel;
    /**
     * 自定义宽度<br>
     * 仅在{@link IndicatorWidthModel#SELF_DEFINE}模式下有效
     */
    private int width;
    /**
     * 圆角矩形圆角大小
     */
    private int roundRadius;
    /**
     * 左间距
     */
    private int pendingLeft;
    /**
     * 右间距
     */
    private int pendingRight;
    /**
     * 上间距
     */
    private int pendingTop;
    /**
     * 下间距
     */
    private int pendingBottom;
    /**
     * 是否使用贝塞尔曲线效果
     */
    private boolean useBezierEffect;
    /**
     * 是否显示在顶部
     */
    private boolean isShowTop;
    /**
     * 是否需要尖角<br>
     * 设置为true后，形状、宽度模式、间距皆无效
     */
    private boolean showSharpCorner;
    /**
     * 尖角高度
     */
    private int sharpCornerHeight;
    /**
     * 尖角宽度
     */
    private int sharpCornerWidth;

    private LineIndicatorConfig() {
    }

    /**
     * 工厂方法，默认矩形及整个Item宽度、白色
     *
     * @return
     */
    public static LineIndicatorConfig factory() {
        LineIndicatorConfig config = new LineIndicatorConfig();
        config.setShapeType(IndicatorShapeType.ROUND_RECT)
                .setWidthModel(IndicatorWidthModel.MATCH_EDGE)
                .setHeight(10)
                .setRoundRadius(5)
                .setPendingTop(10)
                .setColor(Color.WHITE);
        return config;
    }

    @Override
    public LineIndicatorConfig setShapeType(int shapeType) {
        if (shapeType != IndicatorShapeType.CIRCLE && isUseBezierEffect()) {
            throw new IllegalArgumentException("If u set setUseBezierEffect(true),then u just can use IndicatorShapeType.CIRCLE");
        }
        return super.setShapeType(shapeType);
    }

    @Override
    public LineIndicatorConfig setColors(ArrayList<Integer> colors) {
        return super.setColors(colors);
    }

    @Override
    public LineIndicatorConfig addColor(int color) {
        return super.addColor(color);
    }

    @Override
    public LineIndicatorConfig setColor(int color) {
        return super.setColor(color);
    }


    /**
     * 设置高度<br>
     * 当为圆形时，宽度与高度取最大值作为直径长度
     *
     * @param height
     * @return
     */
    public LineIndicatorConfig setHeight(int height) {
        this.height = height;
        return this;
    }


    /**
     * 自定义宽度<br>
     * 当为圆形时，宽度与高度取最大值作为直径长度
     *
     * @param width
     * @return
     */
    public LineIndicatorConfig setWidth(int width) {
        this.width = width;
        return this;
    }

    /**
     * 宽度模式
     *
     * @param widthModel
     * @return
     */
    public LineIndicatorConfig setWidthModel(@IndicatorWidthModel int widthModel) {
        this.widthModel = widthModel;
        return this;
    }

    /**
     * 设置尺寸<br>
     * 当为圆形时，宽度与高度取最小值作为直径长度
     *
     * @param widthModel 宽度模式
     * @param width      仅在模式为{@link IndicatorWidthModel#SELF_DEFINE}有效
     * @param height     高度
     * @return
     */
    public LineIndicatorConfig setSize(@IndicatorWidthModel int widthModel, int width, int height) {
        this.widthModel = widthModel;
        if (widthModel == IndicatorWidthModel.SELF_DEFINE) {
            this.width = width;
        }
        this.height = height;
        return this;
    }

    /**
     * 设置圆半径
     *
     * @param radius
     * @return
     */
    public LineIndicatorConfig setRadius(int radius) {
        setShapeType(IndicatorShapeType.CIRCLE);
        this.height = radius;
        this.width = radius;
        return this;
    }

    /**
     * 圆角矩形圆角大小
     *
     * @param roundRadius
     * @return
     */
    public LineIndicatorConfig setRoundRadius(int roundRadius) {
        this.roundRadius = roundRadius;
        return this;
    }

    /**
     * 四周间距
     *
     * @param panding
     * @return
     */
    public LineIndicatorConfig setPanding(int panding) {
        this.pendingLeft = panding;
        this.pendingRight = panding;
        this.pendingTop = panding;
        this.pendingBottom = panding;
        return this;
    }

    /**
     * 左间距，对自定义长度无效
     *
     * @param pendingLeft
     * @return
     */
    public LineIndicatorConfig setPendingLeft(int pendingLeft) {
        this.pendingLeft = pendingLeft;
        return this;
    }

    /**
     * 右间距，对自定义长度无效
     *
     * @param pendingRight
     * @return
     */
    public LineIndicatorConfig setPendingRight(int pendingRight) {
        this.pendingRight = pendingRight;
        return this;
    }

    /**
     * 上间距
     *
     * @param pendingTop
     * @return
     */
    public LineIndicatorConfig setPendingTop(int pendingTop) {
        this.pendingTop = pendingTop;
        return this;
    }

    /**
     * 下间距
     *
     * @param pendingBottom
     * @return
     */
    public LineIndicatorConfig setPendingBottom(int pendingBottom) {
        this.pendingBottom = pendingBottom;
        return this;
    }


    /**
     * 是否显示在顶部
     *
     * @return
     */
    public LineIndicatorConfig setTop() {
        this.isShowTop = true;
        return this;
    }

    /**
     * 是否需要尖角<br>
     * 设置为true后，线宽度模式、线宽度、贝塞尔曲线效果皆无效
     *
     * @param showSharpCorner
     * @return
     */
    public LineIndicatorConfig setShowSharpCorner(boolean showSharpCorner) {
        if (useBezierEffect) {
            throw new IllegalArgumentException("If u set setUseBezierEffect(true),then u cann't setShowSharpCorner(true)");
        }
        this.showSharpCorner = showSharpCorner;
        return this;
    }

    /**
     * 设置尖角高度
     *
     * @param sharpCornerHeight
     * @return
     */
    public LineIndicatorConfig setSharpCornerHeight(int sharpCornerHeight) {
        this.sharpCornerHeight = sharpCornerHeight;
        return this;
    }

    /**
     * 设置尖角宽度
     *
     * @param sharpCornerWidth
     * @return
     */
    public LineIndicatorConfig setSharpCornerWidth(int sharpCornerWidth) {
        this.sharpCornerWidth = sharpCornerWidth;
        return this;
    }

    @Override
    boolean isSupportShape(int shapeType) {
        return shapeType == IndicatorShapeType.RECT
                || shapeType == IndicatorShapeType.ROUND_RECT
                || shapeType == IndicatorShapeType.CIRCLE;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getWidthModel() {
        return widthModel;
    }

    public int getRoundRadius() {
        return roundRadius;
    }

    public int getPendingLeft() {
        return pendingLeft;
    }

    public int getPendingRight() {
        return pendingRight;
    }

    public int getPendingTop() {
        return pendingTop;
    }

    public int getPendingBottom() {
        return pendingBottom;
    }

    public boolean isUseBezierEffect() {
        return useBezierEffect;
    }

    public boolean isTop() {
        return isShowTop;
    }

    public boolean isShowSharpCorner() {
        return showSharpCorner;
    }

    public int getSharpCornerHeight() {
        return sharpCornerHeight;
    }

    public int getSharpCornerWidth() {
        return sharpCornerWidth;
    }

    @Override
    public int getColor() {
        return super.getColor();
    }

    @Override
    public int getColor(int position) {
        return super.getColor(position);
    }

    @Override
    public ArrayList<Integer> getColors() {
        return super.getColors();
    }

}
