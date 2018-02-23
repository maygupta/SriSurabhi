package com.socal.connection.sri.surabhi.media.controller;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.demo.com.single.activity.sample.R;
import com.payumoney.core.entity.TransactionResponse;
import com.payumoney.sdkui.ui.utils.PayUmoneyFlowManager;
import com.payumoney.sdkui.ui.utils.ResultModel;
import com.socal.connection.sri.surabhi.media.Notification.Constants;
import com.socal.connection.sri.surabhi.media.controller.Enum.SideMenuEnum;
import com.socal.connection.sri.surabhi.media.payment.PayUmoney.AppPreference;
import com.socal.connection.sri.surabhi.media.screen.PaymentScreen;
import com.socal.connection.sri.surabhi.media.screen.Screen;
import com.socal.connection.sri.surabhi.media.screen.ScreenSwitchHandler;
import com.socal.connection.sri.surabhi.media.screen.Setup.Enum.ColourThemeFileType;
import com.socal.connection.sri.surabhi.media.screen.Setup.Utils.SetupFile;
import com.socal.connection.sri.surabhi.media.utils.Common;
import com.socal.connection.sri.surabhi.media.utils.CommonUtil;
import com.socal.connection.sri.surabhi.media.utils.Enums.ScreenEnums;
import com.socal.connection.sri.surabhi.media.utils.SharedPref;
import com.socal.connection.sri.surabhi.media.utils.SharedStaticObjects;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import android.app.NotificationChannel;
import android.app.NotificationManager;

public class SriSurabhiActivity extends AppCompatActivity {
    public static AppPreference mAppPreference;
    public static String userId;
    private static SriSurabhiActivity mainActivity = new SriSurabhiActivity();
    private SetupFile setupFile;

    // Sied Menu
    private DrawerLayout mDrawerLayout;

    public static SriSurabhiActivity getInstance() {
        return mainActivity;
    }

    public static void cleanAll(Context context) {
        // TODO
        ScreenSwitchHandler.clean();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cleanAll(this);
        this.mAppPreference = new AppPreference();
        this.setupFile = new SetupFile.SetupBuilder().build();

        ColourThemeFileType.setTheme(this, ColourThemeFileType.getType(setupFile.getColourFileType()));

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        onSideMenuToolBar();

        ScreenEnums activeScreen = ScreenEnums.SCREEN_NONE;

        if (savedInstanceState != null) {

            activeScreen = (ScreenEnums) savedInstanceState.get(Common.SS_BUNDLE_ACTIVE_SCREEN);

        }

        if (activeScreen == ScreenEnums.SCREEN_NONE) {

            activeScreen = ScreenEnums.SCREEN_HOME;
        }

        switchScreen(activeScreen, this, null, setupFile);

        // this was saved onSaveInstancce.
        if (savedInstanceState != null) {

        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(Constants.CHANNEL_ID, Constants.CHANNEL_NAME, importance);
            mChannel.setDescription(Constants.CHANNEL_DESCRIPTION);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mNotificationManager.createNotificationChannel(mChannel);
        }

//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
//        findViewById(R.id.buttonSubscribe).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String topic = spinner.getSelectedItem().toString();
//                FirebaseMessaging.getInstance().subscribeToTopic(topic);
//                Toast.makeText(getApplicationContext(), "Topic Subscribed", Toast.LENGTH_LONG).show();
//            }
//        });

    }

    private void onSideMenuToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Sri Surabhi");
        toolbar.setNavigationIcon(R.drawable.side_menu);

