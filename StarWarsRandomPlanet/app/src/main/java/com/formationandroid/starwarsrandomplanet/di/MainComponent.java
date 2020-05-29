package com.formationandroid.starwarsrandomplanet.di;

import android.app.Application;

import com.formationandroid.starwarsrandomplanet.activity.MainActivity;
import com.formationandroid.starwarsrandomplanet.modules.AppModule;
import com.formationandroid.starwarsrandomplanet.modules.MainModule;
import com.formationandroid.starwarsrandomplanet.repository.MainRepository;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Subcomponent;

@Subcomponent(modules = {MainModule.class})
public interface MainComponent {
    @Subcomponent.Factory
    interface Factory {
        MainComponent create();
    }

    void inject(MainActivity mainActivity);
}

