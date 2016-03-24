package com.phearom.shop.binder;

import com.phearom.shop.api.bindapi.binder.ConditionalDataBinder;
import com.phearom.shop.viewmodels.product.ProductViewModel;

/**
 * Created by phearom on 3/16/16.
 */
public class ProductBinder {
    public static class Product extends ConditionalDataBinder<ProductViewModel>
    {
        public Product(int bindingVariable, int layoutId) {
            super(bindingVariable, layoutId);
        }

        @Override
        public boolean canHandle(ProductViewModel model) {
            return true;
        }
    }
}
