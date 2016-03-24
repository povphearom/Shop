package com.phearom.shop.api.bindapi.listener;

import android.view.View;

public interface ClickHandler<T> {
    void onClick(T viewModel, View v);
}