package com.library.util.common;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.util.ArrayMap;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * cache for generating typeface from assets
 */
@Singleton
public class FontCache {

    private final Map<String, Typeface> mMapOfTypefaces;
    private final Context mContext;

    @Inject
    FontCache(Context context) {
        //no direct instances allowed. use di instead.
        mContext = context;
        //array map is efficient than hash map
        mMapOfTypefaces = new ArrayMap<>();
    }

    /**
     * generates typeface from font file name given - only if it does not exist in cache.
     * <p>
     * font file must be in assets folder. otherwise specify foll path under assets ex. 'fonts/xyz.ttf'
     */
    public Typeface getTypeface(String fontName) {
        //using app context to avoid leaks
        Typeface typeface = mMapOfTypefaces.get(fontName);
        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(mContext.getAssets(), fontName);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            mMapOfTypefaces.put(fontName, typeface);
        }
        return typeface;
    }
}