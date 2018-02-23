package com.socal.connection.sri.surabhi.media.screen.Setup.Enum;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.widget.FrameLayout;

import com.demo.com.single.activity.sample.R;

public enum ColourThemeFileType {
    AMBER(0, R.string.color_amber_tag),
    BLUE_GRAY(1, R.string.color_blue_gray_tag),
    BLUE(2, R.string.color_blue_tag),
    BROWN(3, R.string.color_brown_tag),
    CYAN(4, R.string.color_cyan_tag),
    DEEP_ORANGE(5, R.string.color_deep_orange_tag),
    DEEP_PURPLE(6, R.string.color_deep_purple_tag),
    GREEN(7, R.string.color_Green_tag),
    GREY(8, R.string.color_grey_tag),
    INDIGO(9, R.string.color_indigo_tag),
    LIGHT_BLUE(10, R.string.color_light_blue_tag),
    LIGHT_GREEN(11, R.string.color_light_green_tag),
    LIME(12, R.string.color_lime_tag),
    ORANGE(13, R.string.color_orange_tag),
    PINK(14, R.string.color_pink_tag),
    PURPLE(15, R.string.color_purple_tag),
    RED(16, R.string.color_red_tag),
    TEAL(17, R.string.color_teal_tag),
    YELLOW(18, R.string.color_yellow_tag);

    private int _value;
    private int _id;

    private ColourThemeFileType(int value, int id) {
        this._value = value;
        this._id = id;
    }

    public static String[] getList(Activity activity) {
        return new String[]{AMBER.getName(activity), BLUE_GRAY.getName(activity), BLUE.getName(activity)
                , BROWN.getName(activity), CYAN.getName(activity), DEEP_ORANGE.getName(activity),
                DEEP_PURPLE.getName(activity), GREEN.getName(activity), GREY.getName(activity),
                INDIGO.getName(activity), LIGHT_BLUE.getName(activity), LIGHT_GREEN.getName(activity),
                LIME.getName(activity), ORANGE.getName(activity), PINK.getName(activity), PURPLE.getName(activity),
                RED.getName(activity), TEAL.getName(activity), YELLOW.getName(activity)
        };
    }

    public static ColourThemeFileType getType(int value) {
        switch (value) {
            case 0:
                return AMBER;

            case 1:

                return BLUE_GRAY;

            case 2:

                return BLUE;

            case 3:

                return BROWN;
            case 4:

                return CYAN;
            case 5:

                return DEEP_ORANGE;
            case 6:

                return DEEP_PURPLE;
            case 7:

                return GREEN;
            case 8:

                return GREY;
            case 9:

                return INDIGO;
            case 10:

                return LIGHT_BLUE;
            case 11:

                return LIGHT_GREEN;
            case 12:

                return LIME;
            case 13:

                return ORANGE;
            case 14:

                return PINK;
            case 15:

                return PURPLE;
            case 16:

                return RED;
            case 17:

                return TEAL;
            case 18:

                return YELLOW;

            default:
                throw new IllegalArgumentException(value + " is not a valid Pixel  file type ");
        }
    }

    public static ColourThemeFileType getType(Context context, String value) {
        if (value.equals(context.getString(R.string.color_amber_tag))) {

            return AMBER;
        } else if (value.equals(context.getString(R.string.color_blue_gray_tag))) {

            return BLUE_GRAY;
        } else if (value.equals(context.getString(R.string.color_blue_tag))) {

            return BLUE;
        } else if (value.equals(context.getString(R.string.color_brown_tag))) {

            return BROWN;
        } else if (value.equals(context.getString(R.string.color_cyan_tag))) {

            return CYAN;
        } else if (value.equals(context.getString(R.string.color_deep_orange_tag))) {

            return DEEP_ORANGE;
        } else if (value.equals(context.getString(R.string.color_deep_purple_tag))) {

            return DEEP_PURPLE;
        } else if (value.equals(context.getString(R.string.color_Green_tag))) {

            return GREEN;
        } else if (value.equals(context.getString(R.string.color_grey_tag))) {

            return GREY;
        } else if (value.equals(context.getString(R.string.color_indigo_tag))) {

            return INDIGO;
        } else if (value.equals(context.getString(R.string.color_light_blue_tag))) {

            return LIGHT_BLUE;
        } else if (value.equals(context.getString(R.string.color_light_green_tag))) {

            return LIGHT_GREEN;
        } else if (value.equals(context.getString(R.string.color_lime_tag))) {

            return LIME;
        } else if (value.equals(context.getString(R.string.color_orange_tag))) {

            return ORANGE;
        } else if (value.equals(context.getString(R.string.color_pink_tag))) {

            return PINK;
        } else if (value.equals(context.getString(R.string.color_purple_tag))) {

            return PURPLE;
        } else if (value.equals(context.getString(R.string.color_red_tag))) {

            return RED;
        } else if (value.equals(context.getString(R.string.color_teal_tag))) {

            return TEAL;
        } else if (value.equals(context.getString(R.string.color_yellow_tag))) {

            return YELLOW;
        } else {
            return AMBER;
        }
    }

