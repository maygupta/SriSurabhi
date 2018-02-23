package com.socal.connection.sri.surabhi.media.screen.Setup.Enum;

import android.app.Activity;
import android.content.Context;

import com.demo.com.single.activity.sample.R;

public enum OrientationFileType {
    BOTH(0, R.string.both_tag),
    LANDSCAPE(1, R.string.landscape_tag),
    PORTRAIT(2, R.string.portrait_tag);

    private int _orientModeId;
    private int _value;

    private OrientationFileType(int value, int orentMode) {
        this._value = value;
        this._orientModeId = orentMode;
    }

    public static String[] getList(Activity activity) {
        return new String[]{BOTH.getName(activity), LANDSCAPE.getName(activity), PORTRAIT.getName(activity)};
    }

    public static OrientationFileType getType(int value) {
        switch (value) {
            case 0:
                return BOTH;

            case 1:

                return LANDSCAPE;
            case 2:

                return PORTRAIT;

            default:
                throw new IllegalArgumentException(value + " is not a valid orientation file type ");
        }
    }

    public static OrientationFileType getType(Context context, String value) {
        if (value.equals(context.getString(R.string.both_tag))) {
            return BOTH;
        } else if (value.equals(context.getString(R.string.landscape_tag))) {
            return LANDSCAPE;
        } else if (value.equals(context.getString(R.string.portrait_tag))) {
            return PORTRAIT;
        } else
            return BOTH;
    }

    public int getValue() {
        return this._value;
    }

    public String getName(Context context) {
        return context.getString(this._orientModeId);
    }


}
