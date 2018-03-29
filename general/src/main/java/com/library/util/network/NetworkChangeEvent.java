package com.library.util.network;

/**
 * Created by agile-01 on 6/28/2016.
 * <p>
 * this event will be posted whenever change in internet connection received in {@link NetworkChangeReceiver}
 */
public class NetworkChangeEvent {

    private final boolean isNetworkAvailable;

    public NetworkChangeEvent(boolean isNetworkAvailable) {
        this.isNetworkAvailable = isNetworkAvailable;
    }

    public boolean isNetworkAvailable() {
        return isNetworkAvailable;
    }

    @Override
    public String toString() {
        return "NetworkChangeEvent{" +
                "isNetworkAvailable=" + isNetworkAvailable +
                '}';
    }
}