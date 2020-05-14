package com.example.serviceexample.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;


public class MyIntentService extends IntentService {


    private static final String TAG = "tag";
    private int cpt=0;

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            Thread.sleep(3000);
            cpt++;
            Log.i(TAG, "compteur = "+cpt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
