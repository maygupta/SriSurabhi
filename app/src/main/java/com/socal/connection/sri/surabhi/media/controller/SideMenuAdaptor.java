package com.socal.connection.sri.surabhi.media.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.com.single.activity.sample.R;
import com.socal.connection.sri.surabhi.media.controller.Enum.SideMenuEnum;

import java.util.ArrayList;

/**
 * Created by vivek on 07/01/18.
 */


public class SideMenuAdaptor<S> extends ArrayAdapter<SideMenuModel> {
    Context context;

    public SideMenuAdaptor(Context context, ArrayList<SideMenuModel> users) {
        super(context, 0, users);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        SideMenuModel user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.side_menu_listview, parent, false);
        }
        // Lookup view for data population
        ImageView sideMenuItemImg = (ImageView) convertView.findViewById(R.id.side_menu_item_img);
        TextView sideMenuItemTextView = (TextView) convertView.findViewById(R.id.side_menu_item_text_view);
        // Populate the data into the template view using the data object
        sideMenuItemImg.setBackgroundResource(SideMenuEnum.getType(position).getImageName());
        sideMenuItemTextView.setText(user.getName());
        // Return the completed view to render on screen
        return convertView;
    }
}
