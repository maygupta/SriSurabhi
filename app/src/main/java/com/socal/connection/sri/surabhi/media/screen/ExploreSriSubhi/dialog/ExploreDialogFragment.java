package com.socal.connection.sri.surabhi.media.screen.ExploreSriSubhi.dialog;

/**
 * Created by vivek on 10/01/18.
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class ExploreDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                // set dialog icon
                .setIcon(android.R.drawable.stat_notify_error)
                // set Dialog Title
                .setTitle(getArguments().getString("Title"))
                // Set Dialog Message
                .setMessage(getArguments().getString("Message"))
/*
* bundle.putString(, activity.getString(R.string.cow_dung));
            bundle.putString(, activity.getString(R.string.panchgyana_cows_dung_text));
* */
                // positive button
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(getActivity(), "Pressed OK", Toast.LENGTH_SHORT).show();
                        dismiss();
                    }
                }).create();
                // negative button
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
////                        Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_SHORT).show();
//                    }
//                }).create();
    }
}
