package com.example.xx.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * 底部旋转
 */
public class RotateDownPagerTransformer implements ViewPager.PageTransformer {
    private static final float DEFAULT_MAX_ROTATE = 15.0f;
    public static final float DEFAULT_CENTER = 0.5f;
    private float mMaxRotate = DEFAULT_MAX_ROTATE;

    @Override
    public void transformPage(View page, float position) {
        if (position < -1) {
            page.setRotation(mMaxRotate * -1);
            page.setPivotX(page.getWidth());
            page.setPivotY(page.getHeight());
        } else if (position > 1) {
            page.setRotation(mMaxRotate);
            page.setPivotX(page.getWidth() * 0);
            page.setPivotY(page.getHeight());
        } else {
            if (position < 0) {//[0,-1]
                page.setPivotX(page.getWidth() * (DEFAULT_CENTER + DEFAULT_CENTER * (-position)));
                page.setPivotY(page.getHeight());
                page.setRotation(mMaxRotate * position);
            } else {//[1,0]
                page.setPivotX(page.getWidth() * DEFAULT_CENTER * (1 - position));
                page.setPivotY(page.getHeight());
                page.setRotation(mMaxRotate * position);
            }
        }
    }
}
