package com.example.xx.utils.imageloader;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by xx on 2017-07-14.
 */

public interface IImageLoader {
    void showImage(@NonNull ImageLoaderOptions options);

    void cleanMemory(Context context);

    void init(Context context);
}
