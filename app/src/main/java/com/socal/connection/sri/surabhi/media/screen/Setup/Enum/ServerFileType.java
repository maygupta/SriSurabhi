package com.socal.connection.sri.surabhi.media.screen.Setup.Enum;

import android.app.Activity;
import android.content.Context;

import com.demo.com.single.activity.sample.R;

public enum ServerFileType {

    FTP_SERVER(0, R.string.ftp_server_tag),
    FILE_SERVER(1, R.string.file_server_tag),
    DEVICE_SERVER(2, R.string.device_server_tag),
    GOOGLE_DRIVE_SERVER(3, R.string.google_drive_server_tag);

    private int _value;
    private int _id;

    private ServerFileType(int value, int id) {
        this._value = value;
        this._id = id;
    }

    public static ServerFileType getType(int value) {
        switch (value) {
            case 0:
                return FTP_SERVER;

            case 1:

                return FILE_SERVER;

            case 2:

                return DEVICE_SERVER;

            case 3:

                return GOOGLE_DRIVE_SERVER;

            default:
                throw new IllegalArgumentException(value + " is not a valid Pixel  file type ");
        }
    }

    public static ServerFileType getType(Context context, String value) {
        if (value.equals(context.getString(R.string.ftp_server_tag))) {
            return FTP_SERVER;
        } else if (value.equals(context.getString(R.string.file_server_tag))) {
            return FILE_SERVER;
        } else if (value.equals(context.getString(R.string.device_server_tag))) {
            return DEVICE_SERVER;
        } else if (value.equals(context.getString(R.string.google_drive_server_tag))) {
            return GOOGLE_DRIVE_SERVER;
        } else
            return GOOGLE_DRIVE_SERVER;
    }

    public static String[] getList(Activity activity, int license) {
        if (license == LicenseFileType.FREE.getValue()) {

            return new String[]{GOOGLE_DRIVE_SERVER.getName(activity)};
        } else if (license == LicenseFileType.LITE.getValue()) {

            return new String[]{GOOGLE_DRIVE_SERVER.getName(activity)};
        } else if (license == LicenseFileType.STANDARD.getValue()) {

            return new String[]{FILE_SERVER.getName(activity), GOOGLE_DRIVE_SERVER.getName(activity)};
        } else if (license == LicenseFileType.ENTERPRISE.getValue()) {

            return new String[]{FTP_SERVER.getName(activity), FILE_SERVER.getName(activity), DEVICE_SERVER.getName(activity),
                    GOOGLE_DRIVE_SERVER.getName(activity)};
        } else {
            return new String[]{GOOGLE_DRIVE_SERVER.getName(activity)};
        }
    }

    public int getValue() {
        return this._value;
    }

    public String getName(Context context) {
        return context.getString(this._id);
    }
}
