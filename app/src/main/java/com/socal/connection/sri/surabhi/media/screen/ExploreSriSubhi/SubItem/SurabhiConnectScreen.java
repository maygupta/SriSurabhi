package com.socal.connection.sri.surabhi.media.screen.ExploreSriSubhi.SubItem;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
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
import com.socal.connection.sri.surabhi.media.screen.ExploreSriSubhi.dialog.SurbhaiCoordinorDialog;
import com.socal.connection.sri.surabhi.media.screen.ExploreSriSubhi.dialog.WebViewShowDialog;
import com.socal.connection.sri.surabhi.media.screen.Screen;
import com.socal.connection.sri.surabhi.media.screen.Setup.Utils.SetupFile;
import com.socal.connection.sri.surabhi.media.utils.AnimUtils;
import com.socal.connection.sri.surabhi.media.utils.Common;
import com.socal.connection.sri.surabhi.media.utils.CommonUtil;
import com.socal.connection.sri.surabhi.media.utils.Enums.ScreenEnums;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by vivek on 10/01/18.
 */

public class SurabhiConnectScreen extends Screen {
    private Activity activity = null;
    private SetupFile setupFile;
    private FrameLayout rootView;
    private Unbinder unbinder;

    public SurabhiConnectScreen(Activity activity, Object data, SetupFile setupFile) {
        this.activity = activity;
        this.setupFile = setupFile;
    }

    @Override
    public void onCurrentScreen() {
        rootView = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.screen_surabhi_connection, null);
        unbinder = ButterKnife.bind(this, view);
        rootView.addView(view);

        ((SriSurabhiActivity) activity).getSupportActionBar().setTitle("Surabhi Connect");

        AnimUtils.loadAnimate((RelativeLayout) activity.findViewById(R.id.root_anim_view));
    }

    @Override
    public void onLeaveScreen() {
        if (unbinder != null)
            unbinder.unbind();

        FrameLayout applicationParentFrameLayout = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        RelativeLayout homeLayout = (RelativeLayout) applicationParentFrameLayout.findViewById(R.id.surbhi_connection_layout);
        applicationParentFrameLayout.removeView(homeLayout);
    }

    @Override
    public void onClickAction(View view) {

        if (view.getId() == R.id.about) {
            WebViewShowDialog webViewShowDialog = new WebViewShowDialog();
            Bundle bundle = new Bundle();
            bundle.putString("Title", "About the Campaign");
            bundle.putString("file_name", "surabhi-connect-about.html");
            webViewShowDialog.setArguments(bundle);
            webViewShowDialog.show(activity.getFragmentManager(), "Web_view_show");
        } else if (view.getId() == R.id.campaing_videos) {
            WebViewShowDialog webViewShowDialog = new WebViewShowDialog();
            Bundle bundle = new Bundle();
            bundle.putString("Title", "Campaign Videos");
            bundle.putString("file_name", "surabhi-connect-campaign-videos.html");
            webViewShowDialog.setArguments(bundle);
            webViewShowDialog.show(activity.getFragmentManager(), "Web_view_show");
        } else if (view.getId() == R.id.cooinator) {
            SurbhaiCoordinorDialog surbhaiCoordinorDialog = new SurbhaiCoordinorDialog();
            Bundle bundle = new Bundle();
            bundle.putString("Title", "Coordinators");
            bundle.putString("file_name", "surabhi-connect-connect.html");
            surbhaiCoordinorDialog.setArguments(bundle);
            surbhaiCoordinorDialog.show(activity.getFragmentManager(), "Web_view_show");
        } else if (view.getId() == R.id.goshala_directory) {
            WebViewShowDialog webViewShowDialog = new WebViewShowDialog();
            Bundle bundle = new Bundle();
            bundle.putString("Title", "Goshala Directory");
            bundle.putString("file_name", "surabhi-connect-connect.html");
            webViewShowDialog.setArguments(bundle);
            webViewShowDialog.show(activity.getFragmentManager(), "Web_view_show");
        } else if (view.getId() == R.id.other_related) {
            WebViewShowDialog webViewShowDialog = new WebViewShowDialog();
            Bundle bundle = new Bundle();
            bundle.putString("Title", "Other Related Websites");
            bundle.putString("file_name", "surabhi-connect-other-related-websites.html");
            webViewShowDialog.setArguments(bundle);
            webViewShowDialog.show(activity.getFragmentManager(), "Web_view_show");
        } else if (view.getId() == R.id.about_us) {
            WebViewShowDialog webViewShowDialog = new WebViewShowDialog();
            Bundle bundle = new Bundle();
            bundle.putString("Title", "About Us");
            bundle.putString("file_name", "surabhi-connect-about-us-new.html");
            webViewShowDialog.setArguments(bundle);
            webViewShowDialog.show(activity.getFragmentManager(), "Web_view_show");
        } else if (view.getId() == R.id.prabhupada_uvaca_daily_excepts) {
            onSurabhiConnectQuoteAPI("daily");
        } else if (view.getId() == R.id.weekly_quote) {
            onSurabhiConnectQuoteAPI("weekly");
        }
    }

    private void onSurabhiConnectQuoteAPI(String value) {
        CommonUtil.progressShow(activity, activity.getString(R.string.loading_dot));
        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = Common.SURABHI_CONNECT + "type=" + value;
        url = url.replace(" ", "%20");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        CommonUtil.progressDismiss();
                        try {
                            CommonUtil.showBasicAlertWithoutBtn(activity, "", response.getString("quotes_text"));
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
        return ScreenEnums.SCREEN_EXPLORE_SRI_SURBHI;
    }
}
