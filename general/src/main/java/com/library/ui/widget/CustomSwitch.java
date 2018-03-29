package com.library.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;

import com.library.General;
import com.library.R;
import com.library.util.common.FontCache;

/**
 * Created by agile-01 on 6/8/2017.
 */

public class CustomSwitch extends SwitchCompat {

    private final FontCache mFontCache = General.getInstance().getAppComponent().provideFontCache();


    public CustomSwitch(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode())
            init(attrs);
    }

    public CustomSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode())
            init(attrs);

    }

    public CustomSwitch(Context context) {
        super(context);
        if (!isInEditMode())
            init(null);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray attributes = getContext().obtainStyledAttributes(attrs,
                    R.styleable.CustomSwitch);
            String fontName = attributes.getString(R.styleable.CustomSwitch_fontName);
            if (fontName != null) {
                Typeface typeface = mFontCache.getTypeface(fontName);
                setTypeface(typeface);
            }
            attributes.recycle();
        }
    }
}
