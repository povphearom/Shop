package com.phearom.shop.api.bindapi.binder;

public interface ItemBinder<T> {
    int getLayoutRes(T model);

    int getBindingVariable(T model);
}