package com.example.test;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;

public class MyTextView extends AppCompatTextView {
    private int mHighlightPosition = -1;
    private int mHightlightColor = Color.WHITE;
    private int mHightlightNum = 1;

    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, android.R.attr.textStyle);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (getText() == null) {
            return;
        }
        if (mHighlightPosition < 0) {
            return;
        }
        if (mHightlightNum < 1) {
            return;
        }

        int textLength = getText().length();
        if (mHighlightPosition > textLength) {
            return;
        }
        int endPosition = mHighlightPosition + mHightlightNum;
        if (mHightlightNum + mHighlightPosition > textLength) {
            endPosition = textLength;
        }

        SpannableStringBuilder builder = new SpannableStringBuilder(getText().toString());
        ForegroundColorSpan span = new ForegroundColorSpan(mHightlightColor);
        builder.setSpan(span, mHighlightPosition, endPosition, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        setText(builder);
    }

    public void setHighlightPosition(int mHighlightPosition) {
        this.mHighlightPosition = mHighlightPosition;
        init();
    }

    public void setHighlightColor(@ColorInt int mHighlightColor) {
        this.mHightlightColor = mHighlightColor;
        init();
    }

    public void setHighlightNum(int mHighlightNum) {
        this.mHightlightNum = mHighlightNum;
        init();
    }
}
