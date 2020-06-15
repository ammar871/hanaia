package com.example.hanaiabeauty.notifecatoin;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;


import com.example.hanaiabeauty.R;
import com.example.hanaiabeauty.commen.Common;
import com.example.hanaiabeauty.spilash.MainActivity;
import com.example.hanaiabeauty.spilash.SpilashActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseServise extends FirebaseMessagingService {


    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);


        String tokenRefresh= FirebaseInstanceId.getInstance().getToken();
if (Common.currentUser !=null)
    UpdateToken(tokenRefresh);

    }

    private void UpdateToken(final String tokenRefresh) {







            FirebaseDatabase db= FirebaseDatabase.getInstance();
            DatabaseReference ref= db.getReference("Tokens");
            Token data=new Token(tokenRefresh,false);
            ref.child(Common.currentUser.getPhone()).setValue(data);

        }





    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        sendNotifcation(remoteMessage);
    }

    private void sendNotifcation(RemoteMessage remoteMessage) {

        RemoteMessage.Notification notification = remoteMessage.getNotification();
        Intent intent=new Intent(this, SpilashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        Uri defuletsounduri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder b = new NotificationCompat.Builder(this);

        b.setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Hearty365")
                .setAutoCancel(true)
                .setContentTitle(notification.getTitle())
                .setContentText(notification.getBody())
                .setSound(defuletsounduri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, b.build());
    }

}
