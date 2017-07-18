package com.example.xx.widget.progress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.xx.R;
import com.example.xx.utils.UiUtils;

/**
 * 环形进度条
 */
public class CircleProgressView extends View {
    private Context mContext;
    private int circleColor = Color.GRAY;//圆环颜色
    private int circleProgressColor = Color.GREEN;//圆弧颜色
    private int textColor = Color.BLACK;//文字颜色
    private float circleWidth;//圆环宽度
    private float textSize;//字体大小
    private float max = 100;//最大值
    private float progress;//进度
    private int width;//视图宽度


    private Paint circlePaint;
    private Paint arcPaint;
    private Paint textPaint;
    private int cx;
    private int cy;

    public CircleProgressView(Context context) {
        this(context, null);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initAttr(attrs);
        init();
    }

    private void initAttr(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.CircleProgressView);
        circleColor = typedArray.getColor(R.styleable.CircleProgressView_circleColor, Color.GRAY);
        circleProgressColor = typedArray.getColor(R.styleable.CircleProgressView_circleProgressColor, Color.GREEN);
        textColor = typedArray.getColor(R.styleable.CircleProgressView_textColor, Color.BLACK);
        circleWidth = typedArray.getDimension(R.styleable.CircleProgressView_circleWidth, UiUtils.dp2px(mContext, 10));
        textSize = typedArray.getDimension(R.styleable.CircleProgressView_textSize, UiUtils.dp2px(mContext, 20));
        max = typedArray.getInteger(R.styleable.CircleProgressView_max, 100);
        progress = typedArray.getInteger(R.styleable.CircleProgressView_progress, 10);
        typedArray.recycle();
    }

    private void init() {
        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(circleColor);//设置圆环颜色
        circlePaint.setStyle(Paint.Style.STROKE);//设置圆环样式
        circlePaint.setStrokeWidth(circleWidth);//设置圆环宽度

        arcPaint = new Paint();
        arcPaint.setAntiAlias(true);
        arcPaint.setColor(circleProgressColor);
        arcPaint.setStyle(Paint.Style.STROKE);//设置圆环样式
        arcPaint.setStrokeWidth(circleWidth);//设置圆环宽度

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        textPaint.setStrokeWidth(0);
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        cx = width / 2;
        cy = width / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float radius = width / 2 - circleWidth / 2;//半径

        canvas.drawCircle(cx, cy, radius, circlePaint);

        //绘制文本
        String text = progress * 100 / max + "%";
        Rect rect = new Rect();
        textPaint.getTextBounds(text, 0, text.length(), rect);
        int x = width / 2 - rect.width() / 2;
        int y = width / 2 + rect.height() / 2;
        canvas.drawText(text, x, y, textPaint);

        //绘制圆弧
        RectF rectF = new RectF(circleWidth / 2, circleWidth / 2, width - circleWidth / 2, width - circleWidth / 2);
        updateArcPaint();
        arcPaint.setStrokeCap(Paint.Cap.ROUND);//设置圆角
        if (progress / max * 360 != 360) {
            canvas.save();
            canvas.rotate(-95, cx, cy);
            canvas.drawArc(rectF, 5, progress / max * 360, false, arcPaint);
            canvas.restore();
        } else {
            //防止进度100%时，显示倾斜
            canvas.save();
            canvas.rotate(-90, cx, cy);
            canvas.drawArc(rectF, 0, progress / max * 360, false, arcPaint);
            canvas.restore();
        }
    }

    //设置渐变
    private void updateArcPaint() {
        int[] arcColors = new int[]{
                Color.RED,
                Color.GREEN,
                Color.BLUE
        };
        // 设置渐变
        int[] mGradientColors = {Color.GREEN, Color.YELLOW, Color.RED};
        SweepGradient mSweepGradient = new SweepGradient(cx, cy, arcColors, null);
        arcPaint.setShader(mSweepGradient);
    }
}
