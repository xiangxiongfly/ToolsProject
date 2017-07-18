package com.example.xx.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;


public class ScaleInTransformer implements ViewPager.PageTransformer {
    private static final float DEFAULT_MIN_SCALE = 0.85f;
    private float mMinScale = DEFAULT_MIN_SCALE;
    public static final float DEFAULT_CENTER = 0.5f;

    @Override
    public void transformPage(View page, float position) {
        int pageWidth = page.getWidth();
        int pageHeight = page.getHeight();

        page.setPivotY(pageHeight / 2);
        page.setPivotX(pageWidth / 2);

        if (position < -1) {
            page.setScaleX(mMinScale);
            page.setScaleY(mMinScale);
            page.setPivotX(pageWidth);
        } else if (position > 1) {
            page.setScaleX(mMinScale);
            page.setScaleY(mMinScale);
            page.setPivotX(0);
        } else {
            if (position < 0) {//[0,-1]
                float factor = mMinScale + (1 - mMinScale) * (1 + position);
                page.setScaleY(factor);
                page.setScaleY(factor);
                page.setPivotX(pageWidth * (DEFAULT_CENTER + (DEFAULT_CENTER * -position)));
            } else {//[1,0]
                float factor = mMinScale + (1 - mMinScale) * (1 - position);
                page.setScaleY(factor);
                page.setScaleY(factor);
                page.setPivotX(pageWidth * ((1 - position) * DEFAULT_CENTER));
            }
        }
    }
}
