package com.example.serviceexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import com.example.serviceexample.services.MyIntentService;
import com.example.serviceexample.services.MyService;

public class SecondActivity extends AppCompatActivity {
    private MyService monService = null;
    private Button btn;
    private ServiceConnection connexion = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            monService = ((MyService.MyBinder)binder).getService();
            btn.setText(String.valueOf(monService.getCpt()));
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            monService = null;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MyService.class);
        bindService(intent,connexion, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(monService != null) {
            unbindService(connexion);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btn = findViewById(R.id.txtval);
    }

    public void clickButton(View view) {
        if (monService != null){
            btn.setText(String.valueOf(monService.inccCpt()));
        }

        Intent intent = new Intent(this, MyIntentService.class);
        startService(intent);
    }
}
