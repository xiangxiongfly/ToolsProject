package com.example.xx.act.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.xx.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.xx.R.drawable.a;

public class NotificationActivity extends AppCompatActivity {
    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_PROGRESS = 2;
    public static final int TYPE_BIGTEXT = 3;
    public static final int TYPE_INBOX = 4;
    public static final int TYPE_BigPicture = 5;
    public static final int TYPE_Hangup = 6;
    public static final int TYPE_MEDIA = 7;
    public static final int TYPE_Customer = 8;

    @BindView(R.id.btn_normal)
    Button btnNormal;
    @BindView(R.id.btn_download)
    Button btnDownload;
    @BindView(R.id.btn_big_text)
    Button btnBigText;
    @BindView(R.id.btn_inbox_style)
    Button btnInboxStyle;
    @BindView(R.id.btn_big_picture_style)
    Button btnBigPictureStyle;
    @BindView(R.id.btn_hangup)
    Button btnHangup;
    @BindView(R.id.btn_media_style)
    Button btnMediaStyle;
    @BindView(R.id.btn_custom_view)
    Button btnCustomView;

    private NotificationManager notificationManager = null;
    private Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        serviceIntent = new Intent(this, DownloadService.class);
    }

    @OnClick({R.id.btn_normal, R.id.btn_download, R.id.btn_big_text, R.id.btn_inbox_style, R.id.btn_big_picture_style, R.id.btn_hangup, R.id.btn_media_style, R.id.btn_custom_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_normal:
                simpleNotification();
                break;
            case R.id.btn_download:
                startService(serviceIntent);
                break;
            case R.id.btn_big_text:
                bitTextStyle();
                break;
            case R.id.btn_inbox_style:
                inBoxStyle();
                break;
            case R.id.btn_big_picture_style:
                bigPictureStyle();
                break;
            case R.id.btn_hangup:
                hangup();
                break;
            case R.id.btn_media_style:
                mediaStyle();
                break;
            case R.id.btn_custom_view:
                Intent intent = new Intent(this, MediaService.class);
                startService(intent);
                break;
        }
    }

    /**
     * 1. 使用类v7包下的NotificationCompat.MediaStyle
     * 2. addAction方法并普通样式也可以用，使用后普通通知可以点击展开，展开部分会显示一排添加的图标，并且可以给每个图标设置不同的点击事件
     * 3. 最多可以添加5哥action 并排显示在点击展开的部分
     * 4. setShowActionsInCompactView的参数是添加的action在所有action组成的数组中的下标，从0开始
     * 5. setShowActionsInCompactView设置的action会显示在点击前的通知的右侧，低版本上也可以显示
     * 6. setShowCancelButton(true)会在通知的右上部分显示一个删除图标 5.0以下有效
     */
    private void mediaStyle() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("MediaStyle");
        builder.setContentText("MediaContent");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.f));
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        Intent intent = new Intent(this, ImageActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, 0);
        builder.setContentIntent(pendingIntent);

        //第一个参数是图标资源id 第二个是图标显示的名称，第三个图标点击要启动的PendingIntent
        builder.addAction(R.drawable.ic_previous_white, "", null);
        builder.addAction(R.drawable.ic_stop_white, "", null);
        builder.addAction(R.drawable.ic_play_arrow_white_18dp, "", pendingIntent);
        builder.addAction(R.drawable.ic_next_white, "", null);
        NotificationCompat.MediaStyle mediaStyle = new NotificationCompat.MediaStyle();
        mediaStyle.setMediaSession(new MediaSessionCompat(this, "MediaSession",
                new ComponentName(NotificationActivity.this, Intent.ACTION_MEDIA_BUTTON), null).getSessionToken());

        //CancelButton在5.0以下的机器有效
        mediaStyle.setCancelButtonIntent(pendingIntent);
        mediaStyle.setShowCancelButton(true);
        //设置要现实在通知右方的图标 最多三个
        mediaStyle.setShowActionsInCompactView(2, 3);
        builder.setStyle(mediaStyle);
        builder.setShowWhen(false);
        Notification notification = builder.build();
        notificationManager.notify(TYPE_MEDIA, notification);
    }

    /**
     * 1. 此种效果只在5.0以上系统中有效
     * 2. mainfest中需要添加<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
     * 3. 可能还需要在设置开启横幅通知权限（在设置通知管理中）
     * 4. 在部分改版rom上可能会直接弹出应用而不是显示横幅
     */
    private void hangup() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Toast.makeText(NotificationActivity.this, "此类通知在Android 5.0以上版本才会有横幅有效！", Toast.LENGTH_SHORT).show();
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("横幅通知");
        builder.setContentText("请在设置通知管理中开启消息横幅提醒权限");
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.f));
        Intent intent = new Intent(this, ImageActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 1, intent, 0);
        builder.setContentIntent(pIntent);
        //重点
        builder.setFullScreenIntent(pIntent, true);
        builder.setAutoCancel(true);
        Notification notification = builder.build();
        notificationManager.notify(TYPE_Hangup, notification);
    }

    private void bigPictureStyle() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("BigPictureStyle");
        builder.setContentText("BigPicture演示示例");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.e));
        NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
        style.setBigContentTitle("BigContentTitle");
        style.setSummaryText("SummaryText");
        style.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.a));
        builder.setStyle(style);
        builder.setAutoCancel(true);
        Intent intent = new Intent(this, ImageActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 1, intent, 0);
        //设置点击大图后跳转
        builder.setContentIntent(pIntent);
        Notification notification = builder.build();
        notificationManager.notify(TYPE_BigPicture, notification);
    }

    private void inBoxStyle() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("InboxStyle");
        builder.setContentText("InboxStyle演示示例");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.d));
        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
        style.setBigContentTitle("inBoxStyle Title")
                .addLine("第一行，第一行，第一行，第一行，第一行，第一行，第一行")
                .addLine("第二行")
                .addLine("第三行")
                .addLine("第四行")
                .addLine("第五行")
                .setSummaryText("SummaryText");
        builder.setStyle(style);
        builder.setAutoCancel(true);
        Intent intent = new Intent(this, NotifyInfoActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 1, intent, 0);
        builder.setContentIntent(pIntent);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        Notification notification = builder.build();
        notificationManager.notify(TYPE_INBOX, notification);
    }

    /**
     * 有点手机无法显示
     */
    private void bitTextStyle() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("BigText");
        builder.setContentText("BigText内容");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.c));
        NotificationCompat.BigTextStyle bigStyle = new NotificationCompat.BigTextStyle();
        bigStyle.setBigContentTitle("BigStyle标题");
        bigStyle.setSummaryText("省略文字");
        bigStyle.bigText("许多文字许多文字许多文字许多文字许多文字许多文字许多文字许多文字许多文字许多文字许多文字许多文字许多文字许多文字许多文字许多文字许多文字许多文字许多文字许多文字许多文字许多文字许多文字许多文字许多文字许多文字许多文字许多文字许多文字许多文字许多文字许多文字许多文字");
        builder.setStyle(bigStyle);
        builder.setAutoCancel(true);
        Intent intent = new Intent(this, NotifyInfoActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 1, intent, 0);
        builder.setContentIntent(pIntent);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        Notification notification = builder.build();
        notificationManager.notify(TYPE_BIGTEXT, notification);

    }

    /**
     * 普通通知栏
     * <p>
     * setVibrate设置震动 参数是个long[]｛震动时长，间隔时长，震动时长，间隔时长…｝单位毫秒 设置提醒声音
     * setSound(Uri sound) 一般默认的就好
     * builder.setLights()设置呼吸灯的颜色 并不是所有颜色都被支持 个人感觉没什么用
     */
    private void simpleNotification() {
        //为了版本兼容使用 v7下的NotificationCompat
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        //Ticker提示,setTicker 通知到来时低版本上会在系统状态栏显示一小段时间 5.0以上版本好像没有用了
        builder.setTicker("Simple Notification");
        //通知栏标题
        builder.setContentTitle("标题");
        //通知栏内容
        builder.setContentText("通知内容");
        //内容摘要，低版本手机不一定显示
        builder.setSubText("通知的内容摘要");
        //在通知栏的右侧，显示其他信息
        builder.setContentInfo("其他信息");
        //其他信息数量，如果设置ContentInfo 则Number会被隐藏
        builder.setNumber(2);
        //点击通知栏删除
        builder.setAutoCancel(true);
        //通知栏小图标
        builder.setSmallIcon(R.mipmap.ic_launcher);
        //下拉显示大图标
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), a));
        //跳转
        Intent intent = new Intent(NotificationActivity.this, NotifyInfoActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        //通知默认声音，震动，呼吸灯
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        Notification notificaition = builder.build();
        notificationManager.notify(TYPE_NORMAL, notificaition);
    }


    @Override
    protected void onDestroy() {
        if (serviceIntent != null) {
            stopService(serviceIntent);
        }
        super.onDestroy();
    }
}
