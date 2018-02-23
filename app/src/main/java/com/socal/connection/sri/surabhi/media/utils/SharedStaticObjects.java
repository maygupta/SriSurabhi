package com.socal.connection.sri.surabhi.media.utils;

import android.os.Environment;

public class SharedStaticObjects {

    public static final String licence_key_text = "Sample Apps License";

    public static final String AppRootPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SampleApps/";

    public static final String SetupFolderName = "Setup/";

    public static final String AppSetupPath = AppRootPath + SetupFolderName;

    public static final String LicenseFileName = "license.ini";
    public static final String TempLicenseFileName = "Templicense.ini";

    public static final String LicenseFilePath = AppSetupPath + "license.ini";

    public static final String SetupFileName = "setup.json";

    public static String SetupFilePath = AppSetupPath + SetupFileName;

    public static final String StandloneFolderName = "Standlone";
    public static final String InputFolderName = "in";
    public static final String OutputFolderName = "out";
    public static final String ProcessedFolderName = "processed";

    public static final String LicensePaymentDetailsFileName = "LicensePaymentDetails.ini";
    public static final String LicensePaymentDetailsFileNameFilePath = AppSetupPath + LicensePaymentDetailsFileName;

    public static final String SMB_TAG = "smb://";
    public static final String AboutFileNameFree = "aboutScreen_free.html";
    public static final String AboutFileNameLite = "aboutScreen_lite.html";
    public static final String AboutFileNameStandard = "aboutScreen_standard.html";
    public static final String AboutFileNameEnterprise = "aboutScreen_enterprise.html";

    public static final String TempDatFileName = "/temp.dat";
    public static final String AndroidDrawablePath = "android.resource://com.package.AppName/res/drawable/";

}
