package com.example.reveil;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private MyBroadcastReceiver broadcastReceiver = null;
    private Button alarmBtn;
    private TimePicker picker;
    private Calendar calendar;

    public class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //Log.d("",calendar.getTime());
            Toast.makeText(context, "DATE: "+calendar.getTime(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmBtn = findViewById(R.id.alarmbtn);
        picker = findViewById(R.id.picker);

        //Broadcast Receiver
        broadcastReceiver = new MyBroadcastReceiver();
        calendar = Calendar.getInstance();
    }

    public void addWakeup(View view) {
        // date future :
        calendar.set(Calendar.HOUR_OF_DAY,picker.getHour());
        calendar.set(Calendar.MINUTE,picker.getMinute());

        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // alarme :
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if (manager != null) {
            manager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
        }
        Log.d("time",""+calendar.getTimeInMillis());
        Log.d("time2", ""+System.currentTimeMillis());
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(broadcastReceiver, new IntentFilter(Intent.ACTION_BATTERY_LOW));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }
}
