package com.library.api;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by agile-01 on 6/10/2017.
 * <p>
 * {@link Interceptor} for api headers.
 * It will pass these headers to all outgoing requests automatically
 */

public class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Request.Builder builder = chain.request().newBuilder();

        //put your headers key values here. for ex:
        //builder.header(ApiConfig.HeaderInterceptor.HEADER_KEY, headerValue);

        return chain.proceed(builder.build());
    }

}

