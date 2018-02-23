package com.socal.connection.sri.surabhi.media.screen.ExploreSriSubhi.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.demo.com.single.activity.sample.R;
import com.socal.connection.sri.surabhi.media.screen.Setup.Enum.ColourThemeFileType;
import com.socal.connection.sri.surabhi.media.screen.Setup.Utils.SetupFile;
import com.socal.connection.sri.surabhi.media.utils.Common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import im.delight.android.webview.AdvancedWebView;

/**
 * Created by vivek on 11/01/18.
 */

public class WebViewShowDialog extends DialogFragment {
    private View root;
    private AdvancedWebView webView;
    private SetupFile setupFile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MY_DIALOG);
        setupFile = new SetupFile.SetupBuilder().build();
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog d = getDialog();
        if (d != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            d.getWindow().setLayout(width, height);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.dialog_export_sri_fragment, container, false);
        Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar);
        toolbar.setTitle(getArguments().getString("Title"));
        toolbar.setTitleTextColor(ColourThemeFileType.setTextColor(getActivity(),
                ColourThemeFileType.getType(setupFile.getColourFileType())));
        webView = (AdvancedWebView) root.findViewById(R.id.web_view_layout);
        LinearLayout linearLayout = (LinearLayout) root.findViewById(R.id.dialog_export_sri_layout);
        linearLayout.setBackgroundColor(ColourThemeFileType.setBackgroundColor(getActivity(),
                ColourThemeFileType.getType(setupFile.getColourFileType())));
        webView.setBackgroundColor(ColourThemeFileType.setBackgroundColor(getActivity(),
                ColourThemeFileType.getType(setupFile.getColourFileType())));
        webView.loadUrl("file:///android_asset/"+getArguments().getString("file_name"));
        webView.requestFocus();
        return root;
    }

}