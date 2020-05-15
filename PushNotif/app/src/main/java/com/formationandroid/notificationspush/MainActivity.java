package com.formationandroid.notificationspush;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import cz.msebera.android.httpclient.Header;

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
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "montag";
    public static final String INTENT_FILTER = "com.formationandroid.notificationspush.SUPER_INTENT";

    private TextView myMsg;
    private EditText msgToSend;
    private MyBroadcastReceiver broadcastReceiver;

    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
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
        String toSend = msgToSend.getText().toString();
        // client HTTP :
        AsyncHttpClient client = new AsyncHttpClient();
        // paramètres :
        RequestParams requestParams = new RequestParams();
        requestParams.put("message", toSend);
        requestParams.put("tokenPush", "fzPHHL5UQqWlakzNiNSBhq:APA91bGP1FB2_XZqp-5Vg7q2lCQgbOArdY4qug_AY7k48p2BBdr8V6j5iA9dtBPio_Q-eL3TUy5h5JLPx8MPiZ_gxxEHU3HObIfnnx2OAB0xFELAgiJK7WlmKpSgkXxwz8mZtAs5uzmh");
        // appel :
        client.post("http://s519716619.onlinehome.fr/exercices/push/envoyer_message_expediteur.php", requestParams, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers,
                                          byte[] response) {
                        String retour = new String(response);
                        Log.i(TAG, retour);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers,
                                          byte[] errorResponse, Throwable e) {
                        Log.e(TAG, e.toString());
                    }
                });

        msgToSend.setText("");
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
