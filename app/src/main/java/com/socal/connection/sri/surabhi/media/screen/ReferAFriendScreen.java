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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.demo.com.single.activity.sample.R;
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

public class ReferAFriendScreen extends Screen {

    @BindView(R.id.inviteEmailEditText)
    public EditText inviteEmailEditText;

    private Activity activity = null;
    private SetupFile setupFile;
    private FrameLayout rootView;
    private Unbinder unbinder;

    public ReferAFriendScreen(Activity activity, Object data, SetupFile setupFile) {
        this.activity = activity;
        this.setupFile = setupFile;
    }

    @Override
    public void onCurrentScreen() {
        rootView = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.screen_refer_a_friend, null);
        unbinder = ButterKnife.bind(this, view);
        rootView.addView(view);

        LinearLayout relativeLayout = (LinearLayout) rootView.findViewById(R.id.refer_a_friend_layout);
        relativeLayout.setBackgroundColor(ColourThemeFileType.setBackgroundColor(activity, ColourThemeFileType.getType(setupFile.getColourFileType())));

        ((SriSurabhiActivity) activity).getSupportActionBar().setTitle("Refer A Friend");

        AnimUtils.loadAnimate((RelativeLayout) activity.findViewById(R.id.root_anim_view));
    }

    @Override
    public void onLeaveScreen() {
        if (unbinder != null)
            unbinder.unbind();

        FrameLayout applicationParentFrameLayout = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        LinearLayout homeLayout = (LinearLayout) applicationParentFrameLayout.findViewById(R.id.refer_a_friend_layout);
        applicationParentFrameLayout.removeView(homeLayout);
    }

    @Override
    public void onClickAction(View view) {
        if (inviteEmailEditText.getText().toString().equals("")) {
            CommonUtil.showBasicAlert(activity, "Error...", "Please put Email address.");
            return;
        }
        try {
            JSONObject obj = new JSONObject(SharedPref.getString(activity, Common.Login_CREDENTIALS_TAG));
            onBookNowBtn(inviteEmailEditText.getText().toString(), obj.getString("email"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void onBookNowBtn(String people_email, String my_email) {
        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = Common.REFER_FRIEND_URL + "id=" + people_email + "&from=" + my_email;
        CommonUtil.progressShow(activity, activity.getString(R.string.loading_dot));
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        CommonUtil.progressDismiss();
                        try {
                            if (response.getString("status").equals("success")) {
                                CommonUtil.showBasicAlert(activity, "Success...", response.getString("status_text"));
                            } else {
                                CommonUtil.showBasicAlert(activity, "Error...", response.getString("status_text"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "Error: " + error.getMessage());
                CommonUtil.progressDismiss();
                CommonUtil.showBasicAlert(activity, activity.getString(R.string.error_dot), error.getMessage());
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
