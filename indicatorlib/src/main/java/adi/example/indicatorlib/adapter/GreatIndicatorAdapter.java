package adi.example.indicatorlib.adapter;

import adi.example.indicatorlib.title.AbstractTitleConfig;

public abstract class GreatIndicatorAdapter<T extends AbstractTitleConfig> extends BaseIndicatorAdapter {


    protected abstract T bindTitleConfig();

    public final T getTitleConfig() {
        return bindTitleConfig();
    }

    public abstract String getTitleName(int position);

}
