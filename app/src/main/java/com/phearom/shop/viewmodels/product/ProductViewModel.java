package com.phearom.shop.viewmodels.product;

import com.phearom.shop.models.Product;

/**
 * Created by phearom on 3/16/16.
 */
public class ProductViewModel {
    private final Product model;

    public ProductViewModel(Product product) {
        model = product;
    }

    public Product getModel() {
        return this.model;
    }

    public String getId() {
        return model.getId();
    }

    public String getName() {
        return model.getName();
    }

    public String getUrl() {
        return model.getUrl();
    }

    public String getDescription() {
        return model.getDescription();
    }

    public double getPrice() {
        return model.getPrice();
    }

    public int getCurrency() {
        return model.getCurrency();
    }
}
