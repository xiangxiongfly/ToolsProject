package com.example.xx.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.widget.EditText;
import com.example.xx.utils.UiUtils;

import java.lang.reflect.Field;

/**
 * 自定义密码输入框
 */
@SuppressLint("AppCompatCustomView")
public class PassView extends EditText {
    private Context mContext;
    private Paint mPaint;
    private Paint mPaintContent;
    private Paint mPaintArc;

    private int maxLineSize;//最大字数
    private int mPadding = 1;//边框线
    private int radius = 1;//圆角
    private boolean isAddText;


    public PassView(Context context) {
        this(context, null);
    }

    public PassView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PassView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.STROKE);//空心

        mPaintArc = new Paint();
        mPaintArc.setAntiAlias(true);
        mPaintArc.setStyle(Paint.Style.FILL);//填充

        mPaintContent = new Paint();
        mPaintArc.setAntiAlias(true);
        mPaintContent.setStyle(Paint.Style.FILL);

        maxLineSize = getMaxLength();
        radius = UiUtils.dp2px(mContext, 6);
    }

    /**
     * 得到布局文件中定义的最长字符数
     */
    public int getMaxLength() {
        int length = 0;
        InputFilter[] inputFilters = getFilters();
        for (InputFilter inputFilter : inputFilters) {
            Class<?> c = inputFilter.getClass();
            if (c.getName().equals("android.text.InputFilter$LengthFilter")) {
                Field[] f = c.getDeclaredFields();
                for (Field field : f) {
                    if (field.getName().equals("mMax")) {
                        field.setAccessible(true);
                        try {
                            length = (int) field.get("inputFilter");
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }
        return length;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制白色背景
        RectF rectF = new RectF(mPadding, mPadding, getMeasuredWidth() - mPadding, getMeasuredHeight() - mPadding);
        canvas.drawRoundRect(rectF, radius, radius, mPaintContent);

        //h绘制外面线框
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(rectF, radius, radius, mPaint);

        float cx;
        float cy = getMeasuredHeight() / 2;
        float half = getMeasuredWidth() / maxLineSize / 2;//单个输入框的宽度一半
        mPaint.setStrokeWidth(0.5f);

        for (int i = 0; i < maxLineSize; i++) {
            float x = getMeasuredWidth() / maxLineSize * i;
            canvas.drawLine(x, 0, 2, getMeasuredHeight(), mPaint);
        }

        for (int i = 0; i < maxLineSize; i++) {
            float x = getMeasuredWidth() / maxLineSize * i + half;
            // 增加文字

            //http://www.jianshu.com/p/18d7a74caa7a


        }


    }
}
