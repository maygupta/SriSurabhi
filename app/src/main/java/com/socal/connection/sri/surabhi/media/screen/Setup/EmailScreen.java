package com.socal.connection.sri.surabhi.media.screen.Setup;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.com.single.activity.sample.R;
import com.socal.connection.sri.surabhi.media.controller.SriSurabhiActivity;
import com.socal.connection.sri.surabhi.media.screen.Screen;
import com.socal.connection.sri.surabhi.media.screen.Setup.Enum.ColourThemeFileType;
import com.socal.connection.sri.surabhi.media.screen.Setup.Utils.SetupFile;
import com.socal.connection.sri.surabhi.media.utils.Common;
import com.socal.connection.sri.surabhi.media.utils.Enums.ScreenEnums;
import com.socal.connection.sri.surabhi.media.utils.License.SignItLicenseFile;
import com.socal.connection.sri.surabhi.media.utils.SharedStaticObjects;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.io.File;
import java.util.ArrayList;

import butterknife.Unbinder;

import static com.socal.connection.sri.surabhi.media.utils.Common.SS_HELP_SCREEN_NAME;
import static com.socal.connection.sri.surabhi.media.utils.Common.licenceTag;
import static com.socal.connection.sri.surabhi.media.utils.CommonUtil.*;


public class EmailScreen extends Screen {
    FrameLayout rootView = null;
    private Activity activity = null;
    private int screen_name;
    private MaterialBetterSpinner licenceTypeSpinner;
    private SetupFile setupFile;
    private Unbinder unbinder;

    public EmailScreen(Activity activity, Object data, SetupFile setupFile) {
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
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        screen_name = savedInstanceState.getInt(SS_HELP_SCREEN_NAME);
    }

    @Override
    public void onCurrentScreen() {
        rootView = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        layoutInflater.inflate(R.layout.screen_email, rootView);
        RelativeLayout helpLayout = (RelativeLayout) rootView.findViewById(R.id.email_screen);
        helpLayout.setVisibility(View.VISIBLE);

        Toolbar myToolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        myToolbar.setNavigationIcon(getIconImage(setupFile.getLicenseType()));
        myToolbar.setTitle(R.string.email_screen);
        myToolbar.setTitleTextColor(ColourThemeFileType.setTextColor(activity, ColourThemeFileType.getType(setupFile.getColourFileType())));
        ((SriSurabhiActivity) activity).setSupportActionBar(myToolbar);

        String mydevice_info = SignItLicenseFile.getEncryptDeviceInfo(activity);
        ((TextView) rootView.findViewById(R.id.mail_deviceInfoField)).setText(mydevice_info);

        ((TextView) rootView.findViewById(R.id.email_ToField)).setText(activity.getString(R.string.dfi_mail_id));

        licenceTypeSpinner = (MaterialBetterSpinner) rootView.findViewById(R.id.licenceTypePeacker);
        licenceTypeSpinner.setAdapter(new ArrayAdapter<String>(activity, R.layout.spinner_item, licenceTag));
    }

    @Override
    public ScreenEnums onBackPressed() {
        return ScreenEnums.SCREEN_LICENCE;
    }

    @Override
    public void onLeaveScreen() {
        rootView = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        RelativeLayout helpLayout = (RelativeLayout) rootView.findViewById(R.id.email_screen);
        rootView.removeView(helpLayout);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(SS_HELP_SCREEN_NAME, screen_name);

    }

    @Override
    public void onClickAction(View view) {
        switch (view.getId()) {
            case R.id.sendEmail:
                break;
        }
        if (view.getId() == R.id.sendEmail) {
            sendEmail();
        } else if (view.getId() == R.id.discard_email) {
            SriSurabhiActivity.getInstance().switchScreen(ScreenEnums.SCREEN_LICENCE, activity, null, setupFile);
        }
    }

    public void sendEmail() {
        String composeemail = Common.BACK_N + activity.getString(R.string.email_device_code) + " - " + getCleanViewText(R.id.mail_deviceInfoField)
                + Common.BACK_N + activity.getString(R.string.email_licenceType) + " - " + licenceTypeSpinner.getText().toString()
                + Common.BACK_N + activity.getString(R.string.email_fullname) + " - " + getCleanViewText(R.id.mail_fullNameField)
                + Common.BACK_N + activity.getString(R.string.email_companyname) + " - " + getCleanViewText(R.id.mail_companyField)
                + Common.BACK_N + activity.getString(R.string.email_postaladdress) + " - " + getCleanViewText(R.id.mail_postalAddressField)
                + Common.BACK_N + activity.getString(R.string.email_phonenumber) + " - " + getCleanViewText(R.id.email_phoneField)
                + Common.BACK_N + activity.getString(R.string.email_emailaddress) + " - " + getCleanViewText(R.id.email_emailFromField);

        String[] tos = new String[]{getCleanViewText(R.id.email_ToField)};

        Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);

        File paymentDetailsfile = new File(SharedStaticObjects.LicensePaymentDetailsFileNameFilePath);
        if (!paymentDetailsfile.exists() || !paymentDetailsfile.canRead()) {
            Toast.makeText(activity, activity.getString(R.string.email_payment_details_file_is_not), Toast.LENGTH_SHORT).show();
            return;
        }
        Uri paymentDetailsUri = Uri.fromFile(paymentDetailsfile);

        File file = new File(SharedStaticObjects.LicenseFilePath);
        if (!file.exists() || !file.canRead()) {
            return;
        }
        Uri uri = Uri.fromFile(file);
        ArrayList<Uri> uris = new ArrayList<Uri>();
        uris.add(uri);
        uris.add(paymentDetailsUri);
        emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);

        emailIntent.setType(Common.messageRFCFileFormat);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, tos);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, activity.getString(R.string.email_license_kay_subject));
        emailIntent.putExtra(Intent.EXTRA_TEXT, composeemail);

        try {
            activity.startActivityForResult(emailIntent, Common.REQUEST_CODE_SENT_MAIL_FOR_LICENCING);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(activity, R.string.email_there_are_no_email_applications_installed, Toast.LENGTH_SHORT).show();
        }
    }

    private String getCleanViewText(int textViewId) {
        return ((TextView) rootView.findViewById(textViewId)).getText().toString().trim();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Common.REQUEST_CODE_SENT_MAIL_FOR_LICENCING) {
            SriSurabhiActivity.getInstance().switchScreen(ScreenEnums.SCREEN_LICENCE, activity, null, setupFile);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // TODO Auto-generated method stub
    }

}
