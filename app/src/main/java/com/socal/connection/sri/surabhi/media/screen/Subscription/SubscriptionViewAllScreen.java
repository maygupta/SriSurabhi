package com.socal.connection.sri.surabhi.media.screen.Subscription;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

public class SubscriptionViewAllScreen extends Screen {
    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;

    private Activity activity = null;
    private SetupFile setupFile;
    private FrameLayout rootView;
    private Unbinder unbinder;

    private List<SubscriptionViewAllModel> subscriptionViewAllList = new ArrayList<>();
    private SubscriptionViewAllAdaptor mAdapter;

    public SubscriptionViewAllScreen(Activity activity, Object data, SetupFile setupFile) {
        this.activity = activity;
        this.setupFile = setupFile;
    }

    @Override
    public void onCurrentScreen() {
        rootView = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.screen_subscription_member_ships, null);
        unbinder = ButterKnife.bind(this, view);
        rootView.addView(view);

        LinearLayout relativeLayout = (LinearLayout) rootView.findViewById(R.id.subscription_member_ship_layout);
        relativeLayout.setBackgroundColor(ColourThemeFileType.setBackgroundColor(activity, ColourThemeFileType.getType(setupFile.getColourFileType())));

        ((SriSurabhiActivity) activity).getSupportActionBar().setTitle("Surabhi Membership");

        AnimUtils.loadAnimate((RelativeLayout) activity.findViewById(R.id.root_anim_view));

        mAdapter = new SubscriptionViewAllAdaptor(activity,subscriptionViewAllList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(activity));
        recyclerView.setAdapter(mAdapter);
        try {
            onSubscriptionViewAllAPI();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void onSubscriptionViewAllAPI() throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(activity);
        JSONObject obj = new JSONObject(SharedPref.getString(activity, Common.Login_CREDENTIALS_TAG));
        String url = Common.SUBSCRIPTION_VIEW_ALL_URL + "show_all=1&email=" + obj.getString("email");
        CommonUtil.progressShow(activity, activity.getString(R.string.loading_dot));
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
                                    Log.i("eclipse", "jsonObject=" + jsonObject.toString());
                                    subscriptionViewAllList.add(new SubscriptionViewAllModel(
                                            jsonObject.getString("plan_id"),
                                            jsonObject.getString("rate"),
                                            jsonObject.getString("plan_type"),
                                            jsonObject.getString("title"),
                                            jsonObject.getString("subscriber_count"),
                                            jsonObject.getString("my_plan"),
                                            jsonObject.getString("overview")));
                                    ++count;
                                } else {
                                    isRunning = false;
                                }
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
                CommonUtil.progressDismiss();
                CommonUtil.showBasicAlert(activity, activity.getString(R.string.error_dot), error.getMessage());
                // hide the progress dialog
            }
        });

        queue.add(jsonObjReq);
    }

    @Override
    public void onLeaveScreen() {
        if (unbinder != null)
            unbinder.unbind();

        FrameLayout applicationParentFrameLayout = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        LinearLayout homeLayout = (LinearLayout) applicationParentFrameLayout.findViewById(R.id.subscription_member_ship_layout);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    @Override
    public ScreenEnums onBackPressed() {
        return ScreenEnums.SCREEN_HOME;
    }
}
