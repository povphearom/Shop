package com.phearom.shop.mapper;

import com.phearom.shop.api.respositories.ProductDao;
import com.phearom.shop.models.Product;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by phearom on 3/21/16.
 */
public class ProductMapper {

    public static Product mapperProduct(ProductDao productDao) {
        Product product = new Product();
        product.setId(productDao.getId());
        product.setName(productDao.getName());
        product.setDescription(productDao.getDescription());
        product.setPrice(productDao.getPrice());
        product.setCurrency(productDao.getCurrency());
        product.setUrl(productDao.getUrl());
        return product;
    }

    public static ProductDao mapperProduct(Product product) {
        ProductDao productDao = new ProductDao();
        productDao.setId(product.getId());
        productDao.setName(product.getName());
        productDao.setPrice(product.getPrice());
        productDao.setDescription(product.getDescription());
        productDao.setCurrency(product.getCurrency());
        productDao.setUrl(product.getUrl());
        return productDao;
    }

    public static List<Product> mapperProduct(RealmList<ProductDao> productDaos) {
        List<Product> list = new ArrayList<>();
        Product product = null;
        for (ProductDao productDao : productDaos) {
            product = new Product();
            product.setId(productDao.getId());
            product.setName(productDao.getName());
            product.setPrice(productDao.getPrice());
            product.setDescription(productDao.getDescription());
            product.setCurrency(productDao.getCurrency());
            product.setUrl(productDao.getUrl());
            list.add(product);
        }
        return list;
    }

    public static List<ProductDao> mapperProduct(List<Product> products) {
        List<ProductDao> list = new ArrayList<>();
        ProductDao productDao = null;
        for (Product product : products) {
            productDao = new ProductDao();
            productDao.setId(product.getId());
            productDao.setName(product.getName());
            productDao.setPrice(product.getPrice());
            productDao.setDescription(product.getDescription());
            productDao.setCurrency(product.getCurrency());
            productDao.setUrl(product.getUrl());
            list.add(productDao);
        }
        return list;
    }
}
