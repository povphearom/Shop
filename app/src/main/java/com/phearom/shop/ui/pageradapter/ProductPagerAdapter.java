package com.phearom.shop.ui.pageradapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.RecyclerView;

import com.phearom.shop.ui.fragments.ProductsViewFragment;
import com.phearom.shop.viewmodels.product.ProductsViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProductPagerAdapter extends FragmentPagerAdapter {
    RecyclerView.RecycledViewPool mPool = new RecyclerView.RecycledViewPool() {
        @Override
        public RecyclerView.ViewHolder getRecycledView(int viewType) {
            RecyclerView.ViewHolder scrap = super.getRecycledView(viewType);
            return scrap;
        }

        @Override
        public void putRecycledView(RecyclerView.ViewHolder scrap) {
            super.putRecycledView(scrap);
        }

        @Override
        public String toString() {
            return "ViewPool in adapter@" + Integer.toHexString(hashCode());
        }
    };
    private List<ProductsViewModel> mItems;

    public ProductPagerAdapter(FragmentManager fm) {
        super(fm);
        mItems = new ArrayList<>();
    }

    public ProductPagerAdapter(FragmentManager fm, List<ProductsViewModel> items) {
        super(fm);
        this.mItems = items;
    }

    public void addProducts(ProductsViewModel products) {
        mItems.add(products);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int i) {
        ProductsViewFragment f = new ProductsViewFragment();
        f.mProducts = mItems.get(i);
        f.mPool = mPool;
        return f;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mItems.get(position).getGroupName();
    }
}