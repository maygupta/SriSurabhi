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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.demo.com.single.activity.sample.R;
import com.socal.connection.sri.surabhi.media.controller.SriSurabhiActivity;
import com.socal.connection.sri.surabhi.media.screen.ExploreSriSubhi.dialog.ExploreDialogFragment;
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

public class PanchgavyaScreen extends Screen {
    private Activity activity = null;
    private SetupFile setupFile;
    private FrameLayout rootView;
    private Unbinder unbinder;

    public PanchgavyaScreen(Activity activity, Object data, SetupFile setupFile) {
        this.activity = activity;
        this.setupFile = setupFile;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCurrentScreen() {
        rootView = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.screen_panchgavya, null);
        unbinder = ButterKnife.bind(this, view);
        rootView.addView(view);

        ((SriSurabhiActivity) activity).getSupportActionBar().setTitle("Panchgavya");

        LinearLayout relativeLayout = (LinearLayout) rootView.findViewById(R.id.panchgavya_layout);
        relativeLayout.setBackgroundColor(ColourThemeFileType.setBackgroundColor(activity,
                ColourThemeFileType.getType(setupFile.getColourFileType())));
        AnimUtils.loadAnimate((RelativeLayout) activity.findViewById(R.id.root_anim_view));

    }

    @Override
    public void onLeaveScreen() {
        if (unbinder != null)
            unbinder.unbind();

        FrameLayout applicationParentFrameLayout = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        LinearLayout homeLayout = (LinearLayout) applicationParentFrameLayout.findViewById(R.id.panchgavya_layout);
        applicationParentFrameLayout.removeView(homeLayout);
    }

    @Override
    public void onClickAction(View view) {
        if (view.getId() == R.id.cow_dung_tv) {
            ExploreDialogFragment alertDialogFragment = new ExploreDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("Title", activity.getString(R.string.cow_dung));
            bundle.putString("Message", activity.getString(R.string.panchgyana_cows_dung_text));
            alertDialogFragment.setArguments(bundle);
            alertDialogFragment.show(  activity.getFragmentManager(), "fragment_edit_name");
        } else if (view.getId() == R.id.cow_urine_tv) {
            ExploreDialogFragment alertDialogFragment = new ExploreDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("Title", activity.getString(R.string.cow_urine));
            bundle.putString("Message", activity.getString(R.string.panchgyana_cow_urine_text));
            alertDialogFragment.setArguments(bundle);
            alertDialogFragment.show(  activity.getFragmentManager(), "fragment_edit_name");
        } else if (view.getId() == R.id.cow_milk_tv) {
            ExploreDialogFragment alertDialogFragment = new ExploreDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("Title", activity.getString(R.string.cow_milk));
            bundle.putString("Message", activity.getString(R.string.panchgyana_cow_milk_text));
            alertDialogFragment.setArguments(bundle);
            alertDialogFragment.show(  activity.getFragmentManager(), "fragment_edit_name");
        } else if (view.getId() == R.id.cow_ghee_tv) {
            ExploreDialogFragment alertDialogFragment = new ExploreDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("Title", activity.getString(R.string.cow_ghee));
            bundle.putString("Message", activity.getString(R.string.panchgyana_cow_ghee_text));
            alertDialogFragment.setArguments(bundle);
            alertDialogFragment.show(  activity.getFragmentManager(), "fragment_edit_name");
        } else if (view.getId() == R.id.cow_dahi_tv) {
            ExploreDialogFragment alertDialogFragment = new ExploreDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("Title", activity.getString(R.string.cow_dahi));
            bundle.putString("Message", activity.getString(R.string.panchgyana_cow_dahi_text));
            alertDialogFragment.setArguments(bundle);
            alertDialogFragment.show(  activity.getFragmentManager(), "fragment_edit_name");
        }else if (view.getId() == R.id.benifits_tv) {
            ExploreDialogFragment alertDialogFragment=new ExploreDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("Title", activity.getString(R.string.benifits));
            bundle.putString("Message", activity.getString(R.string.panchgyana_cow_benifits_text));
            alertDialogFragment.setArguments(bundle);
            alertDialogFragment.show(  activity.getFragmentManager(), "fragment_edit_name");
        }

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
