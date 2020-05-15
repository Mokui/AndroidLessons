package com.formationandroid.notificationspush;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import androidx.annotation.NonNull;

import static com.formationandroid.notificationspush.MainActivity.INTENT_FILTER;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private final String TAG = "montag";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        //Broadcast
        Intent intent = new Intent();
        intent.putExtra("mymsg", remoteMessage.getData().get("message"));
        intent.setAction(INTENT_FILTER);
        sendBroadcast(intent);

        Log.i(TAG, "Expéditeur : " + remoteMessage.getFrom());
        if (remoteMessage.getNotification() != null)
        {
            Log.i(TAG, "Titre : " + remoteMessage.getNotification().getTitle());
            Log.i(TAG, "Contenu : " + remoteMessage.getNotification().getBody());
        }
        Log.i(TAG, "Data : " + remoteMessage.getData());
        Log.i(TAG, "Timestamp : " + remoteMessage.getSentTime());
    }

    @Override
    public void onNewToken(@NonNull String topic) {
        super.onNewToken(topic);
        Log.i(TAG,topic);
        //METTRE LES SHAREDPREF ICI
    }
}
