package com.example.trakedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switch (AppStatusManager.getInstance().getAppStatus()) {
            case AppStatusConstant.STATUS_FORCE_KILLED://App被强杀
                Log.e("Tag", "STATUS_FORCE_KILLED");
                protectApp();
                break;
            case AppStatusConstant.STATUS_KICK_OUT://用户被踢或token失效
                Log.e("Tag", "STATUS_KICK_OUT");
                break;
            case AppStatusConstant.STATUS_NORMAL:
                Log.e("Tag", "STATUS_NORMAL");
                setUpContentView();
                setUpView();
                setUpData(savedInstanceState);
                break;
        }
    }

    protected abstract void setUpContentView();

    protected abstract void setUpView();

    protected abstract void setUpData(Bundle savedInstanceState);


    protected void protectApp() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(AppStatusConstant.KEY_HOME_ACTION, AppStatusConstant.ACTION_RESTART_APP);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
