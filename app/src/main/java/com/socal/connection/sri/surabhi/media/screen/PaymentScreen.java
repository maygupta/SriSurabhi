package com.socal.connection.sri.surabhi.media.screen;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.demo.com.single.activity.sample.R;
import com.socal.connection.sri.surabhi.media.controller.SriSurabhiActivity;
import com.socal.connection.sri.surabhi.media.payment.PayUmoneyOPR;
import com.socal.connection.sri.surabhi.media.screen.Setup.Utils.SetupFile;
import com.socal.connection.sri.surabhi.media.utils.Common;
import com.socal.connection.sri.surabhi.media.utils.CommonUtil;
import com.socal.connection.sri.surabhi.media.utils.Enums.ScreenEnums;
import com.socal.connection.sri.surabhi.media.utils.SharedPref;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by vivek on 24/01/18.
 */

public class PaymentScreen extends Screen {
    private Activity activity = null;
    private SetupFile setupFile;
    private FrameLayout rootView;
    private Unbinder unbinder;
    public static String donation_msg = "Sri Surabhi Donation for Gow Sewa";
    @BindView(R.id.dont_name)
    public EditText name;

    @BindView(R.id.dont_email)
    public EditText email;

    @BindView(R.id.dont_phone)
    public EditText phone;

    @BindView(R.id.dont_amount)
    public EditText amount;

    public PaymentScreen(Activity activity, Object data, SetupFile setupFile) {
        this.activity = activity;
        this.setupFile = setupFile;
    }

    @Override
    public void onCurrentScreen() {
        rootView = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.screen_payment, null);
        unbinder = ButterKnife.bind(this, view);
        rootView.addView(view);

        ((SriSurabhiActivity) activity).getSupportActionBar().setTitle("Donation");

        String json = SharedPref.getString(activity, Common.Login_CREDENTIALS_TAG);
        try {
            JSONObject obj = new JSONObject(json);
            name.setText(obj.getString("name"));
            email.setText(obj.getString("email"));
            phone.setText(obj.getString("phone"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLeaveScreen() {
        if (unbinder != null)
            unbinder.unbind();

        FrameLayout applicationParentFrameLayout = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        LinearLayout homeLayout = (LinearLayout) applicationParentFrameLayout.findViewById(R.id.payment_screen);
        applicationParentFrameLayout.removeView(homeLayout);
    }

    @Override
    public void onClickAction(View view) {
        if (name.getText().toString().equals("")) {
            CommonUtil.showBasicAlert(activity, activity.getString(R.string.error_dot), "Please put the name.");
            return;
        }

        if (email.getText().toString().equals("")) {
            CommonUtil.showBasicAlert(activity, activity.getString(R.string.error_dot), "Please put the email.");
            return;
        }

        if (phone.getText().toString().equals("")) {
            CommonUtil.showBasicAlert(activity, activity.getString(R.string.error_dot), "Please put the phone.");
            return;
        }

        if (amount.getText().toString().equals("")) {
            CommonUtil.showBasicAlert(activity, activity.getString(R.string.error_dot), "Please put the amount.");
            return;
        }
        PayUmoneyOPR.launchPayUMoneyFlowDonation(activity, "Sri Surabhi Donation",
                donation_msg, name.getText().toString().trim(),
                amount.getText().toString().trim(),
                phone.getText().toString().trim(), email.getText().toString().trim());
    }

    @Override
    public boolean onCreate(Bundle savedInstanceState) {
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    @Override
    public ScreenEnums onBackPressed() {
        return ScreenEnums.SCREEN_GET_INVOLVED;
    }
}
