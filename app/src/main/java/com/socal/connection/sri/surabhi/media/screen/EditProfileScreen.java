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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.demo.com.single.activity.sample.R;
import com.socal.connection.sri.surabhi.media.controller.LoginActivity;
import com.socal.connection.sri.surabhi.media.controller.SriSurabhiActivity;
import com.socal.connection.sri.surabhi.media.screen.Setup.Enum.ColourThemeFileType;
import com.socal.connection.sri.surabhi.media.screen.Setup.Utils.SetupFile;
import com.socal.connection.sri.surabhi.media.utils.AnimUtils;
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
 * Created by vivek on 04/01/18.
 */

public class EditProfileScreen extends Screen {
    private Activity activity = null;
    private SetupFile setupFile;
    private FrameLayout rootView;
    private Unbinder unbinder;

    @BindView(R.id.edit_full_name)
    EditText editFullName;

    @BindView(R.id.edit_email)
    TextView editEmail;

    @BindView(R.id.edit_number)
    EditText editNumber;

    @BindView(R.id.edit_password)
    EditText editPassword;

    public EditProfileScreen(Activity activity, Object data, SetupFile setupFile) {
        this.activity = activity;
        this.setupFile = setupFile;
    }

    @Override
    public void onCurrentScreen() {
        rootView = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.screen_edit_profile, null);
        unbinder = ButterKnife.bind(this, view);
        rootView.addView(view);

        LinearLayout relativeLayout = (LinearLayout) rootView.findViewById(R.id.edit_profile_layout);
        relativeLayout.setBackgroundColor(ColourThemeFileType.setBackgroundColor(activity, ColourThemeFileType.getType(setupFile.getColourFileType())));

        ((SriSurabhiActivity) activity).getSupportActionBar().setTitle("Edit Profile");

        AnimUtils.loadAnimate((RelativeLayout) activity.findViewById(R.id.root_anim_view));

        String json = SharedPref.getString(activity, Common.Login_CREDENTIALS_TAG);
        try {
            JSONObject obj = new JSONObject(json);
            editFullName.setText(obj.getString("name"));
            editEmail.setText(obj.getString("email"));
            editNumber.setText(obj.getString("phone"));
            editPassword.setText(obj.getString("password"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLeaveScreen() {
        if (unbinder != null)
            unbinder.unbind();

        FrameLayout applicationParentFrameLayout = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        LinearLayout homeLayout = (LinearLayout) applicationParentFrameLayout.findViewById(R.id.edit_profile_layout);
        applicationParentFrameLayout.removeView(homeLayout);
    }

    @Override
    public void onClickAction(View view) {
        if (view.getId() == R.id.save_continue_btn) {
            onSignUpBtn();
        }
    }

    private void onSignUpBtn() {
        if (editEmail.getText().toString().equals("")) {
            CommonUtil.showBasicAlert(activity, "Error...", "Please put Email address.");
            return;
        }
        if (editFullName.getText().toString().equals("")) {
            CommonUtil.showBasicAlert(activity, "Error...", "Please put User Name.");
            return;
        }
        if (editPassword.getText().toString().equals("")) {
            CommonUtil.showBasicAlert(activity, "Error...", "Please put Password.");
            return;
        }
        if (editNumber.getText().toString().equals("")) {
            CommonUtil.showBasicAlert(activity, "Error...", "Please put Phone Number.");
            return;
        }
        onSignUpAPIAPI(editEmail.getText().toString(), editPassword.getText().toString(),
                editFullName.getText().toString(), editNumber.getText().toString());
    }

    private boolean isEditProfileErrorDeduct = false;

    private void onSignUpAPIAPI(final String email, final String password, final String user, final String phone) {
        CommonUtil.progressShow(activity, activity.getString(R.string.loading_dot));
        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = Common.EditProfURL + "email=" + email + "&password=" + password + "&name=" + user + "&phone=" + phone;
        url = url.replace(" ", "%20");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        CommonUtil.progressDismiss();
                        try {
                            if (response.getString("status").equals("success")) {
                                SharedPref.putString(activity, Common.Login_CREDENTIALS_TAG, response.toString());
                                CommonUtil.showBasicAlert(activity, "Success...", response.getString("status"));
                            } else {
                                CommonUtil.showBasicAlert(activity, "Error...", response.getString("status"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        isEditProfileErrorDeduct = false;
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "Error: " + error.getMessage());
                if (!isEditProfileErrorDeduct) {
                    isEditProfileErrorDeduct = true;
                    onSignUpAPIAPI(email, password, user, phone);
                } else {
                    isEditProfileErrorDeduct = false;
                    CommonUtil.progressDismiss();
                    CommonUtil.showBasicAlert(activity, "Error...", "Have something problem on server, please try again.");
                }
            }
        });

        queue.add(jsonObjReq);
    }

    @Override
    public boolean onCreate(Bundle savedInstanceState) {
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ((SriSurabhiActivity) activity).getMenuInflater().inflate(R.menu.menu, menu);
        menu.findItem(R.id.menuExit).setVisible(false);
        menu.findItem(R.id.menuAbout).setVisible(false);
        menu.findItem(R.id.menuSetup).setVisible(false);
        menu.findItem(R.id.updateLicence).setVisible(false);
        menu.findItem(R.id.menu_view_all).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return onCreateOptionsMenu(item.getSubMenu());
    }

    @Override
    public ScreenEnums onBackPressed() {
        return ScreenEnums.SCREEN_HOME;
    }
}
