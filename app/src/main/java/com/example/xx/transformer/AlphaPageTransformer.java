package com.example.xx.transformer;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * 透明渐变
 */
public class AlphaPageTransformer implements ViewPager.PageTransformer {
    private static final float DEFAULT_MIN_ALPHA = 0.3f;
    private float mMinAlpha = DEFAULT_MIN_ALPHA;

    @Override
    public void transformPage(View page, float position) {
        Log.e("Tag", "position=" + position);
        page.setScaleX(0.999f);//hack

        if (position < -1) {
            page.setAlpha(mMinAlpha);
            Log.e("Tag","::::"+position);
        } else if (position > 1) {
            page.setAlpha(mMinAlpha);
        } else {
            if (position < 0) { //[0,-1]
                float factor = mMinAlpha + (1 - mMinAlpha) * (1 + position);
                page.setAlpha(factor);
            } else {//[1,0]
                float factor = mMinAlpha + (1 - mMinAlpha) * (1 - position);
                page.setAlpha(factor);
            }
        }
    }
}
