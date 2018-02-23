package com.socal.connection.sri.surabhi.media.screen.ExploreSriSubhi;

import android.content.Context;

import com.demo.com.single.activity.sample.R;

/**
 * Created by vivek on 10/01/18.
 */

public enum ExplorEnum {
    ALL_ABOUT_COWS(0, R.string.All_about_Cows, R.drawable.all_about_cows_img),
    GO_Seva(1, R.string.Go_Seva, R.drawable.gosewa_img),
    PANCHGAVYA(2, R.string.Panchgavya, R.drawable.panchgavya_img),
    SURABHI_MEDIA(3, R.string.Surabhi_Media, R.drawable.video_player_outline_img),
    VRT(4, R.string.VRT, R.drawable.vrt_img),
    SURABHI_CONNECT(5, R.string.Surabhi_Connect, R.drawable.surabhi_connect_img),
    GET_INVOLVED(6, R.string.Get_Involved, R.drawable.get_involved_img),
    NEWS(7, R.string.news, R.drawable.news_img),
    MEMBERSHIP(8, R.string.campaign_membership, R.drawable.membership_img);

    private int index;
    private int id;
    private int imgId;

    ExplorEnum(int index, int id, int imgId) {
        this.index = index;
        this.id = id;
        this.imgId = imgId;
    }

    public static ExplorEnum getType(int position) {
        switch (position) {
            case 0:
                return ALL_ABOUT_COWS;
            case 1:
                return GO_Seva;
            case 2:
                return PANCHGAVYA;
            case 3:
                return SURABHI_MEDIA;
            case 4:
                return VRT;
            case 5:
                return SURABHI_CONNECT;
            case 6:
                return GET_INVOLVED;
            case 7:
                return NEWS;
            case 8:
                return MEMBERSHIP;
            default:
                throw new IllegalArgumentException(position + " is not a valid");
        }
    }

    public int getIndex() {
        return this.index;
    }

    public int getImageId() {
        return this.imgId;
    }

    public String getName(Context context) {
        return context.getString(this.id);
    }
}
