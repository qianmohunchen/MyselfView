package adi.example.indicatorlib.adapter;

import android.database.DataSetObservable;
import android.database.DataSetObserver;

import androidx.annotation.NonNull;

import adi.example.indicatorlib.indicator.config.AbstractIndicatorConfig;

public abstract class BaseIndicatorAdapter<T extends AbstractIndicatorConfig> {

    private final DataSetObservable mObservable = new DataSetObservable();

    /**
     * 返回数量
     *
     * @return
     */
    public abstract int getCount();


    /**
     * Register an observer to receive callbacks related to the adapter's data changing.
     *
     * @param observer The {@link android.database.DataSetObserver} which will receive callbacks.
     */
    public void registerDataSetObserver(@NonNull DataSetObserver observer) {
        mObservable.registerObserver(observer);
    }

    /**
     * Unregister an observer from callbacks related to the adapter's data changing.
     *
     * @param observer The {@link android.database.DataSetObserver} which will be unregistered.
     */
    public void unregisterDataSetObserver(@NonNull DataSetObserver observer) {
        mObservable.unregisterObserver(observer);
    }


    /**
     * 通知数据更新
     */
    public final void notifyDataSetChanged() {
        mObservable.notifyChanged();
    }

    public T getIndicatorConfig() {

        return bindIndicatorConfig();
    }

    protected abstract T bindIndicatorConfig();

}