    public static void setTheme(Activity activity, ColourThemeFileType sTheme) {
        switch (sTheme) {
            case INDIGO:
                activity.setTheme(R.style.AppThemeIndigo);
                break;
            case RED:
                activity.setTheme(R.style.AppThemeRed);
                break;
            case GREY:
                activity.setTheme(R.style.AppThemeGray);
                break;
            case PINK:
                activity.setTheme(R.style.AppThemePink);
                break;
            case PURPLE:
                activity.setTheme(R.style.AppThemePurple);
                break;
            case DEEP_PURPLE:
                activity.setTheme(R.style.AppThemeDeepPurple);
                break;
            case BLUE:
                activity.setTheme(R.style.AppThemeBlue);
                break;
            case LIGHT_BLUE:
                activity.setTheme(R.style.AppThemeLightBlue);
                break;
            case CYAN:
                activity.setTheme(R.style.AppThemeCyan);
                break;
            case TEAL:
                activity.setTheme(R.style.AppThemeTeal);
                break;
            case GREEN:
                activity.setTheme(R.style.AppThemeGreen);
                break;
            case LIGHT_GREEN:
                activity.setTheme(R.style.AppThemeLightGreen);
                break;
            case LIME:
                activity.setTheme(R.style.AppThemeLime);
                break;
            case YELLOW:
                activity.setTheme(R.style.AppThemeYellow);
                break;
            case AMBER:
                activity.setTheme(R.style.AppThemeAmber);
                break;
            case ORANGE:
                activity.setTheme(R.style.AppThemeOrange);
                break;
            case DEEP_ORANGE:
                activity.setTheme(R.style.AppThemeDeepOrange);
                break;
            case BROWN:
                activity.setTheme(R.style.AppThemeBrown);
                break;
            case BLUE_GRAY:
                activity.setTheme(R.style.AppThemeBlueGray);
                break;
            default:
                activity.setTheme(R.style.AppThemeIndigo);
                break;
        }
    }

    public static int setTextColor(Activity activity, ColourThemeFileType sTheme) {
        switch (sTheme) {

            case RED:
                return Color.WHITE;
            case GREY:
                return Color.BLACK;
            case PINK:
                return Color.WHITE;
            case PURPLE:
                return Color.WHITE;
            case DEEP_PURPLE:
                return Color.WHITE;
            case BLUE:
                return Color.WHITE;
            case LIGHT_BLUE:
                return Color.WHITE;
            case CYAN:
                return Color.WHITE;
            case TEAL:
                return Color.WHITE;
            case GREEN:
                return Color.WHITE;
            case LIGHT_GREEN:
                return Color.BLACK;
            case LIME:
                return Color.BLACK;
            case YELLOW:
                return Color.BLACK;
            case AMBER:
                return Color.BLACK;
            case ORANGE:
                return Color.BLACK;
            case DEEP_ORANGE:
                return Color.WHITE;
            case BROWN:
                return Color.WHITE;
            case BLUE_GRAY:
                return Color.WHITE;
            default:
                return Color.WHITE;
        }
    }

    public static void setBackLayoutColor(Activity activity, ColourThemeFileType sTheme) {
        FrameLayout relativeLayout = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        relativeLayout.setBackgroundColor(setBackgroundColor(activity, sTheme));
    }

    public static int setBackgroundColor(Activity activity, ColourThemeFileType sTheme) {

        switch (sTheme) {
            case RED:
                return activity.getResources().getColor(R.color.redColorBackground);
            case GREY:
                return activity.getResources().getColor(R.color.grayColorBackground);
            case PURPLE:
                return activity.getResources().getColor(R.color.purpleColorBackground);
            case DEEP_PURPLE:
                return activity.getResources().getColor(R.color.deepPurpleColorBackground);
            case BLUE:
                return activity.getResources().getColor(R.color.blueColorBackground);
            case LIGHT_BLUE:
                return activity.getResources().getColor(R.color.lightBlueColorBackground);
            case CYAN:
                return activity.getResources().getColor(R.color.cyanColorBackground);
            case TEAL:
                return activity.getResources().getColor(R.color.tealColorBackground);
            case GREEN:
                return activity.getResources().getColor(R.color.greenColorBackground);
            case LIGHT_GREEN:
                return activity.getResources().getColor(R.color.lightGreenColorBackground);
            case LIME:
                return activity.getResources().getColor(R.color.limeColorBackground);
            case YELLOW:
                return activity.getResources().getColor(R.color.yellowColorBackground);
            case AMBER:
                return activity.getResources().getColor(R.color.amberColorBackground);
            case ORANGE:
                return activity.getResources().getColor(R.color.orangeColorBackground);
            case DEEP_ORANGE:
                return activity.getResources().getColor(R.color.deepOrangeColorBackground);
            case BROWN:
                return activity.getResources().getColor(R.color.brownColorBackground);
            case BLUE_GRAY:
                return activity.getResources().getColor(R.color.brownColorBackground);
            default:
                return activity.getResources().getColor(R.color.indigoColorBackground);
        }
    }

    public int getValue() {
        return this._value;
    }

    public String getName(Context context) {
        return context.getString(this._id);
    }
}
