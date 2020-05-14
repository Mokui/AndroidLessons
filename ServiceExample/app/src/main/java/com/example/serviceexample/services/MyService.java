package com.example.serviceexample.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private static final String TAG = "tag";
    private IBinder binder = new MyBinder();
    private int secondCpt;

    public class MyBinder extends Binder{
        public MyService getService() {
            return MyService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"My Service started");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public int inccCpt(){
        secondCpt++;
        return secondCpt;
    }

    public int getCpt(){
        return secondCpt;
    }
}
