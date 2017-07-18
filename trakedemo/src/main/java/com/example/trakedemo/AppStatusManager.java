package com.example.trakedemo;

/**
 * Created by Administrator on 2017/7/17.
 */

public class AppStatusManager {
    public int mAppStatus = AppStatusConstant.STATUS_FORCE_KILLED;

    public static AppStatusManager mAppStatusManager;

    private AppStatusManager() {
    }

    public static AppStatusManager getInstance() {
        if (mAppStatusManager == null) {
            mAppStatusManager = new AppStatusManager();
        }
        return mAppStatusManager;
    }

    public int getAppStatus() {
        return mAppStatus;
    }

    public void setAppStatus(int appStatus) {
        this.mAppStatus = appStatus;
    }
}
