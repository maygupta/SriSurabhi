//package com.socal.connection.sri.surabhi.media.screen.Setup.Samba;
//
//import android.app.Activity;
//import android.os.AsyncTask;
//
//
//import com.demo.com.single.activity.sample.R;
//import com.socal.connection.sri.surabhi.media.screen.Setup.Utils.SetupFile;
//import com.socal.connection.sri.surabhi.media.utils.Common;
//import com.socal.connection.sri.surabhi.media.utils.CommonUtil;
//import com.socal.connection.sri.surabhi.media.utils.License.SiteLicenceCommon;
//import com.socal.connection.sri.surabhi.media.utils.SharedStaticObjects;
//
//import jcifs.smb.NtlmPasswordAuthentication;
//import jcifs.smb.SmbFile;
//
//import static com.socal.connection.sri.surabhi.media.utils.SharedStaticObjects.SMB_TAG;
//
//
///**
// * Created by logix028 on 15/03/17.
// */
//
//public class TestSambaConnection extends AsyncTask<String, Void, Boolean> {
//    private Boolean _testLicence;
//    private Activity activity;
//    SetupFile signItSetup = null;
//    String _errorText = "";
//
//    public TestSambaConnection(Activity activity, SetupFile signItSetup) {
//        this.activity = activity;
////        this._testLicence = signItSetup.getDoCheckLicenceOnSync();
//        this.activity = activity;
//        this.signItSetup = signItSetup;
//    }
//
//    @Override
//    protected Boolean doInBackground(String... params) {
//        boolean isLicenseActive = false;
//
//        String test = activity.getString(R.string.setup_connect_to_server);
//        String endLine = System.getProperty(Common.LINE_SEPARATOR);
//        // Connect to Server
//        String fileserver = params[0];
//        String username = params[1];
//        String password = params[2];
//        String ftpPath = params[3];
//        String deviceName = params[4];
//        String sambaSetupPath = SMB_TAG + fileserver + ftpPath + SharedStaticObjects.SetupFolderName;
//        String sambaPath = SMB_TAG + fileserver + ftpPath;
//        try {
//            SmbFile[] domains;
//            final String user = username + ":" + password;
//            final NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(user);
//            final SmbFile sFile;
//            sFile = new SmbFile(sambaPath, auth);
//            test += " passed " + endLine + activity.getString(R.string.setup_log_on_to_server);
//            domains = sFile.listFiles();
//            test += " passed " + endLine + "Find " + activity.getString(R.string.setup_server_path_label);
//            if (domains.length <= 0) {
//                _errorText = test;
//                return false;
//            }
//
//            try {
//                if (this._testLicence) {
//                    isLicenseActive = SiteLicenceCommon.ReadFileSambaLicenseFile(signItSetup);
//                } else {
//                    isLicenseActive = SiteLicenceCommon.checkLocalLicence(activity);
//                }
//                if (!isLicenseActive) {
//                    _errorText = "Licence is not verify, so please contact with Admin or Try again!";
//                    return false;
//                }
//            } catch (Exception e) {
//                _errorText = "Licence is not verify, so please contact with Admin or Try again!";
//                return false;
//            }
//            return true;
//        } catch (Exception e) {
//            _errorText = test + " failed. \r\n\r\nError: " + e.toString();
//            return false;
//        }
//    }
//
//    @Override
//    protected void onPostExecute(Boolean result) {
//        CommonUtil.progressDismiss();
//        if (result) {
//            CommonUtil.showBasicAlert(activity, activity.getString(R.string.setup_test_connection_dot),
//                    activity.getString(R.string.setup_server_has_been_connected_successfully));
//        } else {
//            CommonUtil.showBasicAlert(activity, activity.getString(R.string.setup_test_connection_dot), _errorText);
//        }
//    }
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//        CommonUtil.progressShow(activity, activity.getString(R.string.setup_test));
//    }
//
//}