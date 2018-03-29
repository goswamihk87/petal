package com.library.api;


/**
 * Created by agile-01 on 10/17/2016.
 * <p>
 * configuration class for api
 * <p>
 * all API related config things like appending urls,headers,params and response codes must be put here in corresponding interfaces
 */
public class ApiConfig {

    //base server url
    public static final String BASE_URL = "";

    //Response cache size for OkHTTP - 10 MB
    public static final long MAX_HTTP_CACHE_SIZE = 10 * 1024 * 1024;

    /**
     * all url paths appending to {@link #BASE_URL}
     */
    public interface EndPoints {

    }

    /**
     * header key/values
     *
     * @see HeaderInterceptor
     */
    public interface Headers {

    }

    /**
     * parameters
     */
    public interface Params {

    }

    /**
     * all api specific response codes given from server side
     */
    public interface ResponseCodes {
        int SUCCESS = 200;
    }

    /**
     * network timeouts - in seconds
     */
    public interface Timeouts {
        int CONNECT = 15;
        int READ = 30;
        int WRITE = 30;
    }
}
