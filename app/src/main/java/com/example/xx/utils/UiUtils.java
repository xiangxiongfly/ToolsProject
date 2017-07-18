package com.example.xx.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.WindowManager;

public class UiUtils {

    /**
     * dp转px
     *
     * @param context 上下文
     * @param dpValue dp值
     * @return px值
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale);
    }

    /**
     * 设置透明状态栏(api大于19方可使用)
     * <p>可在Activity的onCreat()中调用</p>
     * <p>需在顶部控件布局中加入以下属性让内容出现在状态栏之下</p>
     * <p>android:clipToPadding="true"</p>
     * <p>android:fitsSystemWindows="true"</p>
     *
     * @param activity activity
     */
    public static void setTransparentStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);  //透明状态栏
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //透明导航栏
        }
    }

    /**
     * 匹配手机号码的正则表达式，只校验 1 开头并且是 11 位数的。
     */
    public static final String PHONE_NUMBER_REGEX = "^1(3|4|5|7|8)[0-9]\\d{8}$";

    /**
     * 校验手机号是否正确
     *
     * @param phone 手机号
     * @return true: 正确 false: 不正确
     */
    public static boolean checkPhone(String phone) {
        return phone.matches(PHONE_NUMBER_REGEX);
    }

    /**
     * 匹配密码的正则表达式(不能以数字开头,6-15位,并且是包含数字和字母的组合)
     */
    public static final String PASSWORD = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";

    /**
     * 校验密码是否输入正确
     *
     * @param password 密码
     * @return true: 正确 false：不正确
     */
    public static boolean checkPassword(String password) {
        return password.matches(PASSWORD);
    }
}
