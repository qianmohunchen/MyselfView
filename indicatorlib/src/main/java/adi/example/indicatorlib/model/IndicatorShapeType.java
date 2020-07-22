package adi.example.indicatorlib.model;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
        IndicatorShapeType.RECT,
        IndicatorShapeType.CIRCLE,
        IndicatorShapeType.ROUND_RECT,
        IndicatorShapeType.TRIANGLE
})
@Retention(RetentionPolicy.SOURCE)
public @interface IndicatorShapeType {

    /**
     * 矩形
     */
    int RECT = 0;
    /**
     * 圆
     */
    int CIRCLE = 1;
    /**
     * 圆角矩形
     */
    int ROUND_RECT = 2;
    /**
     * 三角形
     */
    int TRIANGLE = 3;
}
