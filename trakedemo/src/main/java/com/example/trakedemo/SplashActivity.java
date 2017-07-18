package com.example.trakedemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppStatusManager.getInstance().setAppStatus(AppStatusConstant.STATUS_NORMAL);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void setUpView() {

    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        handler.sendEmptyMessageDelayed(0, 1500);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e("Tag", "Splash");
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            SplashActivity.this.finish();
        }
    };
}
