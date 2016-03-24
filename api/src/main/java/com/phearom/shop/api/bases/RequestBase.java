package com.phearom.shop.api.bases;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.phearom.shop.api.Server;
import com.phearom.shop.api.listener.OnResponseListener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by phearom on 3/15/16.
 */
public abstract class RequestBase<T> implements Response.ErrorListener, Response.Listener<String> {
    private static RequestQueue queue;
    private OnResponseListener<T> mOnResponseListener;
    private Context mContext;
    private Class<T> clazz;

    public RequestBase(Context context) {
        mContext = context;
    }

    public Class<T> getGenericClass() throws Exception {
        if (clazz == null) {
            Type mySuperclass = getClass().getGenericSuperclass();
            Type tType = ((ParameterizedType) mySuperclass).getActualTypeArguments()[0];
            String className = tType.toString().split(" ")[1];
            clazz = (Class<T>) Class.forName(className);
        }
        return clazz;
    }

    public RequestQueue getRequestQueue() {
        if (null == queue)
            queue = Volley.newRequestQueue(mContext);
        return queue;
    }

    public int getRequestMethod() {
        return Request.Method.GET;
    }

    public abstract void getParams();

    public abstract String getFunctionName();

    public String getServerUrl() {
        return Server.init().getServerHost() + getFunctionName();
    }

    public T prepareData(String data) {
        try {
            return new Gson().fromJson(data, getGenericClass());
        } catch (Exception e) {
            Log.e("Server Mapper", " error >>> " + e.getMessage());
            return null;
        }
    }

    public void execute() {
        StringRequest stringRequest = new StringRequest(getRequestMethod(), getServerUrl(), this, this);
        getRequestQueue().add(stringRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        if (null != mOnResponseListener)
            mOnResponseListener.onError(error);
    }

    @Override
    public void onResponse(String response) {
        Log.i("Server Response", getFunctionName() + " : " + response);
        if (null != mOnResponseListener) {
            mOnResponseListener.setData(response);
            mOnResponseListener.onResponse(prepareData(response));
        }
    }

    public void setOnResponseListener(OnResponseListener mOnResponseListener) {
        this.mOnResponseListener = mOnResponseListener;
    }
}
