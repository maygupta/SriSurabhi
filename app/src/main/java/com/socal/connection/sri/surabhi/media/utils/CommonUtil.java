package com.socal.connection.sri.surabhi.media.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;

import com.demo.com.single.activity.sample.R;
import com.socal.connection.sri.surabhi.media.screen.Setup.Enum.LicenseFileType;
import com.socal.connection.sri.surabhi.media.utils.webkit.ClientCertificateWebView;


public class CommonUtil {

    static ProgressDialog progressDialog = null;

    public static void launchMarket(Activity activity, String links) {
        try {
            Uri uri = Uri.parse(links);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
        }

    }

    public static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.wtf(Common.TAG, "UTF-8 should always be supported", e);
            throw new RuntimeException("URLEncoder.encode() failed for " + s);
        }
    }

    public static void aboutSignIt(Activity activity, int licenseFileType) {

        final ClientCertificateWebView webview;
        final View dialogLayout;
        final AlertDialog.Builder alertDialog;
        String dialogBody;
        StringBuilder bodyBuilder = new StringBuilder();

        BufferedReader reader;

        try {
            reader = new BufferedReader(new InputStreamReader(activity.getAssets()
                    .open(getAboutScreenFile(licenseFileType))));
            try {
                while ((dialogBody = reader.readLine()) != null) {
                    bodyBuilder.append(dialogBody);
                }
            } finally {
                reader.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try {

            dialogBody = String.format(bodyBuilder.toString(), activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        alertDialog = new AlertDialog.Builder(activity);
        alertDialog.setIcon(getIconImage(licenseFileType));
        alertDialog.setTitle(getAboutPopupTitle(activity, licenseFileType));

        dialogLayout = LayoutInflater.from(activity).inflate(R.layout.dialog_details_html, null);
        webview = (ClientCertificateWebView) dialogLayout.findViewById(R.id.webview);
        webview.loadData(dialogBody, Common.TextHtmlFileFormat, null);
        alertDialog.setView(dialogLayout);
        alertDialog.setPositiveButton(R.string.ok_tag, null);
        alertDialog.show();
    }

    static String getAboutPopupTitle(Activity activity, int currentLicence) {
        if (currentLicence == LicenseFileType.FREE.getValue()) {
            return activity.getString(R.string.about_signit_free);
        } else if (currentLicence == LicenseFileType.LITE.getValue()) {
            return activity.getString(R.string.about_signit_lite);
        } else if (currentLicence == LicenseFileType.STANDARD.getValue()) {
            return activity.getString(R.string.about_signit_standard);
        } else if (currentLicence == LicenseFileType.ENTERPRISE.getValue()) {
            return activity.getString(R.string.about_signit_enterprise);
        }
        return activity.getString(R.string.about_signit_free);
    }

    static String getAboutScreenFile(int currentLicence) {
        if (currentLicence == LicenseFileType.FREE.getValue()) {
            return SharedStaticObjects.AboutFileNameFree;
        } else if (currentLicence == LicenseFileType.LITE.getValue()) {
            return SharedStaticObjects.AboutFileNameLite;
        } else if (currentLicence == LicenseFileType.STANDARD.getValue()) {
            return SharedStaticObjects.AboutFileNameStandard;
        } else if (currentLicence == LicenseFileType.ENTERPRISE.getValue()) {
            return SharedStaticObjects.AboutFileNameEnterprise;
        } else {
            return SharedStaticObjects.AboutFileNameFree;
        }
    }

    public static int getIconImage(int currentLicence) {
        if (currentLicence == LicenseFileType.FREE.getValue()) {
            return R.mipmap.ic_launcher;
        } else if (currentLicence == LicenseFileType.LITE.getValue()) {
            return R.mipmap.ic_launcher;
        } else if (currentLicence == LicenseFileType.STANDARD.getValue()) {
            return R.mipmap.ic_launcher;
        } else if (currentLicence == LicenseFileType.ENTERPRISE.getValue()) {
            return R.mipmap.ic_launcher;
        }
        return R.mipmap.ic_launcher;
    }

    public static void showBasicAlert(Activity activity, String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title);
        builder.setMessage(message);

        String positiveText = activity.getString(android.R.string.ok);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();

    }

    public static void showBasicAlertWithoutBtn(Activity activity, String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title);
        builder.setMessage(message);

//        String positiveText = activity.getString(android.R.string.ok);
//        builder.setPositiveButton(positiveText,
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // positive button logic
//                        dialog.dismiss();
//                    }
//                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();

    }

    @SuppressLint("RestrictedApi")
    public static void progressShow(Activity activity, String text) {
        if (progressDialog == null) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
                progressDialog = new ProgressDialog(new ContextThemeWrapper(activity, android.R.style.Theme_Holo_Light_Dialog));
            } else {
                progressDialog = new ProgressDialog(activity);
            }
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage(text);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
    }

    public static void progressDismiss() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public static boolean isFileExit(String path) {
        File file = new File(path);
        if (file.exists()) {
            return true;
        }
        return false;
    }
}
