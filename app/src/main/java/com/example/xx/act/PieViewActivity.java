package com.example.xx.act;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.xx.R;
import com.example.xx.widget.pieview.HorLinePieView;
import com.example.xx.widget.pieview.LinePieView;
import com.example.xx.widget.pieview.PercentPieView;

import java.util.Random;

public class PieViewActivity extends AppCompatActivity {
    private HorLinePieView horLinePieView;
    private LinePieView linePieView;
    private PercentPieView percentPieView;

    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_view);
        setTitle("饼状图");

//        横线饼状图
        int[] datas = new int[]{15, 65, 15};
        int[] colors = new int[]{
                0xffff0000, 0xff00ff00, 0xff0000ff
        };
        horLinePieView = (HorLinePieView) findViewById(R.id.horLinePieView);
        horLinePieView.setData(datas, colors);

        //折线饼状图
        int[] data2 = new int[]{10, 10, 20, 40};
        String[] name2 = new String[]{"猫", "狗", "奶牛", "羊驼"};
        int[] colors2 = new int[data2.length];
        for (int i = 0; i < data2.length; i++) {
            //随机颜色
            colors2[i] = randomColor();
        }

        linePieView = (LinePieView) findViewById(R.id.linePieView);
        linePieView.setData(data2, name2, colors2);

        //百分百
        percentPieView= (PercentPieView) findViewById(R.id.percentPieView);
        int[] data3 = new int[]{10, 10, 20, 40, 10, 10, 20};
        String[] name3 = new String[]{"猫", "狗", "奶牛", "羊驼", "大象", "狮子", "老虎"};
        percentPieView.setData(data2, name2);

    }

    private int randomColor() {
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return Color.rgb(red, green, blue);
    }

}
