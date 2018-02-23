package com.socal.connection.sri.surabhi.media.controller.Enum;

import android.app.Activity;
import android.content.Context;

import com.demo.com.single.activity.sample.R;
import com.socal.connection.sri.surabhi.media.utils.SharedPref;

/**
 * Created by vivek on 05/01/18.
 */

public enum SideMenuEnum {
    USER_NAME(0, R.drawable.user_icon, ""),
    EXPLORE_SRI_SURABHI(1, R.drawable.explore_icon, "Explore Sri Surabhi"),
    EVENTS(2, R.drawable.event_side_menu, "Upcoming Events"),
    SUBSCRIPTION(3, R.drawable.subscription_icon, "Subscription"),
    SUPPORT(4, R.drawable.notification_icon, "Support"),
    REFER_A_FRIEND(5, R.drawable.refer_friend, "Refer a friend"),
    LOG_OUT(6, R.drawable.user_icon, "Logout");

    private int _value;
    private int _id;
    private String _strId;

    private SideMenuEnum(int value, int id, String strId) {
        this._value = value;
        this._id = id;
        this._strId = strId;
    }

    public static SideMenuEnum getType(int value) {
        switch (value) {
            case 0:
                return USER_NAME;

            case 1:

                return EXPLORE_SRI_SURABHI;

            case 2:

                return EVENTS;

            case 3:

                return SUBSCRIPTION;

            case 4:

                return SUPPORT;

            case 5:

                return REFER_A_FRIEND;

            case 6:

                return LOG_OUT;

            default:
                throw new IllegalArgumentException(value + " is not a valid Pixel  file type ");
        }
    }

    public static String[] getList(Activity activity) {
        return new String[]{SharedPref.getString(activity, "CURRENT_USER_NAME"), EXPLORE_SRI_SURABHI.getName(activity), EVENTS.getName(activity),
                SUBSCRIPTION.getName(activity), SUPPORT.getName(activity), REFER_A_FRIEND.getName(activity), LOG_OUT.getName(activity)};
    }

    public int getValue() {
        return this._value;
    }

    public int getImageName() {
        return this._id;
    }

    public String getName(Context context) {
        return this._strId;
    }
}
