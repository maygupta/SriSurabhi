package com.socal.connection.sri.surabhi.media.screen.ExploreSriSubhi.SubItem;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.demo.com.single.activity.sample.R;
import com.socal.connection.sri.surabhi.media.controller.SriSurabhiActivity;
import com.socal.connection.sri.surabhi.media.screen.Screen;
import com.socal.connection.sri.surabhi.media.screen.Setup.Enum.ColourThemeFileType;
import com.socal.connection.sri.surabhi.media.screen.Setup.Utils.SetupFile;
import com.socal.connection.sri.surabhi.media.utils.AnimUtils;
import com.socal.connection.sri.surabhi.media.utils.Common;
import com.socal.connection.sri.surabhi.media.utils.Enums.ScreenEnums;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import im.delight.android.webview.AdvancedWebView;

/**
 * Created by vivek on 10/01/18.
 */

public class VRTScreen extends Screen {
    private Activity activity = null;
    private SetupFile setupFile;
    private FrameLayout rootView;
    private Unbinder unbinder;
    private AdvancedWebView webView;

    public VRTScreen(Activity activity, Object data, SetupFile setupFile) {
        this.activity = activity;
        this.setupFile = setupFile;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCurrentScreen() {
        rootView = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.screen_vrt, null);
        unbinder = ButterKnife.bind(this, view);
        rootView.addView(view);

        ((SriSurabhiActivity) activity).getSupportActionBar().setTitle("VRT");

        webView = (AdvancedWebView) rootView.findViewById(R.id.web_view_layout);
        webView.setBackgroundColor(ColourThemeFileType.setBackgroundColor(activity,
                ColourThemeFileType.getType(setupFile.getColourFileType())));
        webView.loadUrl("file:///android_asset/"+"vrt.html");

        LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.vrt_layout);
        linearLayout.setBackgroundColor(ColourThemeFileType.setBackgroundColor(activity,
                ColourThemeFileType.getType(setupFile.getColourFileType())));

        AnimUtils.loadAnimate((RelativeLayout) activity.findViewById(R.id.root_anim_view));
    }

    @Override
    public void onLeaveScreen() {
        if (unbinder != null)
            unbinder.unbind();

        FrameLayout applicationParentFrameLayout = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        LinearLayout homeLayout = (LinearLayout) applicationParentFrameLayout.findViewById(R.id.vrt_layout);
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
