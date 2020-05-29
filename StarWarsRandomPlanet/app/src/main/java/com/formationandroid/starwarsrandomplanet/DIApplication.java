package com.formationandroid.starwarsrandomplanet;


import android.app.Application;

import com.formationandroid.starwarsrandomplanet.di.AppComponent;
import com.formationandroid.starwarsrandomplanet.di.DaggerAppComponent;

public class DIApplication extends Application {

    //Attributes
    private static DIApplication instance;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        appComponent = DaggerAppComponent.builder().application(this).build();
    }

    public static AppComponent getAppComponent(){
        return instance.appComponent;
    }
}
