package com.socal.connection.sri.surabhi.media.screen.News;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.com.single.activity.sample.R;
import com.socal.connection.sri.surabhi.media.screen.Setup.Utils.SetupFile;
import com.squareup.picasso.Picasso;

import im.delight.android.webview.AdvancedWebView;

/**
 * Created by vivek on 30/01/18.
 */

public class NewsDetailsDialog extends DialogFragment {

    private View root;
    private SetupFile setupFile;
    private String Image;
    private String Title;
    private String Details;
    private String Author;
    private String Times;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MY_DIALOG);
        setupFile = new SetupFile.SetupBuilder().build();
        Image = getArguments().getString("Image");
        Title = getArguments().getString("Title");
        Details = getArguments().getString("Details");
        Author = getArguments().getString("Author");
        Times = getArguments().getString("Times");
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
        root = inflater.inflate(R.layout.screen_new_details, container, false);
        TextView textView = root.findViewById(R.id.news_details_title);
        textView.setText(Title);

        textView = root.findViewById(R.id.details_text_view);
        textView.setText(Details);

        textView = root.findViewById(R.id.author_text_view);
        textView.setText("Author : " + Author);

        textView = root.findViewById(R.id.time_text_view);
        textView.setText(Times);

        ImageView imageView = root.findViewById(R.id.new_held_line_img);
        Picasso.with(getActivity()).load(Image).into(imageView);
        return root;
    }

}
