package com.example.trakedemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private TextView tvGotoInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void setUpView() {
        tvGotoInfo = (TextView) findViewById(R.id.tv_goto_info);
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        MyApplication.list = new ArrayList<>();
        MyApplication.list.add("MainActivty发出的消息");
        Log.e("Tag", "Main");
        tvGotoInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InfoActivity.class));
            }
        });
    }

    @Override
    protected void protectApp() {
        Toast.makeText(getApplicationContext(), "应用被回收，重走流程", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, SplashActivity.class));
        finish();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int action = intent.getIntExtra(AppStatusConstant.KEY_HOME_ACTION, AppStatusConstant.ACTION_BACK_TO_HOME);
        switch (action) {
            case AppStatusConstant.ACTION_RESTART_APP:
                protectApp();
                break;
            case AppStatusConstant.STATUS_KICK_OUT:
                break;
            case AppStatusConstant.ACTION_BACK_TO_HOME:
                break;
        }
    }
}
