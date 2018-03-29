package com.library.di.module;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application provideApp() {
        return mApplication;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mApplication.getApplicationContext();
    }

    @Singleton
    @Provides
    Bus provideBus() {
        return new Bus();
    }

    @Singleton
    @Provides
    Gson provideGson() {
        return new Gson();
    }


}