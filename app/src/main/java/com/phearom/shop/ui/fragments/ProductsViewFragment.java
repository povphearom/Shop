package com.phearom.shop.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phearom.shop.BR;
import com.phearom.shop.R;
import com.phearom.shop.api.bindapi.binder.CompositeItemBinder;
import com.phearom.shop.api.bindapi.binder.ItemBinder;
import com.phearom.shop.api.bindapi.listener.ClickHandler;
import com.phearom.shop.api.utils.SpacesItemDecoration;
import com.phearom.shop.binder.ProductBinder;
import com.phearom.shop.databinding.FragmentProductsViewBinding;
import com.phearom.shop.viewmodels.product.ProductViewModel;
import com.phearom.shop.viewmodels.product.ProductsViewModel;

public class ProductsViewFragment extends Fragment {
    public RecyclerView.RecycledViewPool mPool;
    public ProductsViewModel mProducts;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentProductsViewBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_products_view, container, false);

        binding.setProducts(mProducts);
        binding.setView(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setRecycleChildrenOnDetach(true);
        layoutManager.setSmoothScrollbarEnabled(true);
        binding.recyclerProduct.addItemDecoration(new SpacesItemDecoration(8));
        binding.recyclerProduct.setLayoutManager(layoutManager);
        if (mPool != null) {
            binding.recyclerProduct.setRecycledViewPool(mPool);
        }
        binding.recyclerProduct.setItemViewCacheSize(10);
        return binding.getRoot();
    }

    public ItemBinder<ProductViewModel> itemViewBinder() {
        return new CompositeItemBinder<>(new ProductBinder.Product(BR.model, R.layout.item_product));
    }

    public ClickHandler<ProductViewModel> clickHandler() {
        return new ClickHandler<ProductViewModel>() {
            @Override
            public void onClick(ProductViewModel viewModel, View v) {
                DetailProductFragment dDial = DetailProductFragment.init(viewModel.getModel());
                dDial.show(getChildFragmentManager(), "detail");
            }
        };
    }
}