package com.example.hanaiabeauty.notifecatoin;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class OreoAndAboveNoti extends ContextWrapper {

    private static final String ID = "some_id";
    private static final String NAME = "some_id";
    private NotificationManager notificationManager;

    public OreoAndAboveNoti(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {

            creatChanl1();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void creatChanl1() {

        NotificationChannel androidChannel = new NotificationChannel(ID,
                NAME, NotificationManager.IMPORTANCE_DEFAULT);

        androidChannel.enableLights(true);

        androidChannel.enableVibration(true);

        androidChannel.setLightColor(Color.BLUE);

        androidChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(androidChannel);
    }

    private NotificationManager getManager() {
        if (notificationManager == null) {
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notificationManager;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private Notification.Builder getNotifecation(String title, String body, PendingIntent pendingIntent, Uri urisoud, String icon) {


        return new Notification.Builder(getApplicationContext(), ID)
                .setSmallIcon(Integer.parseInt(icon))
                .setContentTitle(title)
                .setContentText(body)
                .setSound(urisoud)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

    }


}



