package com.socal.connection.sri.surabhi.media.screen.Home;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.demo.com.single.activity.sample.R;
import com.socal.connection.sri.surabhi.media.controller.SriSurabhiActivity;
import com.socal.connection.sri.surabhi.media.screen.Screen;
import com.socal.connection.sri.surabhi.media.screen.Setup.Enum.ColourThemeFileType;
import com.socal.connection.sri.surabhi.media.screen.Setup.Utils.SetupFile;
import com.socal.connection.sri.surabhi.media.utils.AnimUtils;
import com.socal.connection.sri.surabhi.media.utils.Enums.ScreenEnums;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public class HomeScreen extends Screen {
    private Unbinder unbinder;
    private Activity activity = null;
    private SetupFile setupFile;
    private FrameLayout rootView;

    public HomeScreen(Activity activity, Object data, SetupFile setupFile) {
        this.activity = activity;
        this.setupFile = setupFile;
    }

    @Override
    public void onClickAction(View view) {

    }

    @Override
    public boolean onCreate(Bundle savedInstanceState) {
        return true;
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onCurrentScreen() {

        rootView = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.screen_home, null);
        unbinder = ButterKnife.bind(this, view);
        rootView.addView(view);
        LinearLayout relativeLayout = (LinearLayout) rootView.findViewById(R.id.home_screen_layout);
        relativeLayout.setBackgroundColor(ColourThemeFileType.setBackgroundColor(activity,
                ColourThemeFileType.getType(setupFile.getColourFileType())));

        AnimUtils.loadAnimate((RelativeLayout) activity.findViewById(R.id.root_anim_view));

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
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public ScreenEnums onBackPressed() {
        return ScreenEnums.SCREEN_EXIT;
    }

    @Override
    public void onLeaveScreen() {
        if (unbinder != null)
            unbinder.unbind();

        FrameLayout applicationParentFrameLayout = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        LinearLayout homeLayout = (LinearLayout) applicationParentFrameLayout.findViewById(R.id.home_screen_layout);
        applicationParentFrameLayout.removeView(homeLayout);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onDestroy() {

    }

}
