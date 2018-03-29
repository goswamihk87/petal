package com.library;

import android.app.Application;

import com.library.di.component.AppComponent;
import com.library.di.component.DaggerAppComponent;
import com.library.di.module.AppModule;
import com.library.di.module.NetworkModule;
import com.library.util.common.Consts;
import com.library.util.common.CrashReportingTree;

import timber.log.Timber;

/**
 * Created by agile-01 on 5/17/2017.
 * <p>
 * copy everything from here to your library application class
 */

public class General extends Application {

    private static General sInstance;
    private AppComponent mAppComponent;

    public static General getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        //if app is in debug mode, planting simple logging tree
        //otherwise planting tree for logging errors to crash reporting tools
        if (Consts.IS_DEBUGGABLE) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }

        //injecting dependencies
        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
        mAppComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
