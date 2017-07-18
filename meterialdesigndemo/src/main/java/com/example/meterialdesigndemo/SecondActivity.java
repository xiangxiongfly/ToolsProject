package com.example.meterialdesigndemo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TabLayout tabs;
//    private TextView fruit_content_text;
    private Toolbar toolbar;

    private ArrayList<String> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initData();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new MyAdapter());

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("AAAAAAAA");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("D"));
        tabs.addTab(tabs.newTab().setText("E"));
        tabs.addTab(tabs.newTab().setText("F"));
    }

    private void initData() {
        for (int i = 0; i < 30; i++) {
            datas.add("item  -->   " + i);
        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(SecondActivity.this).inflate(R.layout.item_recyclerview, parent, false);
            MyViewHolder viewHolder = new MyViewHolder(itemView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv_content.setText(datas.get(position));
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv_content;

            public MyViewHolder(View itemView) {
                super(itemView);
                tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            }
        }
    }
}
