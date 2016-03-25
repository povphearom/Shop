package com.phearom.shop.viewmodels;

import android.databinding.BaseObservable;

import com.phearom.shop.models.Menu;

/**
 * Created by phearom on 3/25/16.
 */
public class MenuViewModel extends BaseObservable {
    private final Menu menu;

    public MenuViewModel(Menu menu) {
        this.menu = menu;
    }

    public Menu getMenu() {
        return menu;
    }

    public String getName() {
        return menu.getName();
    }

    public String getUrl() {
        return menu.getUrl();
    }
}
