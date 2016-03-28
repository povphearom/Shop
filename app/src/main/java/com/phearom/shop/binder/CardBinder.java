package com.phearom.shop.binder;

import com.phearom.shop.api.bindapi.binder.ConditionalDataBinder;
import com.phearom.shop.viewmodels.CardViewModel;

/**
 * Created by phearom on 3/16/16.
 */
public class CardBinder {
    public static class Body extends ConditionalDataBinder<CardViewModel> {
        public Body(int bindingVariable, int layoutId) {
            super(bindingVariable, layoutId);
        }

        @Override
        public boolean canHandle(CardViewModel model) {
            return true;
        }
    }
}
