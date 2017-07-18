package com.example.test;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/*
70dp
 */
public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private EditText et_input01;
    private TextView tv1;
    private TextView tv2;

    private MyTextView tv_hight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        et_input01 = (EditText) findViewById(R.id.et_input01);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);


        SpannableString spannableString = new SpannableString("欢迎光临SpannableString的");
        ForegroundColorSpan span = new ForegroundColorSpan(Color.BLUE);
        spannableString.setSpan(span, 1, 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
//        spannableString.setSpan(span, 1, 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        et_input01.setText(spannableString);
        SpannableString ss1 = new SpannableString("AAAAAAAAAAAAAAA");
        SpannableString ss2 = new SpannableString("BBBBBBBBBBBBBBB");
        AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(70);
        RelativeSizeSpan sizeSpan1 = new RelativeSizeSpan(2);
        ss1.setSpan(sizeSpan, 1, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ss2.setSpan(sizeSpan1, 1, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv1.setText(ss1);
        tv2.setText(ss2);


        tv_hight = (MyTextView) findViewById(R.id.tv_hight);
        tv_hight.setText("010");
        tv_hight.setHighlightPosition(1);
        tv_hight.setHighlightNum(3);
        tv_hight.setHighlightColor(Color.WHITE);


        editText.setFilters(new InputFilter[]{getTwoDecimalFilter()});


        String[] s1 = "123#456".split("#");
        for (String s : s1) {
            Log.e("Tag", "s1====" + s);
        }

        String[] s2 = "789.444".split("\\.");
        for (String s : s2) {
            Log.e("Tag", "s2====" + s);
        }
    }

    public  InputFilter getTwoDecimalFilter() {
        InputFilter inputFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if ("".equals(source.toString())) {
                    return null;
                }
                Log.e("Tag", "" + source);

                String dValue = dest.toString();
                String[] spliteArray = dValue.split("\\.");
                if (spliteArray.length > 1) {
                    String dotValue = spliteArray[1];

                    int diff = dotValue.length() + 1 - 2;
                    if (diff > 0) {
                        Log.e("Tag", diff + "--" + start + ":" + end);
                        return source.subSequence(start, end - diff);
                    }


                    if (dotValue.length() == 2) {
                        return source.subSequence(0, 0);
                    }else{
                        Toast.makeText(MainActivity.this,"只能有2位小数", Toast.LENGTH_SHORT).show();
                    }

                }
                return null;
            }
        };
        return inputFilter;
    }
}
