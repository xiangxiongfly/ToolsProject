package com.example.xx.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.xx.R;
import com.example.xx.act.nest.Nest1Activity;
import com.example.xx.act.nest.Nest2Activity;


public class NestActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn01;
    private Button btn02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nest);
        setTitle("嵌套冲突");

        btn01 = (Button) findViewById(R.id.btn01);
        btn02 = (Button) findViewById(R.id.btn02);

        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn01:
                startActivity(new Intent(this, Nest1Activity.class));
                break;
            case R.id.btn02:
                startActivity(new Intent(this, Nest2Activity.class));
                break;
        }
    }
}
