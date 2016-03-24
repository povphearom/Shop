package com.phearom.shop.api.bindapi.listener;

import android.view.View;

/**
 * Created by phearom on 3/16/16.
 */
public interface LongClickHandler<T> {
    void onLongClick(T viewModel,View view);
}
