package com.library.api;

import com.library.General;
import com.library.util.common.Consts;
import com.library.util.network.NetworkUtils;

import java.net.ConnectException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Response;
import retrofit2.HttpException;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by agile-01 on 6/21/2017.
 */
@Singleton
public class ApiManager {

    private final API mApi;
    private final NetworkUtils mNetworkUtils = General.getInstance().getAppComponent().provideNetworkUtils();

    private final Observable.Transformer<Object, Object> mSchedulersTransformer = observable ->
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());

    @Inject
    ApiManager(API api) {
        //do not instantiate directly. use di instead.
        mApi = api;
    }

    //region internal methods

    /**
     * creates observable on the top of given api observable.
     * it will manage most things about api like network checking and notifying errors.
     * <p>
     * use {@link rx.Observer#onError(Throwable)} to determine type of error.
     * If exception is instance of
     * <ul>
     * <li>{@link ConnectException} then error will be due to network issues.</li>
     * <li>{@link HttpException} then error will be from server.</li>
     * similar to when {@link Response#isSuccessful()} returns false in retrofit.
     * You can use {@link HttpException#code()} to get the http status code for error.
     * <li>For other exceptions, it will be similar to {@link okhttp3.Callback#onFailure} in retrofit.</li>
     * </ul>
     *
     * @param apiObservable observable for apis.
     * @param <T>           response type
     * @return observable on api
     */
    private <T> Observable<T> call(Observable<T> apiObservable) {
        return apiObservable
                //startWith will emit observable inside it before another emissions from source observable
                //defer will create fresh observable only when observer subscribes it
                .startWith(Observable.defer(() -> {
                    //before calling each api, network connection is checked.
                    if (!mNetworkUtils.isConnected()) {
                        //if network is not available, it will return error observable with ConnectException.
                        return Observable.error(new ConnectException("Device is not connected to network"));
                    } else {
                        //if it is available, it will return empty observable. Empty observable just emits onCompleted() immediately
                        return Observable.empty();
                    }
                }))
                .doOnNext(response -> {
                    //logging response on success
                    //you can change to to something else
                    //for example, if all your apis returns error codes in success, then you can throw custom exception here
                    if (Consts.IS_DEBUGGABLE) {
                        Timber.e("Response :\n" + response);
                    }
                })
                .doOnError(throwable -> {
                    //printing stack trace on error
                    if (Consts.IS_DEBUGGABLE) {
                        throwable.printStackTrace();
                    }
                });
    }

    /**
     * returns a transformer with applied schedulers to api observable
     * use it with {@link Observable#compose}
     * <p>
     * ref. : http://blog.danlew.net/2015/03/02/dont-break-the-chain/
     * <p>
     * this will subscribe observable on {@link Schedulers#io()}
     * and observe on {@link AndroidSchedulers#mainThread()}
     *
     * @param <T> response type
     * @return transformer that applies schedulers on observable
     * <p>
     */
    @SuppressWarnings("unchecked")
    public <T> Observable.Transformer<T, T> applySchedulers() {
        //type casting is necessary to reuse our transformer instance
        //see 'Reusing Transformers' in reference given for more info
        return (Observable.Transformer<T, T>) mSchedulersTransformer;
    }
    //endregion

    //region all api requests

    //endregion

}
