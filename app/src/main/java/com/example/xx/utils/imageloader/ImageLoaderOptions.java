package com.example.xx.utils.imageloader;


import android.view.View;

import com.bumptech.glide.request.target.BaseTarget;

public class ImageLoaderOptions {
    private View viewContainer;//图片容器
    private String url;//图片地址
    private Integer imageUrl;//图片地址
    private int holderDrawable;//展位图
    private int errorDrawable;//错误图片
    private ImageSize imageSize;//图片大小
    private boolean asGif = false;//是否展示gif图片
    private boolean isCrossFade = true;//是否渐变平滑显示图片
    private boolean isSkipMemoryCache = false;//是否跳过内存缓存
    private DiskCacheStrategy mDiskCacheStrategy = DiskCacheStrategy.DEFAULT; //磁盘缓存策略
    private boolean blurImage=false;//是否使用高斯模糊
    private BaseTarget target=null;//目标


    public final static class ImageSize {
        private int width = 0;
        private int height = 0;

        public ImageSize(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }

    //对应磁盘缓存策略
    public enum DiskCacheStrategy {
        All, NONE, SOURCE, RESULT, DEFAULT
    }
}
