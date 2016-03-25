package com.phearom.shop.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.phearom.shop.models.Menu;

/**
 * Created by phearom on 3/25/16.
 */
public class MenusViewModel extends BaseObservable {
    @Bindable
    public final ObservableList<MenuViewModel> items;

    public MenusViewModel() {
        items = new ObservableArrayList<>();
    }

    public void add(Menu menu) {
        if (!items.contains(new MenuViewModel(menu)))
            items.add(new MenuViewModel(menu));
    }

    public void add(MenuViewModel menu) {
        if (!items.contains(menu))
            items.add(menu);
    }

    public MenuViewModel getItem(int index){
        return items.get(index);
    }
}
