package com.phearom.shop.viewmodels.product;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import java.util.Collection;

/**
 * Created by phearom on 3/16/16.
 */
public class ProductsViewModel extends BaseObservable{
    @Bindable
    public ObservableList<ProductViewModel> items;

    public ProductsViewModel(){
        items = new ObservableArrayList<>();
    }

    public void add(ProductViewModel item){
        items.add(item);
    }

    public void addAll(Collection<ProductViewModel> list){
        items.clear();
        items.addAll(list);
    }
}
