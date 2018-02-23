package com.socal.connection.sri.surabhi.media.screen.Setup.Enum;

import android.app.Activity;
import android.content.Context;

import com.demo.com.single.activity.sample.R;

public enum LicenseFileType {
    FREE(0, R.string.lic_free_tag),
    LITE(1, R.string.lic_lite_tag),
    STANDARD(2, R.string.lic_standard_tag),
    ENTERPRISE(3, R.string.lic_enterprise_tag);

    private int _value;
    private int _id;

    private LicenseFileType(int value, int id) {
        this._value = value;
        this._id = id;
    }

    public static LicenseFileType getType(int value) {
        switch (value) {
            case 0:
                return FREE;

            case 1:

                return LITE;

            case 2:

                return STANDARD;

            case 3:

                return ENTERPRISE;

            default:
                throw new IllegalArgumentException(value + " is not a valid Pixel  file type ");
        }
    }

    public static LicenseFileType getType(Context context, String value) {
        if (value.equals(context.getString(R.string.lic_free_tag))) {
            return FREE;
        } else if (value.equals(context.getString(R.string.lic_lite_tag))) {
            return LITE;
        } else if (value.equals(context.getString(R.string.lic_standard_tag))) {
            return STANDARD;
        } else if (value.equals(context.getString(R.string.lic_enterprise_tag))) {
            return ENTERPRISE;
        } else
            return FREE;
    }

    public static String[] getList(Activity activity, int license) {
        if (license == DevLicenseOptionType.LOCAL.getValue()) {
            return new String[]{FREE.getName(activity), LITE.getName(activity), STANDARD.getName(activity), ENTERPRISE.getName(activity)};
        } else {
            return new String[]{ENTERPRISE.getName(activity)};
        }
    }

    public int getValue() {
        return this._value;
    }

    public String getName(Context context) {
        return context.getString(this._id);
    }
}
