package com.example.xx.widget.nest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * ScrollView 嵌套 ViewPager,外部解决法
 */

public class MyScrollView extends ScrollView {

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private float downX;
    private float downY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        float x = ev.getX();
        float y = ev.getY();

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = x;
                downY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                float offsetX = Math.abs(x - downX);
                float offsetY = Math.abs(y - downY);
                if (offsetX > offsetY) {
                    intercept = false;
                } else {
                    intercept = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
        }


        return intercept;
    }
}
