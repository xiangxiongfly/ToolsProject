package com.example.xx;

import android.app.Application;

import com.example.xx.utils.AppUtils;

public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppUtils.init(this);
    }
}
