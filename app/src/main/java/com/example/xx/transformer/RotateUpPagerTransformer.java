package com.example.xx.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;

public class RotateUpPagerTransformer implements ViewPager.PageTransformer {
    private static final float DEFAULT_MAX_ROTATE = 15.0f;
    private float mMaxRotate = DEFAULT_MAX_ROTATE;

    @Override
    public void transformPage(View page, float position) {
        if (position < -1) {
            page.setPivotX(page.getWidth());
            page.setPivotY(0);
            page.setRotation(mMaxRotate);
        } else if (position > 1) {
            page.setPivotX(0);
            page.setPivotY(0);
            page.setRotation(-mMaxRotate);
        } else {
            if (position < 0) { //[0,-1]
                page.setPivotX(page.getWidth() * (0.5f - 0.5f * (position)));
                page.setPivotY(0);
                page.setRotation(-DEFAULT_MAX_ROTATE * position);
            } else {//[1,0]
                page.setPivotX(page.getWidth() * (0.5f - 0.5f * position));
                page.setPivotY(0);
                page.setRotation(-DEFAULT_MAX_ROTATE * position);
            }
        }
    }
}
