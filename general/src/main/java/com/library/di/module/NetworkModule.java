package com.library.di.module;

import android.app.Application;

import com.library.api.API;
import com.library.api.ApiConfig;
import com.library.util.common.Consts;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by agile-01 on 6/9/2017.
 */
@Module
public class NetworkModule {

    @Provides
    @Singleton
    API provideAPI(Retrofit retrofit) {
        return retrofit.create(API.class);
    }

    @Provides
    @Singleton
    OkHttpClient.Builder provideHttpClientBuilder(Application application) {
        File cacheDir = new File(application.getCacheDir(), "okhttp_cache");
        Cache cache = new Cache(cacheDir, ApiConfig.MAX_HTTP_CACHE_SIZE);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(ApiConfig.Timeouts.CONNECT, TimeUnit.SECONDS)
                .readTimeout(ApiConfig.Timeouts.READ, TimeUnit.SECONDS)
                .writeTimeout(ApiConfig.Timeouts.WRITE, TimeUnit.SECONDS)
                //.addInterceptor(new HeaderInterceptor()) //add headers in HeaderInterceptor if needed and uncomment this. It will pass headers along with all outgoing requests
                .cache(cache);

        if (Consts.IS_DEBUGGABLE) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        return builder;
    }

    @Provides
    @Singleton
    Retrofit.Builder provideRetrofitBuilder(OkHttpClient okHttpClient, Converter.Factory converterFactory, CallAdapter.Factory adapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(ApiConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(adapterFactory);
    }

    @Provides
    @Singleton
    OkHttpClient provideHttpClient(OkHttpClient.Builder builder) {
        return builder.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Retrofit.Builder builder) {
        return builder.build();
    }

    @Provides
    @Singleton
    Converter.Factory provideConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    CallAdapter.Factory provideCalLAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }

}
