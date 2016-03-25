package com.phearom.shop.ui.activities;

import android.os.Bundle;

import com.phearom.shop.ui.ShopBaseActivity;
import com.phearom.shop.ui.fragments.MenuFragment;

public class MainActivity extends ShopBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFragment(new MenuFragment(), "Menu");
    }
}
