package com.socal.connection.sri.surabhi.media.screen.Setup.Utils;

import com.socal.connection.sri.surabhi.media.screen.Setup.Enum.LicenseFileType;
import com.socal.connection.sri.surabhi.media.screen.Setup.Enum.ServerFileType;
import com.socal.connection.sri.surabhi.media.utils.Common;


/**
 * Created by logix028 on 10/05/17.
 */

public class SetupCommonUtils {

//    public static int getLicenseTypeIndex(String value, int index) {
//        if (value.equals(Common.LOCAL_LICENSE)) {
//            return index;
//        } else {
//            if (index > 0) {
//                return index;
//            } else {
//                return 1;
//            }
//        }
//    }

//    public static String[] getLicenseTypeArray(Activity activity, String value) {
//        String[] licenseTypeArray = activity.getResources().getStringArray(R.array.license_type_array);
//        if (value.equals(Common.LOCAL_LICENSE)) {
//            return licenseTypeArray;
//        } else if (value.endsWith(Common.SITE_LICENSE)) {
//            return new String[]{licenseTypeArray[1], licenseTypeArray[2], licenseTypeArray[3]};
//        }
//        return licenseTypeArray;
//    }

//    public static boolean getSiteLicenseButton(SetupFile signItSetup, String value) {
//        if (value.equals(Common.LOCAL_LICENSE)) {
//            return false;
//        } else {
//            if (signItSetup != null) {
//                if (signItSetup.getDoCheckLicenceOnSync()) {
//                    return true;
//                }
//            }
//            return false;
//        }
//    }

//    public static String[] getServerArray(Activity activity, String currentLicence) {
//        String[] serverTypeArray = activity.getResources().getStringArray(R.array.server_type_array);
//        if (currentLicence.endsWith(Common.SIGN_FREE)) {
//            return new String[]{serverTypeArray[3]};
//        } else if (currentLicence.endsWith(Common.SIGN_LITE)) {
//            return new String[]{serverTypeArray[3]};
//        } else if (currentLicence.endsWith(Common.SIGN_STANDARD)) {
//            return new String[]{serverTypeArray[1], serverTypeArray[3]};
//        } else if (currentLicence.endsWith(Common.SIGN_ENTERPRISE)) {
//            return serverTypeArray;
//        }
//        return new String[]{serverTypeArray[3]};
//    }

//    public static boolean isWaterMarkVisible(String currentLicence) {
//
//        if (currentLicence.endsWith(Common.SIGN_FREE)) {
//            return true;
//        } else {
//            return false;
//        }
//    }

//    public static boolean isChangeAdminButtonVisible(String currentLicence) {
//
//        if (currentLicence.endsWith(Common.SIGN_FREE)) {
//            return false;
//        } else if (currentLicence.endsWith(Common.SIGN_LITE)) {
//            return false;
//        } else if (currentLicence.endsWith(Common.SIGN_STANDARD)) {
//            return true;
//        } else if (currentLicence.endsWith(Common.SIGN_ENTERPRISE)) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    public static boolean isOrientationLevelVisible(int currentLicence) {

        if (currentLicence == LicenseFileType.FREE.getValue()) {
            return false;
        } else if (currentLicence == LicenseFileType.LITE.getValue()) {
            return false;
        } else if (currentLicence == LicenseFileType.STANDARD.getValue()) {
            return true;
        } else if (currentLicence == LicenseFileType.ENTERPRISE.getValue()) {
            return true;
        } else {
            return false;
        }
    }

//    public static boolean isFileTypeVisible(String currentLicence) {
//
//        if (currentLicence.endsWith(Common.SIGN_FREE)) {
//            return false;
//        } else if (currentLicence.endsWith(Common.SIGN_LITE)) {
//            return false;
//        } else if (currentLicence.endsWith(Common.SIGN_STANDARD)) {
//            return true;
//        } else if (currentLicence.endsWith(Common.SIGN_ENTERPRISE)) {
//            return true;
//        } else {
//            return false;
//        }
//    }

//    public static boolean isTagFileTypeVisible(String currentLicence) {
//
//        if (currentLicence.endsWith(Common.SIGN_FREE)) {
//            return false;
//        } else if (currentLicence.endsWith(Common.SIGN_LITE)) {
//            return false;
//        } else if (currentLicence.endsWith(Common.SIGN_STANDARD)) {
//            return true;
//        } else if (currentLicence.endsWith(Common.SIGN_ENTERPRISE)) {
//            return true;
//        } else {
//            return false;
//        }
//    }

//    public static boolean isProcessFolderToogleBtnVisible(String currentLicence) {
//
//        if (currentLicence.endsWith(Common.SIGN_FREE)) {
//            return false;
//        } else if (currentLicence.endsWith(Common.SIGN_LITE)) {
//            return false;
//        } else if (currentLicence.endsWith(Common.SIGN_STANDARD)) {
//            return false;
//        } else if (currentLicence.endsWith(Common.SIGN_ENTERPRISE)) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    public static boolean isSecurityFieldShow(int position) {
        if (position == ServerFileType.FTP_SERVER.getValue()) {
            return false;
        } else {
            return true;
        }
    }

//    public static boolean isSetupValueChack(int position) {
//        if (position == ServerFileType.FTP_SERVER.getValue()) {
//            return false;
//        } else {
//            return true;
//        }
//    }
}
