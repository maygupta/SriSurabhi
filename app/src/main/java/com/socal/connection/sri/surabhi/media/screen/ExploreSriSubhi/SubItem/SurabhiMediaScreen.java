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
import com.socal.connection.sri.surabhi.media.screen.ExploreSriSubhi.dialog.SurbhaiCoordinorDialog;
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

public class SurabhiMediaScreen extends Screen {
    private Activity activity = null;
    private SetupFile setupFile;
    private FrameLayout rootView;
    private Unbinder unbinder;

    public SurabhiMediaScreen(Activity activity, Object data, SetupFile setupFile) {
        this.activity = activity;
        this.setupFile = setupFile;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCurrentScreen() {
        rootView = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.screen_surabhi_media, null);
        unbinder = ButterKnife.bind(this, view);
        rootView.addView(view);

        ((SriSurabhiActivity) activity).getSupportActionBar().setTitle("Surabhi Media");

        AnimUtils.loadAnimate((RelativeLayout) activity.findViewById(R.id.root_anim_view));
    }

    @Override
    public void onLeaveScreen() {
        if (unbinder != null)
            unbinder.unbind();

        FrameLayout applicationParentFrameLayout = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        RelativeLayout homeLayout = (RelativeLayout) applicationParentFrameLayout.findViewById(R.id.surabhi_media_layout);
        applicationParentFrameLayout.removeView(homeLayout);
    }

    @Override
    public void onClickAction(View view) {
        if (view.getId() == R.id.books_tv) {
            WebViewShowDialog surbhaiCoordinorDialog=new WebViewShowDialog();
            Bundle bundle = new Bundle();
            bundle.putString("Title", "Books");
            bundle.putString("file_name", "surabhi-book.html");
            surbhaiCoordinorDialog.setArguments(bundle);
            surbhaiCoordinorDialog.show(activity.getFragmentManager(), "Web_view_show");
        } else if (view.getId() == R.id.presentations_books) {
            WebViewShowDialog surbhaiCoordinorDialog=new WebViewShowDialog();
            Bundle bundle = new Bundle();
            bundle.putString("Title", "Powerpoints - Golden Jubilee");
            bundle.putString("file_name", "surabhi-ppt.html");
            surbhaiCoordinorDialog.setArguments(bundle);
            surbhaiCoordinorDialog.show(activity.getFragmentManager(), "Web_view_show");
        }else if (view.getId() == R.id.reports_tv) {
            WebViewShowDialog surbhaiCoordinorDialog=new WebViewShowDialog();
            Bundle bundle = new Bundle();
            bundle.putString("Title", "Quarterly Reports");
            bundle.putString("file_name", "surabhi-report.html");
            surbhaiCoordinorDialog.setArguments(bundle);
            surbhaiCoordinorDialog.show(activity.getFragmentManager(), "Web_view_show");
        }else if (view.getId() == R.id.video) {
            WebViewShowDialog surbhaiCoordinorDialog=new WebViewShowDialog();
            Bundle bundle = new Bundle();
            bundle.putString("Title", "Videos");
            bundle.putString("file_name", "surabhi-video.html");
            surbhaiCoordinorDialog.setArguments(bundle);
            surbhaiCoordinorDialog.show(activity.getFragmentManager(), "Web_view_show");
        }else if (view.getId() == R.id.pamplet) {
            WebViewShowDialog surbhaiCoordinorDialog=new WebViewShowDialog();
            Bundle bundle = new Bundle();
            bundle.putString("Title", "Pamphlets");
            bundle.putString("file_name", "surabhi-pamplet.html");
            surbhaiCoordinorDialog.setArguments(bundle);
            surbhaiCoordinorDialog.show(activity.getFragmentManager(), "Web_view_show");
        }else if (view.getId() == R.id.sri_surabhi_mantra_and_other_audios) {
            WebViewShowDialog surbhaiCoordinorDialog=new WebViewShowDialog();
            Bundle bundle = new Bundle();
            bundle.putString("Title", "Sri Surabhi Mantra and other Audio");
            bundle.putString("file_name", "surabhi-audio.html");
            surbhaiCoordinorDialog.setArguments(bundle);
            surbhaiCoordinorDialog.show(activity.getFragmentManager(), "Web_view_show");
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
