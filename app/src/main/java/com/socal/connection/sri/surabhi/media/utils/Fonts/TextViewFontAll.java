package com.socal.connection.sri.surabhi.media.utils.Fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.socal.connection.sri.surabhi.media.utils.SharedPref;

import java.util.HashMap;

public class TextViewFontAll extends TextView {
    int textViewSize = 11;
    int textSize = 11;

    public TextViewFontAll(Context context) {
        super(context);
        applyCustomFont(context);
        setTextSize(fontChange(context));

    }

    public TextViewFontAll(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
        setTextSize(fontChange(context));

    }

    public TextViewFontAll(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        applyCustomFont(context);
        setTextSize(fontChange(context));
    }

    private int fontChange(Context context) {

        textViewSize = SharedPref.getInt(context, "TextSize_DBLive");
        if (textViewSize == 15) {
            textSize = textViewSize;
        } else if (textViewSize == 20) {
            textSize = textViewSize;
        } else if (textViewSize == 25) {
            textSize = textViewSize;
        } else if (textViewSize == 0) {
            textSize = 15;
        }

        return textSize;
    }

    private void applyCustomFont(Context context) {

        Typeface customFont = FontCache.getTypeface("fonts/Roboto-Regular.ttf", context);

        setTypeface(customFont);
    }
}

class FontCache {
    private static HashMap<String, Typeface> fontCache = new HashMap<>();

    @Nullable
    public static Typeface getTypeface(String fontname, Context context) {
        Typeface typeface = fontCache.get(fontname);
        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(), fontname);
            } catch (Exception e) {
                return null;
            }

            fontCache.put(fontname, typeface);
        }
        return typeface;
    }

}
