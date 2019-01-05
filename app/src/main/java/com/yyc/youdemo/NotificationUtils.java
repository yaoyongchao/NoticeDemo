package com.yyc.youdemo;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
 
/**
 * Created by LaoZhao on 2017/11/19.
 */
 
public class NotificationUtils extends ContextWrapper {
 
    private NotificationManager manager;
    public static final String id = "weicai";
    public static final String name = "weicai_caimin";
    private Context context;
 
    public NotificationUtils(Context context){
        super(context);
        this.context = context;
    }
 
    @SuppressLint("NewApi")
    public void createNotificationChannel(){
        @SuppressLint({"NewApi", "LocalSuppress"}) NotificationChannel channel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
        getManager().createNotificationChannel(channel);
    }
    private NotificationManager getManager(){
        if (manager == null){
            manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }
        return manager;
    }
    @SuppressLint("NewApi")
    public Notification.Builder getChannelNotification(String title, String content){
        Intent intent =new Intent (context,NotificationClickReceiver.class);
        PendingIntent pendingIntent =PendingIntent.getBroadcast(context, 0, intent, 0);
//        builder1.setContentIntent(pendingIntent);
        return new Notification.Builder(getApplicationContext(), id)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL) //设置默认的提示音，振动方式，灯光
                .setContentIntent(pendingIntent);

    }
    public NotificationCompat.Builder getNotification_25(String title, String content){
        Intent intent =new Intent (context,NotificationClickReceiver.class);
        PendingIntent pendingIntent =PendingIntent.getBroadcast(context, 0, intent, 0);
        return new NotificationCompat.Builder(getApplicationContext())
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL) //设置默认的提示音，振动方式，灯光
                .setContentIntent(pendingIntent);
    }
    public void sendNotification(String title, String content){
//        Intent intent =new Intent (context,NotificationClickReceiver.class);
//        PendingIntent pendingIntent =PendingIntent.getBroadcast(context, 0, intent, 0);
////        builder1.setContentIntent(pendingIntent);
//        Notification notification1 = builder1.build();
//        notificationManager.notify(124, notification1); // 通过通知管理器发送通知
        if (Build.VERSION.SDK_INT>=26){

            createNotificationChannel();
            Notification.Builder builder = getChannelNotification
                    (title, content);
            Notification notification = builder.build();

            getManager().notify(1,notification);
        }else{
            Notification notification = getNotification_25(title, content).build();
            getManager().notify(1,notification);
        }
    }
}