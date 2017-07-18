package com.example.xx.utils;

import android.content.Context;

public class AppUtils {
    private static Context context;

    public static void init(Context context) {
        AppUtils.context = context.getApplicationContext();
    }

    public static Context getContext() {
        if (context != null)
            return context;
        throw new NullPointerException("请先调用init()方法");
    }
}
