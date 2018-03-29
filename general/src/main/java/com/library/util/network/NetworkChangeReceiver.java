package com.library.util.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.library.di.scope.ViewScope;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * broadcast receiver for receiving change in internet connection
 */
@ViewScope
public class NetworkChangeReceiver extends BroadcastReceiver {

    private final Bus mBus;
    private final NetworkUtils mNetworkUtils;
    private boolean wasNetworkDisconnected;

    @Inject
    NetworkChangeReceiver(Bus bus, NetworkUtils networkUtils) {
        mBus = bus;
        mNetworkUtils = networkUtils;
        //no direct instances allowed. use di instead.
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Timber.v("onReceive() called with: intent = [" + intent + "]");
        boolean isNetworkAvailable = mNetworkUtils.isConnected();
        if (isNetworkAvailable) {
            if (wasNetworkDisconnected) {
                mBus.post(new NetworkChangeEvent(true));
            }
        } else {
            if (!wasNetworkDisconnected) {
                mBus.post(new NetworkChangeEvent(false));
            }
        }
        Timber.d("isNetworkAvailable? " + isNetworkAvailable);
        wasNetworkDisconnected = !isNetworkAvailable;
    }

}
