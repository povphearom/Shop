package com.phearom.shop.ui.activities;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.view.MenuItem;

import com.phearom.shop.R;
import com.phearom.shop.ui.ShopBaseActivity;
import com.phearom.shop.ui.fragments.CardFragment;
import com.phearom.shop.ui.fragments.MenuFragment;

public class MainActivity extends ShopBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadFragment(new MenuFragment(), "Menu");
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_card) {
            loadFragment(new CardFragment(), "Card");
        } else if (id == R.id.nav_menu) {
            loadFragment(new MenuFragment(), "Menu");
        } else if (id == R.id.nav_slideshow) {
        } else if (id == R.id.nav_manage) {
        } else if (id == R.id.nav_share) {
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
