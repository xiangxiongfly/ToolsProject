package com.example.trakedemo;

import android.os.Bundle;
import android.widget.TextView;

public class InfoActivity extends BaseActivity {
    private TextView tvInfo;

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.activity_info);
    }

    @Override
    protected void setUpView() {
        tvInfo = (TextView) findViewById(R.id.tv_info);
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        tvInfo.setText(MyApplication.list.get(0));
    }
}
