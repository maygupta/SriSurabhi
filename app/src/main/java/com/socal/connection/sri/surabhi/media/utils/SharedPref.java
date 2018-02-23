package com.socal.connection.sri.surabhi.media.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static com.socal.connection.sri.surabhi.media.utils.Common.APP_NAME;


public class SharedPref {
    public static SharedPreferences prefs;

    public static void putBoolean(Context ctx, String key, boolean val) {
        prefs = ctx.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);
        prefs.edit().putBoolean(key, val).commit();

    }

    public static boolean getBoolean(Context ctx, String key) {
        prefs = ctx.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);
        return prefs.getBoolean(key, false);
    }

    public static void putInt(Context ctx, String key, int score) {
        prefs = ctx.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);
        prefs.edit().putInt(key, score).commit();

    }

    public static int getInt(Context ctx, String key) {
        prefs = ctx.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);

        return prefs.getInt(key, 0);
    }

    public static void putEmail(Context ctx, String key, String score) {
        prefs = ctx.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString(key, score).commit();

    }

    public static String getEmail(Context ctx, String key) {
        prefs = ctx.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);
        return prefs.getString(key, null);
    }

    public static void putString(Context ctx, String key, String score) {
        prefs = ctx.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString(key, score).commit();

    }

    public static String getString(Context ctx, String key) {
        prefs = ctx.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);
        return prefs.getString(key, "");
    }

    /*
    * public void userLoginFunction(final String url){     RequestQueue queue = Volley.newRequestQueue(this);     JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null,             new Response.Listener<JSONObject>() {         @Override         public void onResponse(JSONObject response) {             Log.i("eclipse","response="+response.toString());         }     }, new Response.ErrorListener() {         @Override         public void onErrorResponse(VolleyError error) {             Log.i("eclipse","response="+error);         }     });      queue.add(jsObjRequest);     //UserLoginClass userLoginClass = new UserLoginClass();     ///userLoginClass.execute(email,httpURL); }

    * */
}
