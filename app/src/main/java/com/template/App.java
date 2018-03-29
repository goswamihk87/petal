package com.template;

import com.library.General;
import com.library.util.common.Consts;
import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;

/**
 * Created on 8/8/2017.
 * application class. change the name to get started.
 */

public class App extends General {

    @Override
    public void onCreate() {
        super.onCreate();
        //using Timber for logging from now.
        //tip : go to settings -> live templates, copy logging templates like logd,logm etc. and change them with abbreviations for Timber.
        Timber.d("onCreate() called");

        //Note : leak canary not working in library application class.
        //installing leak canary in debuggable builds
        if (Consts.IS_DEBUGGABLE && !LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this);
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Timber.w("onLowMemory() called");
    }
}
