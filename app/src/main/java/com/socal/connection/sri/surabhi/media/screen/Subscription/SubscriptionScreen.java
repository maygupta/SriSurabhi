package com.socal.connection.sri.surabhi.media.screen.Subscription;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import com.socal.connection.sri.surabhi.media.controller.SriSurabhiActivity;
import com.socal.connection.sri.surabhi.media.screen.Screen;
import com.socal.connection.sri.surabhi.media.screen.Setup.Enum.ColourThemeFileType;
import com.socal.connection.sri.surabhi.media.screen.Setup.Utils.SetupFile;
import com.socal.connection.sri.surabhi.media.utils.AnimUtils;
import com.socal.connection.sri.surabhi.media.utils.Common;
import com.socal.connection.sri.surabhi.media.utils.CommonUtil;
import com.socal.connection.sri.surabhi.media.utils.Enums.ScreenEnums;
import com.socal.connection.sri.surabhi.media.utils.RecycelViewUtils.SimpleDividerItemDecoration;
import com.socal.connection.sri.surabhi.media.utils.SharedPref;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by vivek on 06/01/18.
 */

public class SubscriptionScreen extends Screen {
    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;

    @BindView(R.id.no_active_planes)
    public TextView noActivePlanesTextView;

    private Activity activity = null;
    private SetupFile setupFile;
    private FrameLayout rootView;
    private Unbinder unbinder;

    private List<SubscriptionModel> subscriptionList = new ArrayList<>();
    private SubscriptionAdaptor mAdapter;

    public SubscriptionScreen(Activity activity, Object data, SetupFile setupFile) {
        this.activity = activity;
        this.setupFile = setupFile;
    }

    @Override
    public void onCurrentScreen() {
        rootView = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.screen_subscription, null);
        unbinder = ButterKnife.bind(this, view);
        rootView.addView(view);

        RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.subscription_layout);
        relativeLayout.setBackgroundColor(ColourThemeFileType.setBackgroundColor(activity, ColourThemeFileType.getType(setupFile.getColourFileType())));

        ((SriSurabhiActivity) activity).getSupportActionBar().setTitle("My Plans");

        AnimUtils.loadAnimate((RelativeLayout) activity.findViewById(R.id.root_anim_view));

        mAdapter = new SubscriptionAdaptor(activity, subscriptionList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(activity));
        recyclerView.setAdapter(mAdapter);
        try {
            onMySubscriptionAPI();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        noActivePlanesTextView.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void onMySubscriptionAPI() throws JSONException {
        CommonUtil.progressShow(activity, activity.getString(R.string.loading_dot));
        RequestQueue queue = Volley.newRequestQueue(activity);
        JSONObject obj = new JSONObject(SharedPref.getString(activity, Common.Login_CREDENTIALS_TAG));
        String url = Common.MY_SUBSCRIPTION_URL + "mine=" + obj.getString("email");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean isRunning = true;
                            int count = 0;
                            while (isRunning) {
                                if (response.has(String.valueOf(count))) {
                                    JSONObject jsonObject = response.getJSONObject(String.valueOf(count));
                                    subscriptionList.add(new SubscriptionModel(
                                            jsonObject.getString("joined"), jsonObject.getString("last_payment"),
                                            jsonObject.getString("plan_id"), jsonObject.getString("title"),
                                            jsonObject.getString("rate")));
                                    ++count;
                                } else {
                                    isRunning = false;
                                }
                            }
                            if(subscriptionList.size()==0){
                                noActivePlanesTextView.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.INVISIBLE);
                            }
                            mAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        CommonUtil.progressDismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "Error: " + error.getMessage());
                // hide the progress dialog
                CommonUtil.progressDismiss();
                CommonUtil.showBasicAlert(activity, activity.getString(R.string.error_dot), error.getMessage());
            }
        });

        queue.add(jsonObjReq);
    }

    @Override
    public void onLeaveScreen() {
        if (unbinder != null)
            unbinder.unbind();

        FrameLayout applicationParentFrameLayout = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        RelativeLayout homeLayout = (RelativeLayout) applicationParentFrameLayout.findViewById(R.id.subscription_layout);
        applicationParentFrameLayout.removeView(homeLayout);
    }

    @Override
    public void onClickAction(View view) {

    }

    @Override
    public boolean onCreate(Bundle savedInstanceState) {
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        activity.invalidateOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ((SriSurabhiActivity) activity).getMenuInflater().inflate(R.menu.menu, menu);
        menu.findItem(R.id.menuExit).setVisible(false);
        menu.findItem(R.id.menuAbout).setVisible(false);
        menu.findItem(R.id.menuSetup).setVisible(false);
        menu.findItem(R.id.updateLicence).setVisible(false);
        menu.findItem(R.id.menu_view_all).setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SriSurabhiActivity.getInstance().switchScreen(ScreenEnums.SCREEN_SUBSCRIPTION_MEMBER_SHIPS, activity, null, setupFile);
        return onCreateOptionsMenu(item.getSubMenu());
    }

    @Override
    public ScreenEnums onBackPressed() {
        return ScreenEnums.SCREEN_HOME;
    }
}
