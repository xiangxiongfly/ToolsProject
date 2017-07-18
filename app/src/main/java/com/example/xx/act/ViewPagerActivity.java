package com.example.xx.act;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.xx.R;
import com.example.xx.transformer.AlphaPageTransformer;
import com.example.xx.transformer.RotateDownPagerTransformer;
import com.example.xx.transformer.RotateUpPagerTransformer;
import com.example.xx.transformer.RotateYPagerTransformer;
import com.example.xx.transformer.ScaleInTransformer;

/**
 * A:android:clipChildren="false"
 * 1、android:clipChildren的意思：是否限制子View在其范围内，我们将其值设置为false后那么当子控件的高度高于父控件时也会完全显示,而不会被压缩
 * 2、只需在根节点设置Android:clipChildren为false即可，默认为true，注意：一定是在布局文件的根节点设置，否则不起作用
 * B:ViewPager.setPageTransformer
 * postion:[-Infinity,-1) ：前面一个View
 * ________(1,+Infinity] ：后面一个View
 * _______[-1,1] ：第一页->第二页：页1的position：从0到-1，页2的position：从-1到0
 * ________________
 */

public class ViewPagerActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    int[] imgRes = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d,
            R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i};
    private PagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        setTitle("ViewPager");

        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        mViewPager.setPageMargin(40);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return imgRes.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object o) {
                return view == o;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(ViewPagerActivity.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setImageResource(imgRes[position]);
                container.addView(imageView);

                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

        mViewPager.setPageTransformer(true, new RotateYPagerTransformer());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        String[] menus = this.getResources().getStringArray(R.array.menu_page);
        for (String m : menus) {
            menu.add(m);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mViewPager.setAdapter(mAdapter);
        String title = item.getTitle().toString();

        switch (title) {
            case "Alpha":
                mViewPager.setPageTransformer(true, new AlphaPageTransformer());
                break;
            case "RotateDown":
                mViewPager.setPageTransformer(true, new RotateDownPagerTransformer());
                break;
            case "RotateUp":
                mViewPager.setPageTransformer(true, new RotateUpPagerTransformer());
                break;
            case "RotateY":
                mViewPager.setPageTransformer(true, new RotateYPagerTransformer());
                break;
            case "ScaleIn":
                mViewPager.setPageTransformer(true, new ScaleInTransformer());
                break;
        }
        return true;
    }
}
