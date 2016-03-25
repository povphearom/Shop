package com.phearom.shop.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.phearom.shop.R;
import com.phearom.shop.api.utils.SessionManager;
import com.phearom.shop.databinding.ActivityBaseBinding;
import com.phearom.shop.ui.activities.LoginActivity;
import com.phearom.shop.ui.fragments.MenuFragment;

/**
 * Created by phearom on 3/25/16.
 */
public abstract class ShopBaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ActivityBaseBinding mBinding;
    private Toolbar mToolbar;
    private FloatingActionButton mFab;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavView;

    private FragmentTransaction mTransaction;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mToolbar = null;
        mFab = null;
        mDrawerLayout = null;
        mToggle = null;
        mTransaction = null;
        mBinding.unbind();
        mBinding = null;
    }

    public void setFragment(Fragment fragment, String title) {
        setTitle(title);
        mTransaction = getSupportFragmentManager().beginTransaction();
        mTransaction.replace(R.id.contentPanel, fragment);
        mTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_base);
        setSupportActionBar(mBinding.appBarLayout.toolbar);
        setUpDrawer();
    }

    private void setUpDrawer() {
        mToolbar = mBinding.appBarLayout.toolbar;
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavView = (NavigationView) findViewById(R.id.nav_view);

        mToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        mNavView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_menu) {
            setFragment(new MenuFragment(),"Menu");
        } else if (id == R.id.nav_gallery) {
        } else if (id == R.id.nav_slideshow) {
        } else if (id == R.id.nav_manage) {
        } else if (id == R.id.nav_share) {
        } else if (id == R.id.nav_logout) {
            logoutFacebook();
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logoutFacebook() {
        if (AccessToken.getCurrentAccessToken() != null) {
            LoginManager.getInstance().logOut();
            SessionManager.init(this).reset();
            startActivity(new Intent(ShopBaseActivity.this, LoginActivity.class));
        }
    }
}
