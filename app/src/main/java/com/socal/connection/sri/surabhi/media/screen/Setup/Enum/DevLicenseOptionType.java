package com.socal.connection.sri.surabhi.media.screen.Setup.Enum;

import android.app.Activity;
import android.content.Context;

import com.demo.com.single.activity.sample.R;

public enum DevLicenseOptionType {
    LOCAL(0, R.string.local_license_tag),
    SITE(1, R.string.site_license_tag);

    private int _value;
    private int _id;

    private DevLicenseOptionType(int value, int id) {
        this._value = value;
        this._id = id;
    }

    public static String[] getList(Activity activity) {
        return new String[]{LOCAL.getName(activity), SITE.getName(activity)};
    }

    public static DevLicenseOptionType getType(int value) {
        switch (value) {
            case 0:
                return LOCAL;

            case 1:
                return SITE;

            default:
                throw new IllegalArgumentException(value + " is not a valid Pixel  file type ");
        }
    }

    public static DevLicenseOptionType getType(Context context, String value) {
        if (value.equals(context.getString(R.string.local_license_tag))) {
            return LOCAL;
        } else if (value.equals(context.getString(R.string.site_license_tag))) {
            return SITE;
        } else
            return LOCAL;
    }

    public int getValue() {
        return this._value;
    }

    public String getName(Context context) {
        return context.getString(this._id);
    }
}
