package com.example.xx.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.widget.ImageView;

import java.util.Random;

/**
 * 生成验证码器
 */
public class CaptchaUtils {
    //    数字
    private static final char[] CHARS_NUMBER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    //    字母
    private static final char[] CHARS_LETTER = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
            'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z'};
    //    数字+字母
    private static final char[] CHARS_ALL = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
            'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z'};

    private static CaptchaUtils captchaUtils;
    private TYPE type = TYPE.CHARS;
    private final int DEFAULT_CODE_LENGTH = 4;
    private final int DEFAULT_COLOR = 0xffffff;
    private final int DEFAULT_FONT_SIZE = 60;
    private final int DEFAULT_LINE_NUMBER = 0;
    private final int DEFAULT_WIDTH = 200;
    private final int DEFAULT_HEIGHT = 70;

    private int backgroundColor = DEFAULT_COLOR;//背景颜色
    private int codeLength = DEFAULT_CODE_LENGTH;//验证码长度
    private int fontSize = DEFAULT_FONT_SIZE;//字体大小
    private int lineNumber = DEFAULT_LINE_NUMBER;//干扰线数量
    private int width = DEFAULT_WIDTH;//宽
    private int height = DEFAULT_HEIGHT;//高

    private final int BASE_PADDING_LEFT = 20;//左边距
    private final int BASE_PADDING_TOP = 40;//上边距
    private static final int RANGE_PADDING_LEFT = 20;// 左边距范围值
    private static final int RANGE_PADDING_TOP = 15;// 上边距范围值

    private String code;//生成的验证码
    private Random random = new Random();

    private int paddingLeft, paddingTop;

    public enum TYPE {
        NUMBER, LETTER, CHARS
    }

    public static CaptchaUtils build() {
        if (captchaUtils == null) {
            captchaUtils = new CaptchaUtils();
        }
        return captchaUtils;
    }

    private CaptchaUtils() {
    }

    /**
     * 设置背景颜色
     */
    public CaptchaUtils setBacgroundColor(int color) {
        backgroundColor = color;
        return captchaUtils;
    }

    /**
     * 设置验证码长度
     */
    public CaptchaUtils setCodeLength(int length) {
        codeLength = length;
        return captchaUtils;
    }

    /**
     * 字体大小
     */
    public CaptchaUtils setFontSize(int size) {
        fontSize = size;
        return captchaUtils;
    }

    /**
     * 干扰线数量
     */
    public CaptchaUtils setLineNumber(int number) {
        lineNumber = number;
        return captchaUtils;
    }

    /**
     * 大小
     */
    public CaptchaUtils setSize(int width, int height) {
        this.width = width;
        this.height = height;
        return captchaUtils;
    }

    /**
     * 类型
     */
    public CaptchaUtils setType(TYPE type) {
        this.type = type;
        return captchaUtils;
    }

    public String getCode() {
        return code.toLowerCase();
    }

    /**
     * 生成图片
     */
    public void into(ImageView imageView) {
        Bitmap bitmap = createBitmap();
        if (imageView != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

    private Bitmap createBitmap() {
        paddingLeft = 0;

        Bitmap tBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(tBitmap);
        code = makeCode();
        Log.e("Tag", "code=" + code);

        canvas.drawColor(backgroundColor);
        Paint textPaint = new Paint();
        textPaint.setTextSize(fontSize);
        float textWidth = textPaint.measureText(code);
        float charLength = textWidth / code.length();


        for (int i = 0; i < codeLength; i++) {
            randomTextStyle(textPaint);
            randomPadding();
//            canvas.drawText(String.valueOf(code.charAt(i)), paddingLeft, paddingTop, paint);
            canvas.drawText(String.valueOf(code.charAt(i)), i * charLength * 1.6f + 30, height * 2 / 3f, textPaint);
        }

        Paint linePaint = new Paint();

        for (int i = 0; i < lineNumber; i++) {
            int lineColor = randomColor(1);
            linePaint.setFakeBoldText(random.nextBoolean());
            linePaint.setColor(lineColor);
            drawLine(canvas, linePaint);
        }

        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();

        return tBitmap;
    }

    /**
     * 绘制干扰线
     */
    private void drawLine(Canvas canvas, Paint linePaint) {
        int color = randomColor(1);
        int startX = random.nextInt(width);
        int startY = random.nextInt(height);
        int endX = random.nextInt(width);
        int endY = random.nextInt(height);
        linePaint.setStrokeWidth(1);
        linePaint.setColor(color);
        canvas.drawLine(startX, startX, endX, endY, linePaint);
    }

    private void randomPadding() {
        paddingLeft += BASE_PADDING_LEFT + random.nextInt(RANGE_PADDING_LEFT);
        paddingTop = BASE_PADDING_TOP + random.nextInt(RANGE_PADDING_TOP);
    }

    /**
     * 随机字体类型
     */
    private void randomTextStyle(Paint paint) {
        int color = randomColor(1);
        paint.setColor(color);
        paint.setFakeBoldText(random.nextBoolean());// true为粗体，false为非粗体

        // float skewX = random.nextInt(11) / 10;
        // skewX = random.nextBoolean() ? skewX : -skewX;
        // paint.setTextSkewX(skewX); // float类型参数，负数表示右斜，整数左斜
        // paint.setUnderlineText(true); //true为下划线，false为非下划线
        // paint.setStrikeThruText(true); //true为删除线，false为非删除线
    }

    private int randomColor(int rate) {
        int red = random.nextInt(256) / rate;
        int green = random.nextInt(256) / rate;
        int blue = random.nextInt(256) / rate;
        return Color.rgb(red, green, blue);
    }

    private String makeCode() {
        StringBuffer buffer = new StringBuffer();
        switch (type) {
            case NUMBER:
                for (int i = 0; i < codeLength; i++) {
                    buffer.append(CHARS_NUMBER[random.nextInt(CHARS_NUMBER.length)]);
                }
                break;
            case LETTER:
                for (int i = 0; i < codeLength; i++) {
                    buffer.append(CHARS_LETTER[random.nextInt(CHARS_LETTER.length)]);
                }
                break;
            case CHARS:
                for (int i = 0; i < codeLength; i++) {
                    buffer.append(CHARS_ALL[random.nextInt(CHARS_ALL.length)]);
                }
                break;
            default:
                for (int i = 0; i < codeLength; i++) {
                    buffer.append(CHARS_NUMBER[random.nextInt(CHARS_ALL.length)]);
                }
                break;
        }
        return buffer.toString();
    }
}
