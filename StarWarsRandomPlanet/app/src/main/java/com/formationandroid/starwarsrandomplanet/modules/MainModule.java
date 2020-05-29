package com.formationandroid.starwarsrandomplanet.modules;

import com.formationandroid.starwarsrandomplanet.di.MainComponent;
import com.formationandroid.starwarsrandomplanet.repository.MainRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    static MainRepository provideMainRepository(){
        return new MainRepository();
    }
}
