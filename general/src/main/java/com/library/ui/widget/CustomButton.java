package com.library.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.library.General;
import com.library.R;
import com.library.util.common.FontCache;


public class CustomButton extends AppCompatButton {

    private final FontCache mFontCache = General.getInstance().getAppComponent().provideFontCache();

    public CustomButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode())
            init(attrs);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode())
            init(attrs);
    }

    public CustomButton(Context context) {
        super(context);
        if (!isInEditMode())
            init(null);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray attributes = getContext().obtainStyledAttributes(attrs,
                    R.styleable.CustomButton);
            String fontName = attributes.getString(R.styleable.CustomButton_fontName);
            if (fontName != null) {
                Typeface typeface = mFontCache.getTypeface(fontName);
                setTypeface(typeface);
            }
            attributes.recycle();
        }
    }

}
