package com.socal.connection.sri.surabhi.media.utils.License;


import android.content.Context;

import com.socal.connection.sri.surabhi.media.controller.SriSurabhiApp;
import com.socal.connection.sri.surabhi.media.utils.SharedStaticObjects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static com.socal.connection.sri.surabhi.media.utils.Common.DFI_DEC_KAY;
import static com.socal.connection.sri.surabhi.media.utils.Common.DFI_INC_KAY;

public class SignItLicenseFile {
    private Boolean _isValid;
    private String _licenseValue;
    private Context context;

    private SignItLicenseFile() {
        this._licenseValue = null;
        this._isValid = false;
    }

    public SignItLicenseFile(Context context, String licenseValue) {
        this.context = context;
        this._licenseValue = licenseValue;
        this._isValid = this.checkLicenseValidity(context);
    }

    private Boolean checkLicenseValidity(Context context) {
        if (this._licenseValue == null || this._licenseValue.length() == 0)
            return false;

        return checkLicenseValidity(getDeviceInfo(context));
    }

//    public static String getLicenceType(String _licenseValue) {
//        try {
//            String[] licenceCode = SimpleCrypto.LicenseDecrypt(_licenseValue, DFI_DEC_KAY).split("_");
//            if (licenceCode.length > 2) {
//                if (licenceCode[1].equals(licenceTag[0])) {
//                    return SIGN_LITE;
//                } else if (licenceCode[1].equals(licenceTag[1])) {
//                    return SIGN_STANDARD;
//                } else if (licenceCode[1].equals(licenceTag[2])) {
//                    return SIGN_ENTERPRISE;
//                }
//            } else {
//                return SIGN_FREE;
//            }
//        } catch (SimpleCryptoException e) {
//            e.printStackTrace();
//            return null;
//        }
//        return SIGN_FREE;
//    }

    private Boolean checkLicenseValidity(String checkAnswer) {
        try {
            String code = SimpleCrypto.LicenseDecrypt(_licenseValue, DFI_DEC_KAY);
            String[] focus = code.split("_");
            String decodeddata;
            if (focus.length > 2) {
                decodeddata = focus[0].replace("_", "");
            } else {
                decodeddata = focus[0].replace("_", "");
            }
            if (checkAnswer.compareTo(decodeddata) != 0) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static String getDeviceInfo(Context context) {
        String infoString;
        String value;

        // Device model
        value = android.os.Build.MODEL;
        // Log.i("SampleApps ", " PhoneModel :: " + value);
        infoString = SharedStaticObjects.licence_key_text + value;

        // Removed to prevent relicencing on OS update
        // // Android version
        // value = android.os.Build.VERSION.RELEASE;
        // // Log.i("SampleApps ", " AndroidVersion :: " + value);
        // infoString += ", " + value;

        // Android_Id
        value = SriSurabhiApp.getAndroidId();
        // Log.i("SampleApps ", " ANDROID_ID :: " + value);
        infoString += ", " + value;

        // Installation Id
        value = Installation.id();
        // Log.i("SampleApps ", " InstallationID :: " + value);
        infoString += ", " + value;

        return infoString;
    }

    public static String getEncryptDeviceInfo(Context context)// throws Exception
    {
        String mydevice_info;
        String infoString;

        infoString = SignItLicenseFile.getDeviceInfo(context);

        try {
            mydevice_info = SimpleCrypto.LicenseEncrypt(infoString, DFI_INC_KAY);
        } catch (SimpleCryptoException e) {
            return null;
        }

        return mydevice_info;
    }

    public Boolean isValid() {
        return this._isValid;
    }

    public String getLicenseValue() {
        return this._licenseValue;
    }

    public static SignItLicenseFile ReadFile(Context context) {
        SignItLicenseFile returnValue;
        String fileLine = null;
        String licenseFileKey = "";
        BufferedReader streamReader;
        FileReader inputStream;
        File licenseFile = new File(SharedStaticObjects.LicenseFilePath);

        try {
            inputStream = new FileReader(licenseFile);

            streamReader = new BufferedReader(inputStream);
            try {
                while ((fileLine = streamReader.readLine()) != null) {
                    licenseFileKey += fileLine;
                }
            } finally {
                streamReader.close();
                inputStream.close();
            }
            returnValue = new SignItLicenseFile(context, licenseFileKey);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return returnValue;
    }

}
