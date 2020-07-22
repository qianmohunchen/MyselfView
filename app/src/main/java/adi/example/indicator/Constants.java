package adi.example.indicator;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Random;

/**
 * description
 *
 * @author liangzx
 * @version 1.0
 * @time 2019-12-19 18:01
 **/
public final class Constants {

    public static final String[] CHANNELS = new String[]{"CUPCAKE", "DONUT", "ECLAIR", "GINGERBREAD", "HONEYCOMB", "ICE_CREAM_SANDWICH"};
    public static final String[] CHANNELS2 = new String[]{"CUPCAKE", "CUPCAKE", "CUPCAKE", "CUPCAKE", "CUPCAKE", "CUPCAKE", "CUPCAKE", "CUPCAKE", "CUPCAKE", "CUPCAKE", "CUPCAKE"};
    public static final String[] CHANNELS3 = new String[]{"CAKE", "CAKE", "CAKE", "CAKE", "CAKE", "CAKE", "CAKE", "CAKE", "CAKE", "CAKE", "CAKE"};
    public static final String[] CHANNELS4 = new String[]{"CAKE", "CAKE"};
    public static final ArrayList<Integer> indicatorColors;
    public static final ArrayList<Integer> bgColors;
    public static final ArrayList<Integer> normalTxtColors;
    public static final ArrayList<Integer> selectedTxtColors;
    public static final ArrayList<Integer> wrapColors;
    public static final int normalTxtColor = color("#F0F8FF");
    public static final int selectedTxtColor = color("#FFFFFF");

    static {
        indicatorColors = new ArrayList<>();
        normalTxtColors = new ArrayList<>();
        selectedTxtColors = new ArrayList<>();
        bgColors = new ArrayList<>();
        wrapColors = new ArrayList<>();

        indicatorColors.add(color("#FFF68F"));
        indicatorColors.add(color("#FFE4E1"));
        indicatorColors.add(color("#FF8C00"));
        indicatorColors.add(color("#FF6EB4"));
        indicatorColors.add(color("#FF4500"));
        indicatorColors.add(color("#FF00FF"));
        indicatorColors.add(color("#CD8500"));
        indicatorColors.add(color("#B0E0E6"));
        indicatorColors.add(color("#90EE90"));
        indicatorColors.add(color("#836FFF"));
        indicatorColors.add(color("#7CFC00"));
        indicatorColors.add(color("#008B00"));
        normalTxtColors.addAll(indicatorColors);

        bgColors.add(color("#DC143C"));
        bgColors.add(color("#C71585"));
        bgColors.add(color("#8B008B"));
        bgColors.add(color("#4B0082"));
        bgColors.add(color("#8A2BE2"));
        bgColors.add(color("#0000CD"));
        bgColors.add(color("#000080"));
        bgColors.add(color("#2F4F4F"));
        bgColors.add(color("#006400"));
        bgColors.add(color("#556B2F"));
        bgColors.add(color("#B8860B"));
        bgColors.add(color("#8B4513"));
        selectedTxtColors.addAll(bgColors);

        wrapColors.add(color("#96ADADAD"));
        wrapColors.add(color("#96BEBEBE"));
        wrapColors.add(color("#96d0d0d0"));
        wrapColors.add(color("#96E0E0E0"));
        wrapColors.add(color("#96F0F0F0"));
        wrapColors.add(color("#96FF9797"));
        wrapColors.add(color("#96FFB5B5"));
        wrapColors.add(color("#96FFD2D2"));
        wrapColors.add(color("#96FFECF5"));
        wrapColors.add(color("#96FFD9EC"));
        wrapColors.add(color("#96FFC1E0"));
        wrapColors.add(color("#9684C1FF"));
    }


    public static int randomBgColor() {
        int max = bgColors.size();
        int min = 0;
        int index = new Random().nextInt(max - min) + min;
        return bgColors.get(index);
    }

    public static int randomIndicatorColor() {
        int max = indicatorColors.size();
        int min = 0;
        int index = new Random().nextInt(max - min) + min;
        return indicatorColors.get(index);
    }

    public static int randomWrapIndicatorColor() {
        int max = wrapColors.size();
        int min = 0;
        int index = new Random().nextInt(max - min) + min;
        return wrapColors.get(index);
    }

    public static int color(String color) {
        return Color.parseColor(color);
    }
}
