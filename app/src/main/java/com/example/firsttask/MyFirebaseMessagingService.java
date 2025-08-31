package com.example.firsttask;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {



    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
//        String title=message.getNotification().getTitle();
//        String rmessage= message.getNotification().getBody();
//
//        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,CHANNEL_ID);
//        builder.setSmallIcon(R.drawable.icon_notification);

    }
}
