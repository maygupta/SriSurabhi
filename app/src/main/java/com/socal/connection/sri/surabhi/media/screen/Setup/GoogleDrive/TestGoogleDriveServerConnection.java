//package com.socal.connection.sri.surabhi.media.screen.Setup.GoogleDrive;
//
//import android.app.Activity;
//import android.os.AsyncTask;
//
//import com.demo.com.single.activity.sample.R;
//import com.socal.connection.sri.surabhi.media.screen.Setup.Utils.SetupFile;
//import com.socal.connection.sri.surabhi.media.utils.CommonUtil;
//import com.socal.connection.sri.surabhi.media.utils.License.SiteLicenceCommon;
//
//import java.io.IOException;
//
//
///**
// * TestGoogleDriveServerConnection class is responsible to check the connect of Google Drive Server.
// * This is Custom AsyncTask class.
// */
//
//public class TestGoogleDriveServerConnection extends AsyncTask<String, Void, Boolean> {
//
//    SetupFile signItSetup = null;
//    private Boolean _testLicence;
//    private String _errorText;
//    private Activity activity;
//    private com.google.api.services.drive.Drive mService = null;
//    private Exception mLastError = null;
//
//   /*
//   * This method is custom method of AsyncTask class, In this class we initilize value
//   * @Param activity:- This object is contain current activity context
//   * @Param signItSetup:- This object is contain hole setup information
//   * @Param _testLicence:- This object is contain value of currect licencing feature
//   * @Param mService:- This object is contain google Drive Server reference.
//   */
//
//    public void executeTask(Activity activity, GoogleAccountCredential mCredential, SetupFile signItSetup) {
//        this.activity = activity;
//        this.signItSetup = signItSetup;
////        this._testLicence = signItSetup.getDoCheckLicenceOnSync();
//
//        HttpTransport transport = AndroidHttp.newCompatibleTransport();
//        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
//
//        // Initialize credentials and service object.
//        mService = new com.google.api.services.drive.Drive.Builder(transport, jsonFactory, mCredential)
//                .setApplicationName(activity.getResources().getString(R.string.app_name)).build();
//
//        this.execute();
//    }
//
//    /**
//     * Background task to call Drive API.
//     */
//
//    @Override
//    protected Boolean doInBackground(String... strings) {
//        try {
//            boolean isLicenseActive;
//
//            if (this._testLicence) {
//                isLicenseActive = SiteLicenceCommon.ReadFileGoogleDriveLicenseFile(mService, signItSetup);
//            } else {
//                isLicenseActive = SiteLicenceCommon.checkLocalLicence(activity);
//            }
//            if (!isLicenseActive) {
//                _errorText = "Licence is not verify, so please contact with Admin or Try again!";
//                return false;
//            }
//
//            return getDataFromApi(signItSetup.getServerPath(), signItSetup.getFileNetwork());
//
//        } catch (Exception e) {
//            mLastError = e;
//            _errorText = "Server is not connected, so please talk with Admin or Try again!";
//            cancel(true);
//            return false;
//        }
//    }
//
//    /**
//     * Fetch a list of up to 10 file names and IDs.
//     *
//     * @return List of Strings describing files, or an empty list if no files
//     * found.
//     * @throws IOException
//     */
//    private boolean getDataFromApi(String ftpPath, String fileNetwork) throws IOException {
//
//        Drive.Files.List request = null;
//        boolean isResult = false;
//
//        String pathList[] = ftpPath.replaceAll("/", " ").trim().split(" ");
//        request = mService.files().list();
//        String query = "'" + fileNetwork + "' in parents and trashed=false";
//        FileList files = request.setQ(query).execute();
//        List<com.google.api.services.drive.model.File> filesList = files.getFiles();
//        if (files != null) {
//            for (com.google.api.services.drive.model.File file : filesList) {
//                if (file.getName().trim().equalsIgnoreCase(pathList[0].replace("/", "").trim())) {
//                    isResult = true;
//                }
//            }
//        }
//
//        return isResult;
//    }
//
//    @Override
//    protected void onPreExecute() {
//        CommonUtil.progressShow(activity, activity.getString(R.string.setup_test));
//    }
//
//    @Override
//    protected void onPostExecute(Boolean aBoolean) {
//        super.onPostExecute(aBoolean);
//        if (aBoolean) {
//            CommonUtil.progressDismiss();
//            CommonUtil.showBasicAlert(activity, activity.getString(R.string.eval_text_connection), activity.getString(R.string.eval_server_is_connected_sucessfully));
//        } else {
//            CommonUtil.progressDismiss();
//            CommonUtil.showBasicAlert(activity, activity.getString(R.string.eval_text_connection), _errorText);
//        }
//    }
//
//    @Override
//    protected void onCancelled() {
////        CommonUtil.progressDismiss();
////        if (mLastError != null) {
////            PaymentActivity mainActivity = PaymentActivity.getInstance();
////            if (mLastError instanceof GooglePlayServicesAvailabilityIOException) {
////                mainActivity.showGooglePlayServicesAvailabilityErrorDialog(((GooglePlayServicesAvailabilityIOException) mLastError).getConnectionStatusCode());
////            } else if (mLastError instanceof UserRecoverableAuthIOException) {
////                mainActivity.startActivityForResult(((UserRecoverableAuthIOException) mLastError).getIntent(), REQUEST_AUTHORIZATION);
////            } else {
////                CommonUtil.showBasicAlert(activity, activity.getString(R.string.error_dot),
////                        activity.getString(R.string.error_message_start) + mLastError.getMessage());
////            }
////        } else {
////            Log.i("eclipse", "Request cancelled.");
////        }
//    }
//
//}
//
//
