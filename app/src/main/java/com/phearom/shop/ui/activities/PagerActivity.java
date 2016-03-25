package com.phearom.shop.ui.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.phearom.shop.R;
import com.phearom.shop.databinding.ActivityPagerBinding;
import com.phearom.shop.models.Product;
import com.phearom.shop.ui.ShopBaseActivity;
import com.phearom.shop.ui.pageradapter.ProductPagerAdapter;
import com.phearom.shop.viewmodels.product.ProductViewModel;
import com.phearom.shop.viewmodels.product.ProductsViewModel;

public class PagerActivity extends ShopBaseActivity {

    private ActivityPagerBinding mBinding = null;
    private ProductPagerAdapter mPagerAdapter;
//    private List<ProductsViewModel> mListProducts;

    private String[] title = {"Breakfast", "Lunch", "Dinner", "Sweet", "Drink",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_pager);
        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        mListProducts = new ArrayList<>();

        mPagerAdapter = new ProductPagerAdapter(getSupportFragmentManager());

        mBinding.pager.setAdapter(mPagerAdapter);
        mBinding.tab.setupWithViewPager(mBinding.pager);

        ProductsViewModel products;
        Product product;
        for (String s : title) {
            products = new ProductsViewModel();
            products.setGroupName(s);
            for (int n = 0; n < 10; n++) {
                product = new Product();
                product.setName(s + " " + n);
                product.setDescription(s + " is the best price for this promotion.");
                product.setPrice(Math.round(Math.random() * 100));
                product.setCurrency(1);
                products.add(new ProductViewModel(product));
            }
            mPagerAdapter.addProducts(products);
        }
    }
}