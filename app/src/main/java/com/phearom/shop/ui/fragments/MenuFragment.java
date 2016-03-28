package com.phearom.shop.ui.fragments;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phearom.shop.BR;
import com.phearom.shop.R;
import com.phearom.shop.api.bindapi.binder.CompositeItemBinder;
import com.phearom.shop.api.bindapi.binder.ItemBinder;
import com.phearom.shop.api.bindapi.listener.ClickHandler;
import com.phearom.shop.binder.MenuBinder;
import com.phearom.shop.databinding.FragmentMenuBinding;
import com.phearom.shop.models.Menu;
import com.phearom.shop.ui.activities.PagerActivity;
import com.phearom.shop.viewmodels.MenuViewModel;
import com.phearom.shop.viewmodels.MenusViewModel;

/**
 * Created by phearom on 3/21/16.
 */
public class MenuFragment extends Fragment {
    private FragmentMenuBinding mBinding;
    private MenusViewModel mMenus;
    private String[] menus = {"Breakfast", "Lunch", "Dinner", "Sweet", "Drink"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false);
        mMenus = new MenusViewModel();
        mBinding.setMenus(mMenus);
        mBinding.setView(this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mBinding.recyclerMenu.setLayoutManager(layoutManager);

        return mBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMenus = null;
        mBinding.unbind();
        mBinding = null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        for (String s : menus) {
            mMenus.add(new Menu(s, "Image " + s));
        }
    }

    public ClickHandler<MenuViewModel> clickHandler() {
        return new ClickHandler<MenuViewModel>() {
            @Override
            public void onClick(MenuViewModel viewModel, View v) {
                startActivity(new Intent(getContext(), PagerActivity.class));
            }
        };
    }

    public ItemBinder<MenuViewModel> itemViewBinder() {
        return new CompositeItemBinder<>(
                new MenuBinder.Body(BR.menu, R.layout.item_menu)
        );
    }
}
