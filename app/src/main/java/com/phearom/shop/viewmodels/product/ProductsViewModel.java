package com.phearom.shop.viewmodels.product;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.text.TextUtils;

import java.util.Collection;

/**
 * Created by phearom on 3/16/16.
 */
public class ProductsViewModel extends BaseObservable {
    @Bindable
    public ObservableList<ProductViewModel> items;
    private String groupName;

    public ProductsViewModel() {
        items = new ObservableArrayList<>();
    }

    public void add(ProductViewModel item) {
        items.add(item);
    }

    public void addAll(Collection<ProductViewModel> list) {
        items.clear();
        items.addAll(list);
    }

    public String getGroupName() {
        return TextUtils.isEmpty(groupName) ? "Group Name" : groupName;
    }

    public void setGroupName(String name) {
        this.groupName = name;
    }
}