        mDrawerLayout = findViewById(R.id.navigation_drawer);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        final ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                toolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);

        ListView listView = findViewById(R.id.left_drawer);
        ArrayList<SideMenuModel> arrayList = new ArrayList<>();
        for (String value : SideMenuEnum.getList(this)) {
            arrayList.add(new SideMenuModel(R.drawable.side_menu, value));
        }
        try {
            JSONObject obj = new JSONObject(SharedPref.getString(SriSurabhiActivity.this, Common.Login_CREDENTIALS_TAG));
            arrayList.get(0).setName(obj.getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        SideMenuAdaptor<SideMenuModel> adapter = new SideMenuAdaptor<SideMenuModel>(getApplicationContext(), arrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                if (position == SideMenuEnum.USER_NAME.getValue()) {
                    switchScreen(ScreenEnums.SCREEN_EDIT_PROFILE, SriSurabhiActivity.this, null, setupFile);
                } else if (position == SideMenuEnum.REFER_A_FRIEND.getValue()) {
                    switchScreen(ScreenEnums.SCREEN_REFEAR_A_FRIEND, SriSurabhiActivity.this, null, setupFile);
                } else if (position == SideMenuEnum.EXPLORE_SRI_SURABHI.getValue()) {
                    switchScreen(ScreenEnums.SCREEN_EXPLORE_SRI_SURBHI, SriSurabhiActivity.this, null, setupFile);
                } else if (position == SideMenuEnum.EVENTS.getValue()) {
                    switchScreen(ScreenEnums.SCREEN_UPCOMING_EVENT, SriSurabhiActivity.this, null, setupFile);
                } else if (position == SideMenuEnum.SUBSCRIPTION.getValue()) {
                    switchScreen(ScreenEnums.SCREEN_SUBSCRIPTION, SriSurabhiActivity.this, null, setupFile);
                } else if (position == SideMenuEnum.LOG_OUT.getValue()) {
                    SharedPref.putString(SriSurabhiActivity.this, Common.Login_CREDENTIALS_TAG, "");
                    Intent intent = new Intent(SriSurabhiActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else if (position == SideMenuEnum.SUPPORT.getValue()) {
                    switchScreen(ScreenEnums.SCREEN_SUPPORT, SriSurabhiActivity.this, null, setupFile);
                }
            }
        });
    }

    public void switchScreen(ScreenEnums activeScreen, Activity active, Object data, SetupFile setupFile) {
        ScreenSwitchHandler screenSwitchHandler = ScreenSwitchHandler.getScreenSwitchHandler();
        screenSwitchHandler.setCurrentScreenID(activeScreen, active, data, setupFile);
        Screen screen = screenSwitchHandler.getCurrentScreen();

        boolean createSuccess = screen.onCreate(null);

        if (createSuccess) {
            screen.onCurrentScreen();
        }
    }

     /*
    * This method return Current Screen ID:
    *
    */

    public void setSetupFile(SetupFile setupFile) {
        this.setupFile = setupFile;
    }

    String getUserId() {

        return userId;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        boolean success = false;

        Screen screen = ScreenSwitchHandler.getScreenSwitchHandler().getCurrentScreen();

        if (screen != null) {

            success = screen.onCreateOptionsMenu(menu);
        }
        return success;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_view_all:
                switchScreen(ScreenEnums.SCREEN_SUBSCRIPTION_MEMBER_SHIPS, this, null, setupFile);
                return true;
            case R.id.menuSetup:
                if (!CommonUtil.isFileExit(SharedStaticObjects.LicenseFilePath)) {
                    switchScreen(ScreenEnums.SCREEN_LICENCE, this, null, setupFile);
                } else {
                    switchScreen(ScreenEnums.SCREEN_SETUP, this, null, setupFile);
                }
                return true;

            case R.id.menuExit:
                finish();
                return true;

            case R.id.menuAbout:
                CommonUtil.aboutSignIt(this, setupFile.getLicenseType());
                return true;

            case R.id.updateLicence:
                switchScreen(ScreenEnums.SCREEN_LICENCE, this, null, setupFile);
                return true;

            default:
                boolean success = false;
                Screen screen = ScreenSwitchHandler.getScreenSwitchHandler().getCurrentScreen();
                if (screen != null) {
                    success = screen.onOptionsItemSelected(item);
                }
                return success;
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Screen screen = ScreenSwitchHandler.getScreenSwitchHandler().getCurrentScreen();
        if (screen != null) {
            screen.onRestoreInstanceState(savedInstanceState);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        ScreenSwitchHandler screenSwitchHandler = ScreenSwitchHandler.getScreenSwitchHandler();
        Screen screen = screenSwitchHandler.getCurrentScreen();
        if (screen != null) {
            screen.onStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        ScreenSwitchHandler screenSwitchHandler = ScreenSwitchHandler.getScreenSwitchHandler();
        Screen screen = screenSwitchHandler.getCurrentScreen();
        if (screen != null) {
            screen.onResume();
        }
    }

    @Override
    public void onBackPressed() {
        ScreenSwitchHandler screenSwitchHandler = ScreenSwitchHandler.getScreenSwitchHandler();
        Screen screen = screenSwitchHandler.getCurrentScreen();
        if (screen != null) {
            ScreenEnums nextScreen = screen.onBackPressed();
            if (nextScreen != ScreenEnums.SCREEN_NONE) {
                screenSwitchHandler.setCurrentScreenID(nextScreen, this, null, setupFile);
                Screen currentScreen = screenSwitchHandler.getCurrentScreen();
                currentScreen.onCurrentScreen();
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        ScreenSwitchHandler screenSwitchHandler = ScreenSwitchHandler.getScreenSwitchHandler();
        Screen screen = screenSwitchHandler.getCurrentScreen();
        if (screen != null) {
            screen.onPause();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        ScreenSwitchHandler screenSwitchHandler = ScreenSwitchHandler.getScreenSwitchHandler();
        Screen screen = screenSwitchHandler.getCurrentScreen();
        if (screen != null) {
            screen.onStop();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        ScreenSwitchHandler screenSwitchHandler = ScreenSwitchHandler.getScreenSwitchHandler();
        Screen screen = screenSwitchHandler.getCurrentScreen();
        if (screen != null) {
            outState.putSerializable(Common.SS_BUNDLE_ACTIVE_SCREEN, screenSwitchHandler.getCurrentScreenID());
            screen.onSaveInstanceState(outState);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cleanAll(this);
    }

    public void onClickAction(View v) {
        ScreenSwitchHandler screenSwitchHandler = ScreenSwitchHandler.getScreenSwitchHandler();
        if (screenSwitchHandler != null) {
            screenSwitchHandler.getCurrentScreen().onClickAction(v);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result Code is -1 send from Payumoney activity
        Log.d("PayUmoneyOPR", "request code " + requestCode + " resultcode " + resultCode);
        if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == RESULT_OK && data !=
                null) {
            TransactionResponse transactionResponse = data.getParcelableExtra(PayUmoneyFlowManager
                    .INTENT_EXTRA_TRANSACTION_RESPONSE);

            ResultModel resultModel = data.getParcelableExtra(PayUmoneyFlowManager.ARG_RESULT);

            // Check which object is non-null
            if (transactionResponse != null && transactionResponse.getPayuResponse() != null) {
                if (transactionResponse.getTransactionStatus().equals(TransactionResponse.TransactionStatus.SUCCESSFUL)) {
                    //Success Transaction
                } else {
                    //Failure Transaction
                }

                // Response from Payumoney
                String payuResponse = transactionResponse.getPayuResponse();
                Log.i("eclipse", "payuResponse=" + payuResponse);
                // Response from SURl and FURL
                String merchantResponse = transactionResponse.getTransactionDetails();
                Log.i("eclipse", "merchantResponse=" + merchantResponse);
//                new AlertDialog.Builder(this)
//                        .setCancelable(false)
//                        .setMessage("Payu's Data : " + payuResponse + "\n\n\n Merchant's Data: " + merchantResponse)
//                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int whichButton) {
//                                dialog.dismiss();
//                            }
//                        }).show();
                try {
                    JSONObject jsonObject = new JSONObject(payuResponse);
                    jsonObject=jsonObject.getJSONObject("result");
                    if (jsonObject.getString("productinfo").equals(PaymentScreen.donation_msg)) {
                        onPaymentUpdateDonationAPI(jsonObject.getString("email"), jsonObject.getString("amount"));
                    } else {
                        onPaymentUpdateAPI(jsonObject.getString("email"), jsonObject.getString("amount"), jsonObject.getString("productinfo"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (resultModel != null && resultModel.getError() != null) {
                Log.d("eclipse", "Error response : " + resultModel.getError().getTransactionResponse());
            } else {
                Log.d("eclipse", "Both objects are null!");
            }
        }
    }

    private void onPaymentUpdateAPI(String email, String amount, String plane_id) {
        CommonUtil.progressShow(this, this.getString(R.string.loading_dot));
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Common.SUBSCRIBE_PAYMENT + "email=" + email + "&amount=" + amount + "&plan_id=" + plane_id;
        url = url.replace(" ", "%20");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        CommonUtil.progressDismiss();
                        try {
                            CommonUtil.showBasicAlertWithoutBtn(SriSurabhiActivity.this, "", response.getString("status_text"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "Error: " + error.getMessage());
            }
        });

        queue.add(jsonObjReq);
    }

    private void onPaymentUpdateDonationAPI(String email, String amount) {
        CommonUtil.progressShow(this, this.getString(R.string.loading_dot));
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Common.DONATION + "email=" + email + "&amount=" + amount;
        url = url.replace(" ", "%20");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        CommonUtil.progressDismiss();
                        try {
                            CommonUtil.showBasicAlertWithoutBtn(SriSurabhiActivity.this, "", response.getString("status_text"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "Error: " + error.getMessage());
            }
        });

        queue.add(jsonObjReq);
    }
}
