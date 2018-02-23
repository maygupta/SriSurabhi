package com.socal.connection.sri.surabhi.media.screen.ExploreSriSubhi;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.demo.com.single.activity.sample.R;
import com.socal.connection.sri.surabhi.media.controller.SriSurabhiActivity;
import com.socal.connection.sri.surabhi.media.screen.News.NewsDetailsDialog;
import com.socal.connection.sri.surabhi.media.screen.Screen;
import com.socal.connection.sri.surabhi.media.screen.Setup.Enum.ColourThemeFileType;
import com.socal.connection.sri.surabhi.media.screen.Setup.Utils.SetupFile;
import com.socal.connection.sri.surabhi.media.utils.AnimUtils;
import com.socal.connection.sri.surabhi.media.utils.Enums.ScreenEnums;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by vivek on 04/01/18.
 */

public class ExploreSriSurbhiScreen extends Screen {

    private Activity activity = null;
    private SetupFile setupFile;
    private FrameLayout rootView;
    private Unbinder unbinder;

    GridView grid;

    public ExploreSriSurbhiScreen(Activity activity, Object data, SetupFile setupFile) {
        this.activity = activity;
        this.setupFile = setupFile;
    }

    @Override
    public void onCurrentScreen() {
        rootView = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.screen_explore_sri_surbhi, null);
        unbinder = ButterKnife.bind(this, view);
        rootView.addView(view);

        LinearLayout relativeLayout = (LinearLayout) rootView.findViewById(R.id.explore_sri_surbhi_layout);
        relativeLayout.setBackgroundColor(ColourThemeFileType.setBackgroundColor(activity, ColourThemeFileType.getType(setupFile.getColourFileType())));

        ((SriSurabhiActivity) activity).getSupportActionBar().setTitle("Explore Sri Surabhi");
        AnimUtils.loadAnimate((RelativeLayout) activity.findViewById(R.id.root_anim_view));

        RelativeLayout relativeLayout1 = (RelativeLayout) activity.findViewById(R.id.root_anim_view);
        AnimUtils.loadAnimate(relativeLayout1);

        ExploreGridAdaptor adapter = new ExploreGridAdaptor(activity, ExplorEnum.values());
        grid = (GridView) rootView.findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == ExplorEnum.ALL_ABOUT_COWS.getIndex()) {
                    SriSurabhiActivity.getInstance().switchScreen(ScreenEnums.SCREEN_ALL_ABOUT_COWS_SCREEN, activity, null, setupFile);
                } else if (position == ExplorEnum.GO_Seva.getIndex()) {
                    SriSurabhiActivity.getInstance().switchScreen(ScreenEnums.SCREEN_GO_Seva, activity, null, setupFile);
                } else if (position == ExplorEnum.PANCHGAVYA.getIndex()) {
                    SriSurabhiActivity.getInstance().switchScreen(ScreenEnums.SCREEN_PANCHGAVYA, activity, null, setupFile);
                } else if (position == ExplorEnum.SURABHI_MEDIA.getIndex()) {
                    SriSurabhiActivity.getInstance().switchScreen(ScreenEnums.SCREEN_SURABHI_MEDIA, activity, null, setupFile);
                } else if (position == ExplorEnum.VRT.getIndex()) {
                    SriSurabhiActivity.getInstance().switchScreen(ScreenEnums.SCREEN_VRT, activity, null, setupFile);
                } else if (position == ExplorEnum.SURABHI_CONNECT.getIndex()) {
                    SriSurabhiActivity.getInstance().switchScreen(ScreenEnums.SCREEN_SURABHI_CONNECT, activity, null, setupFile);
                } else if (position == ExplorEnum.GET_INVOLVED.getIndex()) {
                    SriSurabhiActivity.getInstance().switchScreen(ScreenEnums.SCREEN_GET_INVOLVED, activity, null, setupFile);
                }else if (position == ExplorEnum.NEWS.getIndex()) {
                    SriSurabhiActivity.getInstance().switchScreen(ScreenEnums.SCREEN_NEWS_FEED, activity, null, setupFile);
                }else if (position == ExplorEnum.MEMBERSHIP.getIndex()) {
                    SriSurabhiActivity.getInstance().switchScreen(ScreenEnums.SCREEN_SUBSCRIPTION, activity, null, setupFile);
                }
            }
        });


    }

    @Override
    public void onLeaveScreen() {
        if (unbinder != null)
            unbinder.unbind();

        FrameLayout applicationParentFrameLayout = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        LinearLayout homeLayout = (LinearLayout) applicationParentFrameLayout.findViewById(R.id.explore_sri_surbhi_layout);
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
        return ScreenEnums.SCREEN_EXIT;
    }
}
