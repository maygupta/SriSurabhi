package com.socal.connection.sri.surabhi.media.screen.Setup.Enum;

import android.app.Activity;
import android.content.Context;

import com.demo.com.single.activity.sample.R;

public enum LanguageFileType {
    ENGLISH(0, R.string.lang_english_tag),
    FRENCH(1, R.string.lang_french_tag),
    TURKISH(2, R.string.lang_turkish_tag),
    PORTUGUESE(3, R.string.lang_portuguese_tag),
    SPANISH(4, R.string.lang_Spanish_tag),
    DUTCH(5, R.string.lang_dutch_tag),
    CZECH(6, R.string.lang_czech_tag),
    GERMAN(7, R.string.lang_german_tag),
    FINNISH(8, R.string.lang_finnish_tag),
    INDONESIAN(9, R.string.lang_indonesian_tag),
    JAPANESE(10, R.string.lang_Japanese_tag),
    ITALIAN(11, R.string.lang_italian_tag),
    POLISH(12, R.string.lang_Polish_tag),
    SWEDISH(13, R.string.lang_swedish_tag),
    VIETNAMESE(14, R.string.lang_vietnamese_tag);

    private int _id;
    private int _value;

    private LanguageFileType(int value, int id) {
        this._value = value;
        this._id = id;
    }

    public static String[] getList(Activity activity) {
        return new String[]{ENGLISH.getName(activity),
                FRENCH.getName(activity), TURKISH.getName(activity), PORTUGUESE.getName(activity),
                SPANISH.getName(activity), DUTCH.getName(activity), CZECH.getName(activity),
                GERMAN.getName(activity), FINNISH.getName(activity), INDONESIAN.getName(activity),
                JAPANESE.getName(activity), ITALIAN.getName(activity), POLISH.getName(activity),
                SWEDISH.getName(activity), VIETNAMESE.getName(activity)};
    }

    public static LanguageFileType getType(int value) {
        switch (value) {
            case 0:
                return ENGLISH;

            case 1:

                return FRENCH;
            case 2:

                return TURKISH;
            case 3:

                return PORTUGUESE;
            case 4:

                return SPANISH;
            case 5:

                return DUTCH;
            case 6:

                return CZECH;
            case 7:

                return GERMAN;
            case 8:

                return FINNISH;
            case 9:

                return INDONESIAN;
            case 10:

                return JAPANESE;
            case 11:

                return ITALIAN;
            case 12:

                return POLISH;
            case 13:

                return SWEDISH;
            case 14:

                return VIETNAMESE;

            default:
                throw new IllegalArgumentException(value + " is not a valid orientation file type ");
        }
    }

    public static LanguageFileType getType(Context context, String value) {
        if (value.equals(context.getString(R.string.lang_english_tag))) {
            return ENGLISH;
        } else if (value.equals(context.getString(R.string.lang_french_tag))) {

            return FRENCH;
        } else if (value.equals(context.getString(R.string.lang_turkish_tag))) {

            return TURKISH;
        } else if (value.equals(context.getString(R.string.lang_portuguese_tag))) {

            return PORTUGUESE;
        } else if (value.equals(context.getString(R.string.lang_Spanish_tag))) {

            return SPANISH;
        } else if (value.equals(context.getString(R.string.lang_dutch_tag))) {

            return DUTCH;
        } else if (value.equals(context.getString(R.string.lang_czech_tag))) {

            return CZECH;
        } else if (value.equals(context.getString(R.string.lang_german_tag))) {

            return GERMAN;
        } else if (value.equals(context.getString(R.string.lang_finnish_tag))) {

            return FINNISH;
        } else if (value.equals(context.getString(R.string.lang_indonesian_tag))) {

            return INDONESIAN;
        } else if (value.equals(context.getString(R.string.lang_Japanese_tag))) {

            return JAPANESE;
        } else if (value.equals(context.getString(R.string.lang_italian_tag))) {

            return ITALIAN;
        } else if (value.equals(context.getString(R.string.lang_Polish_tag))) {

            return POLISH;
        } else if (value.equals(context.getString(R.string.lang_swedish_tag))) {

            return SWEDISH;
        } else if (value.equals(context.getString(R.string.lang_vietnamese_tag))) {

            return VIETNAMESE;
        } else {
            return ENGLISH;
        }
    }

    public static String getLocal(LanguageFileType languageFileType) {
        switch (languageFileType) {

            case ENGLISH:
                return "es";

            case FRENCH:
                return "fr";

            case TURKISH:
                return "tr";

            case PORTUGUESE:
                return "pt";

            case SPANISH:
                return "es";

            case DUTCH:
                return "nl";

            case CZECH:
                return "cs";

            case GERMAN:
                return "de";

            case FINNISH:
                return "fi";

            case INDONESIAN:
                return "in";

            case JAPANESE:
                return "ja";

            case ITALIAN:
                return "it";

            case POLISH:
                return "pl";

            case SWEDISH:
                return "sv";

            case VIETNAMESE:
                return "vi";
            default:
                return "en";
        }
    }

    public int getValue() {
        return this._value;
    }

    public String getName(Context context) {
        return context.getString(this._id);
    }
}
