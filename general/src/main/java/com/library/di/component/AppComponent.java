package com.library.di.component;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.library.General;
import com.library.api.ApiManager;
import com.library.di.module.AppModule;
import com.library.di.module.NetworkModule;
import com.library.di.module.StorageModule;
import com.library.util.common.FontCache;
import com.library.util.network.NetworkUtils;
import com.library.util.permission.PermissionUtils;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by agile-01 on 6/9/2017.
 * <p>
 * This component injects all singleton scoped instances.
 * It injects all instances in {@link Application#onCreate()}.
 * Includes all modules which are having all singleton instances.
 * <p>
 * If you need to provide injection for any in-library class which
 * then use constructor injection of that class
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, StorageModule.class})
public interface AppComponent {

    void inject(General general);

    //required dependencies
    Context provideContext();

    Gson provideGson();

    PermissionUtils providePermissionUtils();

    Bus provideBus();

    FontCache provideFontCache();

    NetworkUtils provideNetworkUtils();

    ApiManager provideApiManager();
}