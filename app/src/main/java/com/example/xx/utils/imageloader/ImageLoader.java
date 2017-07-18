package com.example.xx.utils.imageloader;


import android.content.Context;
import android.support.annotation.NonNull;

public class ImageLoader implements IImageLoader {
    private static final ImageLoader instance = new ImageLoader();

    private ImageLoader() {
    }

    public static ImageLoader getInstance() {
        return instance;
    }

    @Override
    public void showImage(@NonNull ImageLoaderOptions options) {

    }

    @Override
    public void cleanMemory(Context context) {

    }

    @Override
    public void init(Context context) {

    }
}
