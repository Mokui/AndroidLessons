package com.formationandroid.starwarsrandomplanet.di;

import android.app.Application;
import com.formationandroid.starwarsrandomplanet.modules.AppModule;
import com.formationandroid.starwarsrandomplanet.repository.MainRepository;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    //Sous composant:
    MainComponent.Factory mainComponent();

    void inject(MainRepository mainRepository);
}
