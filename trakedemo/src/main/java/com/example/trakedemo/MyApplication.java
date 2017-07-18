package com.example.trakedemo;

import android.app.Application;

import java.util.List;

public class MyApplication extends Application {
    private static MyApplication myApplication;
    public static List<String> list;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }
}
