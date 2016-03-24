package com.phearom.shop.Requests;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.phearom.shop.api.bases.RequestBase;
import com.phearom.shop.models.Product;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by phearom on 3/15/16.
 */
public class ProductRequest extends RequestBase<List<Product>> {
    private final static String functionName = "getProducts";

    public ProductRequest(Context context) {
        super(context);
    }

    @Override
    public void getParams() {
    }

    @Override
    public List<Product> prepareData(String data) {
        Type listType = new TypeToken<ArrayList<Product>>() {
        }.getType();
        return new Gson().fromJson(data, listType);
    }

    @Override
    public String getFunctionName() {
        return functionName;
    }
}
