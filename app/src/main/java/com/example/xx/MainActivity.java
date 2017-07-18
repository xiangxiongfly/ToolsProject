package com.example.xx;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.xx.act.CircleProgressActivity;
import com.example.xx.act.DialogActivity;
import com.example.xx.act.ImageLoaderActivity;
import com.example.xx.act.InputActivity;
import com.example.xx.act.NestActivity;
import com.example.xx.act.PieViewActivity;
import com.example.xx.act.TextActivity;
import com.example.xx.act.ToastActivity;
import com.example.xx.act.VerifyCodeActivity;
import com.example.xx.act.ViewPagerActivity;
import com.example.xx.act.notification.NotificationActivity;
import com.example.xx.adapter.MyAdapter;
import com.example.xx.bean.ModelMainItem;
import com.example.xx.decoration.MyItemDecoration;
import com.example.xx.utils.UiUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private Context mContext;
    private ArrayList<ModelMainItem> mData;
    private int mColumnCount = 3;

    private static final int TIME_INTERVAL = 2000;//间隔时间
    private long mBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
        initData();
        initView();
    }

    private void initData() {
        mData = new ArrayList<>();
        mData.add(new ModelMainItem("输入框", R.drawable.icon_input, InputActivity.class));
        mData.add(new ModelMainItem("Text操作", R.drawable.icon_text, TextActivity.class));
        mData.add(new ModelMainItem("验证码", R.drawable.icon_verification, VerifyCodeActivity.class));
        mData.add(new ModelMainItem("Toast", R.drawable.icon_toast, ToastActivity.class));
        mData.add(new ModelMainItem("Dialog", R.drawable.icon_dialog, DialogActivity.class));
        mData.add(new ModelMainItem("图表", R.drawable.icon_chart, PieViewActivity.class));
        mData.add(new ModelMainItem("进度条", R.drawable.icon_progress, CircleProgressActivity.class));
        mData.add(new ModelMainItem("ViewPager", R.drawable.icon_viewpager, ViewPagerActivity.class));
        mData.add(new ModelMainItem("嵌套", R.drawable.icon_viewpager, NestActivity.class));
        mData.add(new ModelMainItem("ImageLoader", R.drawable.icon_image, ImageLoaderActivity.class));
        mData.add(new ModelMainItem("Notification", R.drawable.icon_image, NotificationActivity.class));

    }

    private void initView() {
        recyclerview.setLayoutManager(new GridLayoutManager(mContext, mColumnCount));
        recyclerview.addItemDecoration(new MyItemDecoration(UiUtils.dp2px(mContext, 5f)));
        recyclerview.setAdapter(new MyAdapter(mContext, mData));
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - mBackPressed < TIME_INTERVAL) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(getBaseContext(), "再次点击返回键退出", Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();
    }
}
