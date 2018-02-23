package com.socal.connection.sri.surabhi.media.screen.News;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.demo.com.single.activity.sample.R;
import com.socal.connection.sri.surabhi.media.controller.SriSurabhiActivity;
import com.socal.connection.sri.surabhi.media.screen.Screen;
import com.socal.connection.sri.surabhi.media.screen.Setup.Utils.SetupFile;
import com.socal.connection.sri.surabhi.media.utils.Common;
import com.socal.connection.sri.surabhi.media.utils.CommonUtil;
import com.socal.connection.sri.surabhi.media.utils.Enums.ScreenEnums;
import com.socal.connection.sri.surabhi.media.utils.RecycelViewUtils.RecyclerItemClickListener;
import com.socal.connection.sri.surabhi.media.utils.RecycelViewUtils.SimpleDividerItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by vivek on 30/01/18.
 */

public class NewScreen extends Screen {
    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;

    private Activity activity = null;
    private SetupFile setupFile;
    private FrameLayout rootView;
    private Unbinder unbinder;

    private List<NesModel> newsList = new ArrayList<>();
    private NewsAdaptor mAdapter;

    public NewScreen(Activity activity, Object data, SetupFile setupFile) {
        this.activity = activity;
        this.setupFile = setupFile;
    }

    @Override
    public void onCurrentScreen() {
        rootView = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.screen_new, null);
        unbinder = ButterKnife.bind(this, view);
        rootView.addView(view);

        ((SriSurabhiActivity) activity).getSupportActionBar().setTitle("News");

        mAdapter = new NewsAdaptor(activity, newsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(activity));
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(activity, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        NesModel nesModel=newsList.get(position);
                        NewsDetailsDialog newsDetailsDialog=new NewsDetailsDialog();
                        Bundle bundle=new Bundle();
                        bundle.putString("Image",nesModel.getImg());
                        bundle.putString("Title",nesModel.getHeadLine());
                        bundle.putString("Details",nesModel.getNews());
                        bundle.putString("Author",nesModel.getAuthor());
                        bundle.putString("Times",nesModel.getTimings());
                        newsDetailsDialog.setArguments(bundle);
                        newsDetailsDialog.show(activity.getFragmentManager(),"News feed");
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        try {
            onSignUpAPIAPI();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void onSignUpAPIAPI() throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(activity);
        CommonUtil.progressShow(activity, activity.getString(R.string.loading_dot));
        String url = Common.NEWS_LIST;

        JsonArrayRequest jsonObjReq = new JsonArrayRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++)
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                newsList.add(new NesModel(jsonObject.getString("headline"),
                                        jsonObject.getString("img"),
                                        jsonObject.getString("news"),
                                        jsonObject.getString("author"),
                                        jsonObject.getString("timings_ago")));
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                        mAdapter.notifyDataSetChanged();

                        CommonUtil.progressDismiss();
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        CommonUtil.progressDismiss();
                        CommonUtil.showBasicAlert(activity, activity.getString(R.string.error_dot), error.getMessage());
                        VolleyLog.d("", "Error: " + error.getMessage());
                        // hide the progress dialog
                    }
                });

        queue.add(jsonObjReq);
    }

    /*
    *
    * */
    @Override
    public void onLeaveScreen() {
        if (unbinder != null)
            unbinder.unbind();

        FrameLayout applicationParentFrameLayout = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        LinearLayout homeLayout = (LinearLayout) applicationParentFrameLayout.findViewById(R.id.new_feed_layout);
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
        return ScreenEnums.SCREEN_EXPLORE_SRI_SURBHI;
    }
}
