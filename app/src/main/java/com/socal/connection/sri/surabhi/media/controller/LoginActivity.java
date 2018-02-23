package com.socal.connection.sri.surabhi.media.controller;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.demo.com.single.activity.sample.R;
import com.socal.connection.sri.surabhi.media.screen.Setup.Enum.ColourThemeFileType;
import com.socal.connection.sri.surabhi.media.screen.Setup.Utils.SetupFile;
import com.socal.connection.sri.surabhi.media.utils.Common;
import com.socal.connection.sri.surabhi.media.utils.CommonUtil;
import com.socal.connection.sri.surabhi.media.utils.SharedPref;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.socal.connection.sri.surabhi.media.utils.License.SimpleCrypto.LicenseDecrypt;
import static com.socal.connection.sri.surabhi.media.utils.License.SimpleCrypto.LicenseEncrypt;


public class LoginActivity extends AppCompatActivity {
    // Splash screen timer
    @BindView(R.id.sign_in_layout)
    LinearLayout signInLayout;

    @BindView(R.id.sign_up_layout)
    LinearLayout signUpLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.sign_in_user_name)
    EditText signInUserName;

    @BindView(R.id.sign_in_password)
    EditText signInPassword;

    @BindView(R.id.sign_up_full_name)
    EditText signUpFullName;

    @BindView(R.id.sign_up_email)
    EditText signUpEmail;

    @BindView(R.id.sign_up_number)
    EditText signUpNumber;

    @BindView(R.id.sign_up_password)
    EditText signUpPassword;

    private Unbinder unbinder;
    private static final int PERMISSION_REQUEST_CODE_USE_FINGERPRINT = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SetupFile setupFile = new SetupFile.SetupBuilder().build();

        ColourThemeFileType.setTheme(this, ColourThemeFileType.getType(setupFile.getColourFileType()));

        setContentView(R.layout.activity_login);

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.login_layout);
        relativeLayout.setBackgroundColor(ColourThemeFileType.setBackgroundColor(this, ColourThemeFileType.getType(setupFile.getColourFileType())));

        unbinder = ButterKnife.bind(this);

        toolbar.setTitle("Sign In");
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);

        signInLayout.setVisibility(View.VISIBLE);
        signUpLayout.setVisibility(View.INVISIBLE);
    }

    /*
     * This Class is create license.ini file, when application is install first time,
     * This licens is represent free version and after create license application will be
     * move to next screen.
     *
     */

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        onSignInBtn();
    }

    public void onClickAction(View view) {
        if (view.getId() == R.id.sign_up_screen_open) {
            signInLayout.setVisibility(View.INVISIBLE);
            signUpLayout.setVisibility(View.VISIBLE);
            getSupportActionBar().setTitle("Sign Up");
        } else if (view.getId() == R.id.sign_up_now_text_view) {
            signInLayout.setVisibility(View.VISIBLE);
            signUpLayout.setVisibility(View.INVISIBLE);
            getSupportActionBar().setTitle("Sign In");
        } else if (view.getId() == R.id.login_sign_in_btn) {
            onSignInBtn();
        } else if (view.getId() == R.id.sign_up_sign_up_btn) {
            onSignUpBtn();
        } else if (view.getId() == R.id.forgot_password) {
            forgotPasswordDialog();
        }
    }

    protected void forgotPasswordDialog() {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(LoginActivity.this);
        View promptView = layoutInflater.inflate(R.layout.dialog_forgot_password, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this, R.style.AmberAlertDialogTheme);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (editText.getText().toString().equals("")) {
                            CommonUtil.showBasicAlert(LoginActivity.this, "Error...", "Please put Email id.");
                            return;
                        }
                        onForgotPassword(editText.getText().toString());
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }


    private void onSignInBtn() {
        if (signInUserName.getText().toString().equals("")) {
            CommonUtil.showBasicAlert(LoginActivity.this, "Error...", "Please put User Name.");
            return;
        }
        if (signInPassword.getText().toString().equals("")) {
            CommonUtil.showBasicAlert(LoginActivity.this, "Error...", "Please put Password.");
            return;
        }
        onSignInAPIAPI(signInUserName.getText().toString(), signInPassword.getText().toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    private boolean isSignInErrorDeduct = false;

    private void onSignInAPIAPI(String user, String password) {
        CommonUtil.progressShow(this, getString(R.string.loading_dot));
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Common.LogInURL + "id=" + user + "&pass=" + password;

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        CommonUtil.progressDismiss();
                        try {
                            if (response.getString("status").equals("success")) {
                                SharedPref.putString(LoginActivity.this, Common.Login_CREDENTIALS_TAG, response.toString());
                                callMainScreen();
                            } else {
                                CommonUtil.showBasicAlert(LoginActivity.this, "Error...", response.getString("status_text"));
                            }
                            isSignInErrorDeduct = false;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "Error: " + error.getMessage());
                if (!isSignInErrorDeduct) {
                    isSignInErrorDeduct = true;
                    onSignInAPIAPI(signInUserName.getText().toString(), signInPassword.getText().toString());
                } else {
                    isSignInErrorDeduct = false;
                    CommonUtil.progressDismiss();
                    CommonUtil.showBasicAlert(LoginActivity.this, "Error...", "Have something problem on server, please try again.");
                }
            }
        });

        queue.add(jsonObjReq);
    }

    private void onSignUpBtn() {
        if (signUpEmail.getText().toString().equals("")) {
            CommonUtil.showBasicAlert(LoginActivity.this, "Error...", "Please put Email address.");
            return;
        }
        if (signUpFullName.getText().toString().equals("")) {
            CommonUtil.showBasicAlert(LoginActivity.this, "Error...", "Please put User Name.");
            return;
        }
        if (signUpPassword.getText().toString().equals("")) {
            CommonUtil.showBasicAlert(LoginActivity.this, "Error...", "Please put Password.");
            return;
        }
        if (signUpNumber.getText().toString().equals("")) {
            CommonUtil.showBasicAlert(LoginActivity.this, "Error...", "Please put Phone Number.");
            return;
        }
        onSignUpAPIAPI(signUpEmail.getText().toString(), signUpPassword.getText().toString(),
                signUpFullName.getText().toString(), signUpNumber.getText().toString());
    }

    private boolean isSignUpErrorDeduct = false;

    private void onSignUpAPIAPI(String email, String password, String user, String phone) {
        CommonUtil.progressShow(this, getString(R.string.loading_dot));
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Common.LogUnURL + "email=" + email + "&password=" + password + "&name=" + user + "&phone=" + phone;
        url = url.replace(" ", "%20");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        CommonUtil.progressDismiss();
                        try {
                            if (response.getString("status").equals("success")) {
                                SharedPref.putString(LoginActivity.this, Common.Login_CREDENTIALS_TAG, response.toString());
                                callMainScreen();
                            } else {
                                CommonUtil.showBasicAlert(LoginActivity.this, "Error...", response.getString("status_text"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        isSignUpErrorDeduct = false;
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "Error: " + error.getMessage());
                if (!isSignUpErrorDeduct) {
                    isSignUpErrorDeduct = true;
                    onSignUpAPIAPI(signUpEmail.getText().toString(), signUpPassword.getText().toString(),
                            signUpFullName.getText().toString(), signUpNumber.getText().toString());
                } else {
                    isSignUpErrorDeduct = false;
                    CommonUtil.progressDismiss();
                    CommonUtil.showBasicAlert(LoginActivity.this, "Error...", "Have something problem on server, please try again.");
                }
            }
        });

        queue.add(jsonObjReq);
    }

    private boolean isForgetPassErrorDeduct = false;

    private void onForgotPassword(final String email) {
        CommonUtil.progressShow(this, getString(R.string.loading_dot));
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Common.FORGET_PASSWORD_URL + "email=" + email;

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        CommonUtil.progressDismiss();
                        try {
                            if (response.getString("status").equals("success")) {
                                CommonUtil.showBasicAlert(LoginActivity.this, "Success...", response.getString("status_text"));
                            } else {
                                CommonUtil.showBasicAlert(LoginActivity.this, "Error...", response.getString("status_text"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        isForgetPassErrorDeduct = false;
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "Error: " + error.getMessage());
                if (!isForgetPassErrorDeduct) {
                    isForgetPassErrorDeduct = true;
                    onForgotPassword(email);
                } else {
                    isForgetPassErrorDeduct = false;
                    CommonUtil.progressDismiss();
                    CommonUtil.showBasicAlert(LoginActivity.this, "Error...", "Have something problem on server, please try again.");
                }
            }
        });

        queue.add(jsonObjReq);
    }

    /*
    * Move Current Active to SignITActivity
    */
    private void callMainScreen() {
        Intent intent = new Intent(LoginActivity.this, SriSurabhiActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

}
