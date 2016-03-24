package com.phearom.shop.Requests;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.phearom.shop.api.bases.RequestBase;
import com.phearom.shop.models.responses.ResPost;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by phearom on 3/15/16.
 */
public class PostRequest extends RequestBase<List<ResPost>> {
    private final static String functionName = "posts";

    public PostRequest(Context context) {
        super(context);
    }

    @Override
    public void getParams() {

    }

    @Override
    public List<ResPost> prepareData(String data) {
        Type listType = new TypeToken<ArrayList<ResPost>>() {
        }.getType();
        return new Gson().fromJson(data, listType);
    }

    @Override
    public String getFunctionName() {
        return functionName;
    }
}
