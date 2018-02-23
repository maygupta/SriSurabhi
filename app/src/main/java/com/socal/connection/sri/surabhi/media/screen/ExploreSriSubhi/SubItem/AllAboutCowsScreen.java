package com.socal.connection.sri.surabhi.media.screen.ExploreSriSubhi.SubItem;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.demo.com.single.activity.sample.R;
import com.nineoldandroids.animation.ObjectAnimator;
import com.socal.connection.sri.surabhi.media.controller.SriSurabhiActivity;
import com.socal.connection.sri.surabhi.media.screen.ExploreSriSubhi.dialog.WebViewShowDialog;
import com.socal.connection.sri.surabhi.media.screen.Screen;
import com.socal.connection.sri.surabhi.media.screen.Setup.Enum.ColourThemeFileType;
import com.socal.connection.sri.surabhi.media.screen.Setup.Utils.SetupFile;
import com.socal.connection.sri.surabhi.media.utils.AnimUtils;
import com.socal.connection.sri.surabhi.media.utils.Enums.ScreenEnums;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by vivek on 10/01/18.
 */

public class AllAboutCowsScreen extends Screen {
    private Activity activity = null;
    private SetupFile setupFile;
    private FrameLayout rootView;
    private Unbinder unbinder;

    public AllAboutCowsScreen(Activity activity, Object data, SetupFile setupFile) {
        this.activity = activity;
        this.setupFile = setupFile;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCurrentScreen() {

        rootView = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.screen_all_about_cows, null);
        unbinder = ButterKnife.bind(this, view);
        rootView.addView(view);

        ((SriSurabhiActivity) activity).getSupportActionBar().setTitle("All about Cows");

        AnimUtils.loadAnimate((RelativeLayout) activity.findViewById(R.id.root_anim_view));
    }

    @Override
    public void onLeaveScreen() {
        if (unbinder != null)
            unbinder.unbind();

        FrameLayout applicationParentFrameLayout = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        RelativeLayout homeLayout = (RelativeLayout) applicationParentFrameLayout.findViewById(R.id.all_about_cows_layout);
        applicationParentFrameLayout.removeView(homeLayout);
    }

    @Override
    public void onClickAction(View view) {
        WebViewShowDialog webViewShowDialog = new WebViewShowDialog();
        if (view.getId() == R.id.all_about_cows_a1_vs_a2) {
            Bundle bundle = new Bundle();
            bundle.putString("Title", "A1 Milk vs. A2 Milk");
            bundle.putString("file_name", "all-about-cows-a1-vs-a2.html");
            webViewShowDialog.setArguments(bundle);
        } else if (view.getId() == R.id.all_about_cows_desi_cow) {
            Bundle bundle = new Bundle();
            bundle.putString("Title", "Desi cow");
            bundle.putString("file_name", "all-about-cows-desi-cow.html");
            webViewShowDialog.setArguments(bundle);
        } else if (view.getId() == R.id.all_about_cows_Indigenous) {
            Bundle bundle = new Bundle();
            bundle.putString("Title", "Indigenous Cows vs. Exotic Cows");
            bundle.putString("file_name", "all-about-cows-Indigenous-Cows-vs-exotic-cows.html");
            webViewShowDialog.setArguments(bundle);
        } /*else if (view.getId() == R.id.all_about_cows_quotes) {
            Bundle bundle = new Bundle();
            bundle.putString("Title", "Quotes");
            bundle.putString("file_name", "all-about-cows-quotes.html");
            webViewShowDialog.setArguments(bundle);
        }*/
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
