package com.socal.connection.sri.surabhi.media.screen.Setup;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.demo.com.single.activity.sample.R;
import com.socal.connection.sri.surabhi.media.controller.SriSurabhiActivity;
import com.socal.connection.sri.surabhi.media.controller.LoginActivity;
import com.socal.connection.sri.surabhi.media.screen.Screen;
import com.socal.connection.sri.surabhi.media.screen.Setup.Enum.ColourThemeFileType;
import com.socal.connection.sri.surabhi.media.screen.Setup.Utils.SetupFile;
import com.socal.connection.sri.surabhi.media.utils.CommonUtil;
import com.socal.connection.sri.surabhi.media.utils.Enums.ScreenEnums;
import com.socal.connection.sri.surabhi.media.utils.License.SignItLicenseFile;
import com.socal.connection.sri.surabhi.media.utils.SharedStaticObjects;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import static com.socal.connection.sri.surabhi.media.utils.Common.SS_HELP_SCREEN_NAME;


public class LicenseScreen extends Screen {

    private static Activity activity = null;
    private TextView licensekey;
    private SetupFile setupFile;
    private int screen_name;

    public LicenseScreen(Activity activity, Object data, SetupFile setupFile) {
        this.activity = activity;
        this.setupFile = setupFile;
    }

    @Override
    public boolean onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return false;
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        screen_name = savedInstanceState.getInt(SS_HELP_SCREEN_NAME);
    }

    @Override
    public void onCurrentScreen() {
        FrameLayout rootView = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        layoutInflater.inflate(R.layout.screen_license, rootView);
        RelativeLayout helpLayout = (RelativeLayout) rootView.findViewById(R.id.licence_screen);
        helpLayout.setVisibility(View.VISIBLE);

        Toolbar myToolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        myToolbar.setNavigationIcon(CommonUtil.getIconImage(setupFile.getLicenseType()));
        myToolbar.setTitle(activity.getString(R.string.licence_screen));
        myToolbar.setTitleTextColor(ColourThemeFileType.setTextColor(activity, ColourThemeFileType.getType(setupFile.getColourFileType())));
        ((SriSurabhiActivity) activity).setSupportActionBar(myToolbar);


        TextView deviceinfo = (TextView) rootView.findViewById(R.id.device_info);
        deviceinfo.setText(SignItLicenseFile.getEncryptDeviceInfo(activity));
        licensekey = (TextView) rootView.findViewById(R.id.license_key);

        final ToggleButton toggleButton = (ToggleButton) rootView.findViewById(R.id.licenceSyncCompoundButton);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isSelection) {
                if (isSelection) {
                    SriSurabhiActivity.getInstance().switchScreen(ScreenEnums.SCREEN_SETUP, activity, null, setupFile);
                }
            }
        });

    }


    @Override
    public ScreenEnums onBackPressed() {
        return ScreenEnums.SCREEN_SETUP;
    }

    @Override
    public void onLeaveScreen() {
        FrameLayout applicationParentFrameLayout = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        RelativeLayout helpLayout = (RelativeLayout) applicationParentFrameLayout.findViewById(R.id.licence_screen);
        applicationParentFrameLayout.removeView(helpLayout);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(SS_HELP_SCREEN_NAME, screen_name);
    }

    @Override
    public void onDestroy() {
        // Stop service when done
//        activity.stopService(new Intent(activity, PayPalService.class));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

    }

    @Override
    public void getResultsFromApi() {

    }

    @Override
    public void onClickAction(View view) {
        File licenceFile = new File(SharedStaticObjects.LicenseFilePath);
        switch (view.getId()) {
            case R.id.activate:
                activateLicence();
                break;
            case R.id.emaillicense:
                SriSurabhiActivity.getInstance().switchScreen(ScreenEnums.SCREEN_EMAIL, activity, null, setupFile);
                break;
            case R.id.picklicensefile:
                SriSurabhiActivity.getInstance().switchScreen(ScreenEnums.SCREEN_PICK_FILE, activity, null, setupFile);
                break;
            case R.id.helplicense:
                helpLicense();
                break;
            case R.id.licenseexit:
                if (licenceFile.exists()) {
                    SriSurabhiActivity.getInstance().switchScreen(ScreenEnums.SCREEN_SETUP, activity, null, setupFile);
                } else {
                    SriSurabhiActivity.getInstance().switchScreen(ScreenEnums.SCREEN_HOME, activity, null, setupFile);
                }
                break;
            case R.id.payment:
//                PaymentOptionListDialog paymentOptionListDialog = new PaymentOptionListDialog();
//                paymentOptionListDialog.show(activity.getFragmentManager(), null);
            default:
                break;
        }
    }

    public void helpLicense() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
        alertDialog.setIcon(R.mipmap.ic_launcher);
        alertDialog.setTitle(activity.getResources().getString(R.string.help_dialog_tittle));
        alertDialog.setMessage(activity.getResources().getString(R.string.help_dialog_message));
        alertDialog.setPositiveButton(R.string.ok_tag, null);
        alertDialog.show();
    }

    public boolean licensevalidity() {
        SignItLicenseFile licenseToValidate = new SignItLicenseFile(activity, licensekey.getText().toString());
        return licenseToValidate.isValid();
    }

    public void activateLicence() {
        try {
            if (licensevalidity()) {
                FileWriter outputFileReader = new FileWriter(SharedStaticObjects.LicenseFilePath);
                PrintWriter outputStream = new PrintWriter(outputFileReader);
                String licensedata = licensekey.getText().toString();
                outputStream.println(licensedata);
                outputStream.close();
                activatePopup();
            } else {
                CommonUtil.showBasicAlert(activity, activity.getString(R.string.error_dot),
                        activity.getString(R.string.licence_kay_wrong_message));
            }
        } catch (Exception e) {
            e.printStackTrace();
            CommonUtil.showBasicAlert(activity, activity.getString(R.string.error_dot), e.toString());
        }
    }

    private void activatePopup() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
        alertDialog.setIcon(R.mipmap.ic_launcher);
        alertDialog.setTitle(activity.getResources().getString(R.string.activate_tag));
        alertDialog.setMessage(activity.getResources().getString(R.string.activate_message));
        alertDialog.setPositiveButton(R.string.ok_tag, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent mStartActivity = new Intent(activity, LoginActivity.class);
                int mPendingIntentId = 123456;
                PendingIntent mPendingIntent = PendingIntent.getActivity(activity, mPendingIntentId, mStartActivity,
                        PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager mgr = (AlarmManager) activity.getSystemService(Context.ALARM_SERVICE);
                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1, mPendingIntent);
                System.exit(0);
            }
        });
        alertDialog.show();
    }

}
