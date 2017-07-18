package com.example.xx.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.xx.R;
import com.example.xx.bean.ModelMainItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<ModelMainItem> mDatas;

    public MyAdapter(Context context, ArrayList<ModelMainItem> data) {
        mContext = context;
        mDatas = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_recyclerview_main, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ModelMainItem modelItem = mDatas.get(position);
        holder.tvName.setText(modelItem.getName());
        Glide.with(mContext).
                load(modelItem.getImage()).
                diskCacheStrategy(DiskCacheStrategy.RESULT).
                thumbnail(0.5f).
                priority(Priority.HIGH).
                placeholder(R.drawable.pikachu_sit).
                error(R.drawable.pikachu_sit).
                fallback(R.drawable.pikachu_sit).
                into(holder.ivIcon);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext,modelItem.getActivity()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_name)
        TextView tvName;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
