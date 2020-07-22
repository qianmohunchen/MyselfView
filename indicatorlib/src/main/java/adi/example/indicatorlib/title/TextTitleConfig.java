package adi.example.indicatorlib.title;

/**
 * 文本标题配置
 *
 * @author liangzx
 * @version 1.0
 * @time 2019-12-14 16:32
 **/
public class TextTitleConfig extends AbstractTitleConfig<TextTitleConfig> {

    private TextTitleConfig() {
    }

    public static TextTitleConfig factory() {
        return new TextTitleConfig();
    }

    /**
     * 有比较难解决的bug，暂时先不支持该功能
     * 是否使用颜色裁剪效果<br>
     * 随着滑动将选中颜色慢慢平移到目标Tab上
     *
     * @param useColorClip
     * @return
     */
    @Deprecated
    public TextTitleConfig setUseColorClip(boolean useColorClip) {
//        if (isColorGradientFollowSlide) {
//            throw new IllegalArgumentException("if u set setColorGradientFollowSlide(true),then u cann't setUseColorClip(true)");
//        }
//        isUseColorClip = useColorClip;
        return this;
    }

}
