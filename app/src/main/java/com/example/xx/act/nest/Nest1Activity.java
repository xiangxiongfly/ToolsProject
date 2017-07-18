package com.example.xx.act.nest;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.example.xx.R;
import com.example.xx.widget.nest.MyListView;

import java.util.ArrayList;

public class Nest1Activity extends AppCompatActivity {
    private ViewPager viewPager;
    private MyListView listView;
    private ScrollView scrollView;
    private boolean first = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nest1);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        listView = (MyListView) findViewById(R.id.listView);
        scrollView = (ScrollView) findViewById(R.id.scrollView);

        initData();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && first) {
            first = false;
            scroll();
        }
    }

    private void scroll() {
        scrollView.scrollTo(0, 0);
    }

    private void initData() {
        final int[] drawableRes = new int[]{
                R.drawable.a,
                R.drawable.b,
                R.drawable.c,
                R.drawable.d,
                R.drawable.e,
                R.drawable.f
        };

        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return drawableRes.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(Nest1Activity.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setImageResource(drawableRes[position]);
                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

        ArrayList<String> datas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            datas.add("item------- " + i);
        }
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas));
    }
}
