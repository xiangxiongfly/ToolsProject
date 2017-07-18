package com.example.xx.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;

public class RotateYPagerTransformer implements ViewPager.PageTransformer {
    private static final float DEFAULT_MAX_ROTATE = 35f;
    private float mMaxRotate = DEFAULT_MAX_ROTATE;
    public static final float DEFAULT_CENTER = 0.5f;

    @Override
    public void transformPage(View page, float position) {
        if (position < -1) {
            page.setPivotX(page.getWidth());
            page.setRotationY(-mMaxRotate);
        } else if (position > 1) {
            page.setPivotX(0);
            page.setRotationY(mMaxRotate);
        } else {
            if (position < 0) {//[0,-1]      page.getWidth/2 ,page.getWidth
                page.setPivotX(page.getWidth() * (DEFAULT_CENTER + DEFAULT_CENTER * (-position)));
            } else {//[1,0]     0,page.getWidth/2
                page.setPivotX(page.getWidth() * DEFAULT_CENTER * (1 - position));
            }
            page.setRotationY(position * mMaxRotate);
        }
    }
}
