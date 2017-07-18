package com.example.xx.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MyItemDecoration extends RecyclerView.ItemDecoration {
    private int space = 0;
    private int leftSpace = 0;
    private int rightSpace = 0;
    private int topSpace = 0;
    private int bottomSpace = 0;

    public MyItemDecoration(int space, int leftSpace, int rightSpace, int topSpace, int bottomSpace) {
        this.space = space;
        this.leftSpace = leftSpace;
        this.rightSpace = rightSpace;
        this.topSpace = topSpace;
        this.bottomSpace = bottomSpace;
    }

    public MyItemDecoration(int space) {
        this.space = space;

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (space == 0) {
            outRect.left = leftSpace;
            outRect.right = rightSpace;
            outRect.top = topSpace;
            outRect.bottom = bottomSpace;
        } else {
            outRect.left = space;
            outRect.right = space;
            outRect.top = space;
            outRect.bottom = space;
        }
    }
}
