package adi.example.indicatorlib.title;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTitleConfig<T extends AbstractTitleConfig> {
    /**
     * 未选中大小
     */
    private int normalSize;
    /**
     * 选中大小
     */
    private int selectedSize;
    /**
     * 未选中颜色
     */
    private int normalColor;
    /**
     * 选中颜色
     */
    private int selectedColor;
    /**
     * 未选中颜色列表
     */
    private List<Integer> normalColors;
    /**
     * 选中颜色列表
     */
    private List<Integer> selectedColors;
    /**
     * 左间距
     */
    private int pendingLeft;
    /**
     * 右间距
     */
    private int pendingRight;

    /**
     * 未选中大小
     *
     * @param normalSize
     * @return
     */
    public T setNormalSize(int normalSize) {
        this.normalSize = normalSize;
        return (T) this;
    }

    /**
     * 选中大小
     *
     * @param selectedSize
     * @return
     */
    public T setSelectedSize(int selectedSize) {
        this.selectedSize = selectedSize;
        return (T) this;
    }

    /**
     * 未选中颜色
     *
     * @param normalColor
     * @return
     */
    public T setNormalColor(int normalColor) {
        this.normalColor = normalColor;
        return (T) this;
    }

    /**
     * 选中颜色
     *
     * @param selectedColor
     * @return
     */
    public T setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
        return (T) this;
    }

    /**
     * 未选中颜色列表
     *
     * @param normalColors
     * @return
     */
    public T setNormalColors(List<Integer> normalColors) {
        this.normalColors = normalColors;
        return (T) this;
    }

    /**
     * 添加未选中颜色到列表，支持多次添加
     *
     * @param normalColor
     * @return
     */
    public T addNormalColors(int normalColor) {
        if (null == normalColors) {
            normalColors = new ArrayList<>(5);
        }
        normalColors.add(normalColor);
        return (T) this;
    }

    /**
     * 选中颜色列表
     *
     * @param selectedColors
     * @return
     */
    public T setSelectedColors(List<Integer> selectedColors) {
        this.selectedColors = selectedColors;
        return (T) this;
    }

    /**
     * 添加选中颜色到列表，支持多次添加
     *
     * @param selectedColor
     * @return
     */
    public T addSelectedColor(int selectedColor) {
        if (null == selectedColors) {
            selectedColors = new ArrayList<>(5);
        }
        selectedColors.add(selectedColor);
        return (T) this;
    }


    /**
     * 左间距
     *
     * @param pendingLeft
     * @return
     */
    public T setPendingLeft(int pendingLeft) {
        this.pendingLeft = pendingLeft;
        return (T) this;
    }

    /**
     * 右间距
     *
     * @param pendingRight
     * @return
     */
    public T setPendingRight(int pendingRight) {
        this.pendingRight = pendingRight;
        return (T) this;
    }

    public int getNormalSize() {
        return normalSize;
    }

    public int getSelectedSize() {
        return selectedSize;
    }

    public int getNormalColor() {
        return normalColor;
    }

    public int getSelectedColor() {
        return selectedColor;
    }

    public List<Integer> getNormalColors() {
        return normalColors;
    }

    public List<Integer> getSelectedColors() {
        return selectedColors;
    }

    /**
     * 根据当前标题所属位置获取未选中的颜色值<br>
     * 该方法先判断颜色列表是否为空，如空则取单个颜色，非空则取颜色列表中对应位置的值，超出下标则取最后一个
     *
     * @param index
     * @return
     */
    public int getNormalColorByPosition(int index) {
        if (null == normalColors || normalColors.isEmpty()) {
            return normalColor;
        }
        if (normalColors.size() <= index) {
            return normalColors.get(normalColors.size() - 1);
        }
        return normalColors.get(index);
    }

    /**
     * 根据当前标题所属位置获取选中的颜色值<br>
     * 该方法先判断颜色列表是否为空，如空则取单个颜色，非空则取颜色列表中对应位置的值，超出下标则取最后一个
     *
     * @param index
     * @return
     */
    public int getSelectedColorByPosition(int index) {
        if (null == selectedColors || selectedColors.isEmpty()) {
            return selectedColor;
        }
        if (selectedColors.size() <= index) {
            return selectedColors.get(selectedColors.size() - 1);
        }
        return selectedColors.get(index);
    }


    public int getPendingLeft() {
        return pendingLeft;
    }

    public int getPendingRight() {
        return pendingRight;
    }
}
