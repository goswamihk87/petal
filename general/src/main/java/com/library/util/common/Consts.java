package com.library.util.common;


import com.library.BuildConfig;

/**
 * Created by agile-01 on 6/10/2017.
 * <p>
 * put all constants here
 */
public class Consts {

    /**
     * {@link BuildConfig#DEBUG} does not work in library modules.
     * ref: https://stackoverflow.com/a/20617949/7809145
     * this flag is used as a replacement of it.
     * set it to false to disable logging.
     */
    public static final boolean IS_DEBUGGABLE = true;

    /**
     * all keys for shared preferences must be put here
     */
    public interface SharedPrefs {

    }

    /**
     * all keys for bundle/intent extras must be put here
     */
    public interface BundleExtras {

    }

    /**
     * all date time formats must be put here
     */
    public interface DateTimeFormats {

    }

    /**
     * all time delays must be put here
     */
    public interface Delays {
        long MIN_TIME_BETWEEN_CLICKS = 200; //in ms
    }
}