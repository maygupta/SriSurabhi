package com.socal.connection.sri.surabhi.media.screen.Setup.Enum;

import android.app.Activity;
import android.content.Context;

import com.demo.com.single.activity.sample.R;

public enum FTPServerFileType {
    FTP(0, R.string.ftp_tag), FTPS(1, R.string.ftps_tag), SFTP(2, R.string.sftp_tag);

    private int _value;
    private int _serverTypeId;

    private FTPServerFileType(int value, int serverType) {
        this._value = value;
        this._serverTypeId = serverType;
    }

    public static String[] getList(Activity activity) {
        return new String[]{FTP.getName(activity), FTPS.getName(activity), SFTP.getName(activity)};
    }

    public static FTPServerFileType getType(int value) {
        switch (value) {
            case 0:
                return FTP;

            case 1:

                return FTPS;
            case 2:

                return SFTP;

            default:
                throw new IllegalArgumentException(value + " is not a valid signature file type ");
        }
    }

    public static FTPServerFileType getType(Context context, String value) {
        if (value.equals(context.getString(R.string.both_tag))) {
            return FTP;
        } else if (value.equals(context.getString(R.string.landscape_tag))) {
            return FTPS;
        } else if (value.equals(context.getString(R.string.portrait_tag))) {
            return SFTP;
        } else
            return FTP;
    }

    public int getValue() {
        return this._value;
    }

    public String getName(Context context) {
        return context.getString(this._serverTypeId);
    }
}
