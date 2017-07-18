package com.example.xx.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.support.annotation.CheckResult;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.xx.R;

import static android.support.v4.view.ViewCompat.setBackground;

/**
 * Toast工具
 */
public class ToastUtils {
    @ColorInt
    private static final int DEFAULT_TEXT_COLOR = Color.parseColor("#FFFFFF");
    @ColorInt
    private static final int ERROR_COLOR = Color.parseColor("#FD4C5B");
    @ColorInt
    private static final int INFO_COLOR = Color.parseColor("#3F51B5");
    @ColorInt
    private static final int SUCCESS_COLOR = Color.parseColor("#388E3C");
    @ColorInt
    private static final int WARNING_COLOR = Color.parseColor("#FFA900");

    private static Toast currentToast;
    private static final String TOAST_TYPEFACE = "sans-serif-condensed";

    @CheckResult
    public static Toast custom(@NonNull Context context, @NonNull String message, Drawable icon, @ColorInt int textColor, int duration, boolean withIcon) {
        return custom(context, message, icon, textColor, -1, duration, false);
    }

    /**
     * @param context
     * @param message    消息
     * @param icon       图标
     * @param textColor  字体颜色
     * @param tintColor  Toast背景颜色
     * @param duration   时间
     * @param shouldTint 是否有背景颜色
     * @return
     */
    private static Toast custom(@NonNull Context context, @NonNull String message, Drawable icon, @ColorInt int textColor, @ColorInt int tintColor, int duration, boolean shouldTint) {
        if (currentToast == null) {
            currentToast = new Toast(context);
        }
        final View toastLayout = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
        final ImageView toastIcon = (ImageView) toastLayout.findViewById(R.id.toast_icon);
        final TextView toastText = (TextView) toastLayout.findViewById(R.id.toast_text);

        //设置背景颜色
        Drawable drawableFrame;
        if (shouldTint) {
            drawableFrame = tint9PatchDrawableFrame(context, tintColor);
        } else {
            drawableFrame = ContextCompat.getDrawable(context, R.drawable.toast_frame);
        }
        setBackground(toastLayout, drawableFrame);

        //设置图标

        if (icon != null) {
            setBackground(toastIcon, icon);
        } else {
            toastIcon.setVisibility(View.GONE);
        }

        toastText.setTextColor(textColor);
        toastText.setText(message);
        toastText.setTypeface(Typeface.create(TOAST_TYPEFACE, Typeface.NORMAL));

        currentToast.setView(toastLayout);
        currentToast.setDuration(duration);

        return currentToast;
    }

    private static final Drawable tint9PatchDrawableFrame(Context context, int tintColor) {
        final NinePatchDrawable toastDrawable = (NinePatchDrawable) ContextCompat.getDrawable(context, R.drawable.toast_frame);
        toastDrawable.setColorFilter(new PorterDuffColorFilter(tintColor, PorterDuff.Mode.SRC_IN));
        return toastDrawable;
    }

    //###################   ERROR   ########################
    public static Toast error(Context context, String message) {
        return error(context, message, Toast.LENGTH_SHORT);
    }

    //错误
    public static Toast error(Context context, String message, int duration) {
        return custom(context, message, ContextCompat.getDrawable(context, R.drawable.toast_error), DEFAULT_TEXT_COLOR, ERROR_COLOR, duration, true);
    }

    //###################   ERROR   ########################
    public static Toast success(Context context, String message) {
        return success(context, message, Toast.LENGTH_SHORT);
    }

    public static Toast success(Context context, String message, int duration) {
        return custom(context, message, ContextCompat.getDrawable(context, R.drawable.toast_success), DEFAULT_TEXT_COLOR, SUCCESS_COLOR, duration, true);
    }

    //###################   INFO   ########################
    public static Toast info(Context context, String message) {
        return info(context, message, Toast.LENGTH_SHORT);
    }

    public static Toast info(Context context, String message, int duration) {
        return custom(context, message, ContextCompat.getDrawable(context, R.drawable.toast_info), DEFAULT_TEXT_COLOR, INFO_COLOR, duration, true);
    }

    //###################   WARNING   ########################
    public static Toast warning(Context context, String message) {
        return warning(context, message, Toast.LENGTH_SHORT);
    }

    public static Toast warning(Context context, String message, int duration) {
        return custom(context, message, ContextCompat.getDrawable(context, R.drawable.toast_warning), DEFAULT_TEXT_COLOR, WARNING_COLOR, duration, true);
    }

    //###################  NORMAL   ########################
    public static void normal(String message) {
        normal(AppUtils.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public static Toast normal(Context context, String message) {
        return normal(context, message, Toast.LENGTH_SHORT);
    }

    public static Toast normal(Context context, String message, int duration) {
        return custom(context, message, ContextCompat.getDrawable(context, R.drawable.toast_normal), DEFAULT_TEXT_COLOR, duration, false);
    }

}
