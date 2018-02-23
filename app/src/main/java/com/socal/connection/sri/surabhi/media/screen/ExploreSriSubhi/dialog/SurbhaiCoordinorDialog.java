package com.socal.connection.sri.surabhi.media.screen.ExploreSriSubhi.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.com.single.activity.sample.R;
import com.socal.connection.sri.surabhi.media.screen.Setup.Enum.ColourThemeFileType;
import com.socal.connection.sri.surabhi.media.screen.Setup.Utils.SetupFile;

/**
 * Created by vivek on 12/01/18.
 */

public class SurbhaiCoordinorDialog extends DialogFragment {
    private View root;
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
        root = inflater.inflate(R.layout.dialog_surnhai_sri_coodinator_fragment, container, false);
        Toolbar toolbar = (Toolbar) root.findViewById(R.id.toolbar);
        toolbar.setTitle(getArguments().getString("Title"));
        toolbar.setTitleTextColor(ColourThemeFileType.setTextColor(getActivity(),
                ColourThemeFileType.getType(setupFile.getColourFileType())));

        return root;
    }

}