package com.phearom.shop.ui.fragments;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
import com.phearom.shop.viewmodels.MenuHeadViewModel;
import com.phearom.shop.viewmodels.MenuViewModel;
import com.phearom.shop.viewmodels.MenusViewModel;

/**
 * Created by phearom on 3/21/16.
 */
public class MenuFragment extends Fragment {
    private FragmentMenuBinding mBinding;
    private MenusViewModel mMenus;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false);
        mMenus = new MenusViewModel();
        mMenus.add(new MenuHeadViewModel(new Menu()));
        mBinding.setMenus(mMenus);
        mBinding.setView(this);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        mBinding.recyclerMenu.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (mMenus.getItem(position) instanceof MenuHeadViewModel) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });

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
        mMenus.add(new Menu("Sweet", ""));
        mMenus.add(new Menu("Sweet", ""));
        mMenus.add(new Menu("Sweet", ""));
        mMenus.add(new Menu("Sweet", ""));
        mMenus.add(new Menu("Sweet", ""));
        mMenus.add(new Menu("Sweet", ""));
        mMenus.add(new Menu("Sweet", ""));
        mMenus.add(new Menu("Sweet", ""));
        mMenus.add(new Menu("Sweet", ""));
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
                new MenuBinder.Head(BR.menuHead, R.layout.item_menu_head),
                new MenuBinder.Body(BR.menu, R.layout.item_menu)
        );
    }
}
