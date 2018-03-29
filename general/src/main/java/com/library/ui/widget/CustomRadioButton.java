package com.library.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;

import com.library.General;
import com.library.R;
import com.library.util.common.FontCache;

/**
 * Created by agile-01 on 5/7/2016.
 */
public class CustomRadioButton extends AppCompatRadioButton {

    private final FontCache mFontCache = General.getInstance().getAppComponent().provideFontCache();


    public CustomRadioButton(Context context) {
        super(context);
        if (!isInEditMode())
            init(null);
    }

    public CustomRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode())
            init(attrs);
    }

    public CustomRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode())
            init(attrs);
    }

    private void init(AttributeSet attributeSet) {
        if (attributeSet == null) return;
        TypedArray attributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.CustomRadioButton);
        String fontName = attributes.getString(R.styleable.CustomRadioButton_fontName);
        if (fontName != null && !isInEditMode()) {
            Typeface typeface = mFontCache.getTypeface(fontName);
            setTypeface(typeface);
        }
        attributes.recycle();
    }
}
