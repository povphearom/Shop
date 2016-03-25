package com.phearom.shop.binder;

import com.phearom.shop.api.bindapi.binder.ConditionalDataBinder;
import com.phearom.shop.viewmodels.MenuHeadViewModel;
import com.phearom.shop.viewmodels.MenuViewModel;

/**
 * Created by phearom on 3/16/16.
 */
public class MenuBinder {
    public static class Body extends ConditionalDataBinder<MenuViewModel> {
        public Body(int bindingVariable, int layoutId) {
            super(bindingVariable, layoutId);
        }

        @Override
        public boolean canHandle(MenuViewModel model) {
            return true;
        }
    }

    public static class Head extends ConditionalDataBinder<MenuViewModel>
    {
        public Head(int bindingVariable, int layoutId) {
            super(bindingVariable, layoutId);
        }

        @Override
        public boolean canHandle(MenuViewModel model) {
            return model instanceof MenuHeadViewModel;
        }
    }
}
