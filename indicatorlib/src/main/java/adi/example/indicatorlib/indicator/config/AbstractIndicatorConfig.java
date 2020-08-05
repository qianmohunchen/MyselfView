package adi.example.indicatorlib.indicator.config;

import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;

import adi.example.indicatorlib.model.IndicatorShapeType;

public abstract class AbstractIndicatorConfig<T extends AbstractIndicatorConfig> {

    /**
     * 起点动画的插值器
     */
    private Interpolator startInterpolator = new LinearInterpolator();
    /**
     * 终点动画的插值器
     */
    private Interpolator endInterpolator = new LinearInterpolator();

    private int indicatorShapeType;

    public Interpolator getStartInterpolator() {
        return startInterpolator;
    }

    public void setStartInterpolator(Interpolator startInterpolator) {
        this.startInterpolator = startInterpolator;
    }

    public Interpolator getEndInterpolator() {
        return endInterpolator;
    }

    public void setEndInterpolator(Interpolator endInterpolator) {
        this.endInterpolator = endInterpolator;
    }

    /**
     * 颜色列表
     */
    private ArrayList<Integer> colors;
    /**
     * 颜色
     */
    private int color;

    protected T setShapeType(@IndicatorShapeType int type) {
        if (isSupportShape(type)) {
            indicatorShapeType = type;
        } else {
            throw new IllegalArgumentException("cant use shapetype" + type);
        }
        return (T) this;
    }

    /**
     * 是否支持的形状
     *
     * @param shapeType
     * @return
     */
    abstract boolean isSupportShape(@IndicatorShapeType int shapeType);

    /**
     * 设置多个颜色
     *
     * @param colors
     * @return
     */
    protected T setColors(ArrayList<Integer> colors) {
        this.colors = colors;
        return (T) this;
    }

    /**
     * 添加颜色到列表，支持多次添加
     *
     * @param color
     * @return
     */
    protected T addColor(int color) {
        if (null == colors) {
            colors = new ArrayList<>(5);
        }
        colors.add(color);
        return (T) this;
    }

    /**
     * 设置颜色
     *
     * @param color
     * @return
     */
    protected T setColor(int color) {
        this.color = color;
        return (T) this;
    }


    /**
     * 获取形状
     *
     * @return
     */
    public int getShapeType() {
        return indicatorShapeType;
    }

    /**
     * 获取颜色列表
     *
     * @return
     */
    protected ArrayList<Integer> getColors() {
        return colors;
    }

    /**
     * 获取颜色
     *
     * @return
     */
    protected int getColor() {
        return color;
    }

    /**
     * 获取对应位置的颜色
     *
     * @param position
     * @return
     */
    protected int getColor(int position) {
        if (isMultiColors()) {
            if (position < 0) {
                return colors.get(0);
            } else if (position >= colors.size()) {
                return colors.get(colors.size() - 1);
            } else {
                return colors.get(position);
            }
        }
        return color;
    }

    /**
     * 是否多种颜色
     *
     * @return
     */
    public boolean isMultiColors() {
        return null != colors && !colors.isEmpty();
    }
}
