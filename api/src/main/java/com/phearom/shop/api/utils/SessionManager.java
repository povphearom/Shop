package com.phearom.shop.api.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by phearom on 3/24/16.
 */
public class SessionManager {
    private static SessionManager instance;
    private SharedPreferences mShare;
    private SharedPreferences.Editor mEditor;
    private Context mContext;

    private SessionManager(Context context) {
        mContext = context;
        mShare = context.getSharedPreferences("shop_preference", Context.MODE_PRIVATE);
        mEditor = mShare.edit();
    }

    public static SessionManager init(Context context) {
        if (instance == null)
            instance = new SessionManager(context);
        return instance;
    }

    public void saveUserData(String key, String val) {
        mEditor.putString(key, val);
        mEditor.apply();
    }

    public void saveUserData(String key, int val) {
        mEditor.putInt(key, val);
        mEditor.apply();
    }

    public void saveUserData(String key, boolean val) {
        mEditor.putBoolean(key, val);
        mEditor.apply();
    }

    public String getUserData(String key, String def) {
        return mShare.getString(key, def);
    }

    public int getUserData(String key, int def) {
        return mShare.getInt(key, def);
    }

    public boolean getUserData(String key, boolean def) {
        return mShare.getBoolean(key, def);
    }

    public void reset() {
        mEditor.clear();
        mEditor.apply();
    }
}
