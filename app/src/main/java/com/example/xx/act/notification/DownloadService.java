package com.example.xx.act.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.example.xx.R;

import static com.example.xx.R.drawable.b;

public class DownloadService extends Service {
    private Handler handler;
    private int progress = 0;
    private NotificationManager notificationManager;
    private Runnable runnable;
    private Notification notifcation;
    private NotificationCompat.Builder builder;

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler(getMainLooper());
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        runnable = new Runnable() {
            @Override
            public void run() {
                if (progress > 99) {
                    progress = 0;
                    notificationManager.cancel(NotificationActivity.TYPE_PROGRESS);
                } else {
                    sendNotification(progress);
                    progress++;
                    handler.postDelayed(runnable, 100);
                }
            }
        };
    }

    private void sendNotification(int progress) {
        if (builder == null) {
            builder = new NotificationCompat.Builder(this);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), b));
            builder.setAutoCancel(false);
            builder.setOngoing(true); //禁止滑动删除
            builder.setShowWhen(false);
            builder.setProgress(100, progress, false);
            Intent intent = new Intent(this, DownloadService.class);
            intent.putExtra("command", 1);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
            builder.setContentIntent(pendingIntent);
        }
        builder.setContentTitle("下载中..." + progress + "%");
        builder.setProgress(100, progress, false);
        notifcation = builder.build();
        notificationManager.notify(NotificationActivity.TYPE_PROGRESS, notifcation);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            return super.onStartCommand(intent, flags, startId);
        }
        int command = intent.getIntExtra("command", 0);
        if (command == 1) {
            progress = 0;
            handler.removeCallbacks(runnable);
            notificationManager.cancel(NotificationActivity.TYPE_PROGRESS);
        } else {
            if (progress < 1) {
                handler.post(runnable);
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(runnable);
        notificationManager.cancel(NotificationActivity.TYPE_PROGRESS);
        super.onDestroy();
    }
}
