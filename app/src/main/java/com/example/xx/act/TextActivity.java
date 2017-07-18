package com.example.xx.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.xx.R;
import com.example.xx.widget.text.AutoWrapTextView;

public class TextActivity extends AppCompatActivity {
    private String text = "text密码：jokG5456KL542356jsjdherGHS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        ((AutoWrapTextView) findViewById(R.id.awtextview)).setText(text);
    }
}
