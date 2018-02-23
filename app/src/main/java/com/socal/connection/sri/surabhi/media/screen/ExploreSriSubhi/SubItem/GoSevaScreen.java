package com.socal.connection.sri.surabhi.media.screen.ExploreSriSubhi.SubItem;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.demo.com.single.activity.sample.R;
import com.socal.connection.sri.surabhi.media.controller.SriSurabhiActivity;
import com.socal.connection.sri.surabhi.media.screen.ExploreSriSubhi.dialog.WebViewShowDialog;
import com.socal.connection.sri.surabhi.media.screen.Screen;
import com.socal.connection.sri.surabhi.media.screen.Setup.Utils.SetupFile;
import com.socal.connection.sri.surabhi.media.utils.AnimUtils;
import com.socal.connection.sri.surabhi.media.utils.Enums.ScreenEnums;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by vivek on 10/01/18.
 */

public class GoSevaScreen extends Screen {
    private Activity activity = null;
    private SetupFile setupFile;
    private FrameLayout rootView;
    private Unbinder unbinder;

    public GoSevaScreen(Activity activity, Object data, SetupFile setupFile) {
        this.activity = activity;
        this.setupFile = setupFile;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCurrentScreen() {
        rootView = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.screen_go_seva, null);
        unbinder = ButterKnife.bind(this, view);
        rootView.addView(view);

        ((SriSurabhiActivity) activity).getSupportActionBar().setTitle("Go Seva");

        AnimUtils.loadAnimate((RelativeLayout) activity.findViewById(R.id.root_anim_view));

    }

    @Override
    public void onLeaveScreen() {
        if (unbinder != null)
            unbinder.unbind();

        FrameLayout applicationParentFrameLayout = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        RelativeLayout homeLayout = (RelativeLayout) applicationParentFrameLayout.findViewById(R.id.go_gewa_layout);
        applicationParentFrameLayout.removeView(homeLayout);
    }

    @Override
    public void onClickAction(View view) {
        WebViewShowDialog webViewShowDialog = new WebViewShowDialog();
        if (view.getId() == R.id.best_practice) {
            Bundle bundle=new Bundle();
            bundle.putString("Title","Best Practices");
            bundle.putString("file_name","gosewa-best-practice.html");
            webViewShowDialog.setArguments(bundle);

        } else if (view.getId() == R.id.go_puja) {
            Bundle bundle=new Bundle();
            bundle.putString("Title","Go Puja");
            bundle.putString("file_name","gosewa-gopuja.html");
            webViewShowDialog.setArguments(bundle);

        } else if (view.getId() == R.id.sacred_oath) {
            Bundle bundle=new Bundle();
            bundle.putString("Title","Sacred Oath");
            bundle.putString("file_name","gosewa-sacred-oath.html");
            webViewShowDialog.setArguments(bundle);

        } else if (view.getId() == R.id.sacred_prayer) {
            Bundle bundle=new Bundle();
            bundle.putString("Title","Sacred Prayer");
            bundle.putString("file_name","gosewa-sacred-prayer.html");
            webViewShowDialog.setArguments(bundle);

        }
        webViewShowDialog.show(activity.getFragmentManager(), "Web_view_show");
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
