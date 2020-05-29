package com.formationandroid.starwarsrandomplanet.modules;

import android.app.Application;
import android.content.Context;

import com.formationandroid.starwarsrandomplanet.di.MainComponent;
import com.formationandroid.starwarsrandomplanet.model.dao.ExempleDAO;
import com.formationandroid.starwarsrandomplanet.repository.MainRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(subcomponents = MainComponent.class)
public class AppModule {
    @Provides
    static Context provideContext(Application application) {
        return application;
    }

    @Singleton
    @Provides
    static ExempleDAO provideExempleDAO() {
        return new ExempleDAO();
    }
}
