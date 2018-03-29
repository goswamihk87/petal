package com.library.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.AttributeSet;

import com.library.General;
import com.library.R;
import com.library.util.common.FontCache;

/**
 * Created by agile-01 on 6/8/2017.
 */

public class CustomCheckBox extends AppCompatCheckBox {

    private final FontCache mFontCache = General.getInstance().getAppComponent().provideFontCache();

    public CustomCheckBox(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode())
            init(attrs);
    }

    public CustomCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode())
            init(attrs);

    }

    public CustomCheckBox(Context context) {
        super(context);
        if (!isInEditMode())
            init(null);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray attributes = getContext().obtainStyledAttributes(attrs,
                    R.styleable.CustomCheckBox);
            String fontName = attributes.getString(R.styleable.CustomCheckBox_fontName);
            if (fontName != null) {
                Typeface typeface = mFontCache.getTypeface(fontName);
                setTypeface(typeface);
            }
            attributes.recycle();
        }
    }
}
