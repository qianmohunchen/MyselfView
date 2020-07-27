package adi.example.indicatorlib.adapter;

import adi.example.indicatorlib.title.AbstractTitleConfig;
import adi.example.indicatorlib.title.badge.BadgeConfig;

public abstract class GreatIndicatorAdapter<T extends AbstractTitleConfig> extends BaseIndicatorAdapter {


    protected abstract T bindTitleConfig();

    public final T getTitleConfig() {
        return bindTitleConfig();
    }

    public abstract String getTitleName(int position);


    public BadgeConfig bindBageConfig(int position) {
        return null;
    }

    public int getBadgeNumer(int position) {
        return 0;
    }
}
