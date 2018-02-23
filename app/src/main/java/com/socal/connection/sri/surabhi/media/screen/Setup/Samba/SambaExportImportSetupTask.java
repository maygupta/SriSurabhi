//package com.socal.connection.sri.surabhi.media.screen.Setup.Samba;
//
//import android.app.Activity;
//import android.os.AsyncTask;
//import android.widget.Toast;
//
//import com.demo.com.single.activity.sample.R;
//import com.socal.connection.sri.surabhi.media.screen.Setup.Utils.SetupFile;
//import com.socal.connection.sri.surabhi.media.utils.CommonUtil;
//import com.socal.connection.sri.surabhi.media.utils.License.SiteLicenceCommon;
//import com.socal.connection.sri.surabhi.media.utils.SharedStaticObjects;
//
//import java.io.File;
//
//import static com.socal.connection.sri.surabhi.media.utils.SharedStaticObjects.SMB_TAG;
//
//
///**
// * Created by logix028 on 15/03/17.
// */
//
//public class SambaExportImportSetupTask extends AsyncTask<String, Void, Boolean> {
//    private final int _acceptTypeIndex = 0;
//    private final int _setupFileIndex = 1;
//    private final int _passwordFileIndex = 2;
//    private final int _pinFileIndex = 3;
//    Activity activity;
//    SetupFile signItSetup;
//    private boolean _importExportSetup = false;
//    private boolean _testLicence = false;
//    private String _errorText = "";
//
//    public SambaExportImportSetupTask(Activity activity, SetupFile signItSetup, boolean _importExportSetup) {
//        this.activity = activity;
//        this.signItSetup = signItSetup;
//        this._importExportSetup = _importExportSetup;
//        this._testLicence = signItSetup.getDoCheckLicenceOnSync();
//    }
//
//    @Override
//    protected Boolean doInBackground(String... params) {
//        boolean isLicenseActive = false;
//
//        String[] filesToDowload;
//        String test = "Connecting to server failed";
//
//        // Connect to Server
//        filesToDowload = new String[4];
//        filesToDowload[_acceptTypeIndex] = SharedStaticObjects.AcceptTypeFileName;
//        filesToDowload[_setupFileIndex] = SharedStaticObjects.SetupFileName;
//        filesToDowload[_passwordFileIndex] = SharedStaticObjects.PasswordFileName;
//        filesToDowload[_pinFileIndex] = SharedStaticObjects.PinFileName;
//
//        try {
//            String fileserver = params[0];
//            String username = params[1];
//            String password = params[2];
//            String ftpPath = params[3];
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
//
//            String sambaSetupPath = SMB_TAG + fileserver + ftpPath + SharedStaticObjects.SetupFolderName;
//            int count = 0;
//            if (_importExportSetup) {
//                for (String name : filesToDowload) {
//                    File sourcePath = new File(SharedStaticObjects.AppSetupPath + name);
//                    test = "File has been not Imported sucessfully";
//                    boolean isDownload = SambaServerUtils.download(username, password, sourcePath, sambaSetupPath + sourcePath.getNameID());
//                    if (!isDownload) {
//                        count++;
//                    }
//                }
//                if (count == 0) {
//                    return true;
//                }
//                return false;
//            }
//
//            for (String name : filesToDowload) {
//                File sourcePath = new File(SharedStaticObjects.AppSetupPath + name);
//                if (sourcePath.exists()) {
//                    test = "File has been not Exported successfully";
//                    boolean isUpload = SambaServerUtils.upload(username, password, sourcePath, sambaSetupPath + sourcePath.getNameID());
//                    if (!isUpload) {
//                        count++;
//                    }
//                }
//            }
//            if (count == 0) {
//                return true;
//            }
//            return false;
//
//        } catch (Exception e) {
//            _errorText = test + " failed. \r\n\r\nError: " + e.toString();
//            return false;
//        }
//    }
//
//    @Override
//    protected void onPostExecute(Boolean result) {
//        super.onPostExecute(result);
//        CommonUtil.progressDismiss();
//        if (result) {
//            if (this._importExportSetup) {
//                Toast.makeText(activity, activity.getString(R.string.file_is_imported_successfully), Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(activity, activity.getString(R.string.file_is_exported_successfully), Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            CommonUtil.showBasicAlert(activity, activity.getString(R.string.setup_import_export_dot), _errorText);
//        }
//    }
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//        if (this._importExportSetup) {
//            CommonUtil.progressShow(activity, activity.getString(R.string.setupImport));
//        } else {
//            CommonUtil.progressShow(activity, activity.getString(R.string.setupExport));
//        }
//    }
//
//}