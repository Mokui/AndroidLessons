package com.formationandroid.notificationspush;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "montag";
    private static final String INTENT_FILTER = "com.formationandroid.notificationspush.SUPER_INTENT";

    private TextView myMsg;
    private EditText msgToSend;
    private MyBroadcastReceiver broadcastReceiver;

    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG,"INCROYABLE");
            myMsg.setText(intent.getStringExtra("mymsg"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myMsg = findViewById(R.id.mymsg);
        msgToSend = findViewById(R.id.msgtosend);

        broadcastReceiver = new MyBroadcastReceiver();
    }

    public void sendMessagetoWeb(View view) {
        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        intent.putExtra("mymsg", msgToSend.getText());
        intent.setAction(INTENT_FILTER);
        msgToSend.setText("");
        sendBroadcast(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseMessaging.getInstance().subscribeToTopic("discussion");
        registerReceiver(broadcastReceiver, new IntentFilter(INTENT_FILTER));
    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseMessaging.getInstance().unsubscribeFromTopic("discussion");
        unregisterReceiver(broadcastReceiver);
    }
}
