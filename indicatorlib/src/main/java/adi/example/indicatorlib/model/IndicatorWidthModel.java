package adi.example.indicatorlib.model;


import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 指示器宽度模式
 *
 * @author zhoudq
 * @version 1.0
 * @time 2020-7-10 17:53
 **/
@IntDef({
        IndicatorWidthModel.MATCH_EDGE,
        IndicatorWidthModel.WARP_CONTENT,
        IndicatorWidthModel.SELF_DEFINE
})
@Retention(RetentionPolicy.SOURCE)
public @interface IndicatorWidthModel {

    /**
     * 整个item的宽度，再减去间距
     */
    int MATCH_EDGE = 0;
    /**
     * 跟随内容宽度，再减去间距
     */
    int WARP_CONTENT = 1;
    /**
     * 自定义宽度，间距设置无效
     */
    int SELF_DEFINE = 2;
}
