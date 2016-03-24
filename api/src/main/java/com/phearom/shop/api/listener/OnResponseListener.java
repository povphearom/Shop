package com.phearom.shop.api.listener;

import com.android.volley.VolleyError;

/**
 * Created by phearom on 3/15/16.
 */
public abstract class OnResponseListener<T> {
    public static final int SUCCESS = 1;
    public static final int FAIL = 0;
    private String mData;

    public abstract void onResponse(T data);

    public abstract void onError(VolleyError error);

    public String getData() {
        return mData;
    }

    public void setData(String data) {
        this.mData = data;
    }
}
