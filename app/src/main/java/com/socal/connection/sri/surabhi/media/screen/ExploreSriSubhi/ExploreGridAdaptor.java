package com.socal.connection.sri.surabhi.media.screen.ExploreSriSubhi;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.com.single.activity.sample.R;

/**
 * Created by vivek on 06/01/18.
 */


public class ExploreGridAdaptor extends BaseAdapter {
    private Context mContext;
    private ExplorEnum[] explorEnum;

    public ExploreGridAdaptor(Context c, ExplorEnum[] explorEnum) {
        mContext = c;
        this.explorEnum = explorEnum;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return explorEnum.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            grid = inflater.inflate(R.layout.explore_item, null);
            ((TextView) grid.findViewById(R.id.grid_text)).setText(explorEnum[position].getName(mContext));
            ((ImageView) grid.findViewById(R.id.grid_image)).setBackground(mContext.getDrawable(explorEnum[position].getImageId()));
        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}
