package com.socal.connection.sri.surabhi.media.controller;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.socal.connection.sri.surabhi.media.payment.PayUmoney.AppEnvironment;
import com.socal.connection.sri.surabhi.media.screen.Setup.Utils.SetupFile;

public class SriSurabhiApp extends MultiDexApplication {
    public static Context context;
    public static SetupFile setupFile;
    public static String version;
    private static String _androidId;
    public static String getAndroidId() {
        return _androidId;
    }

    public static Context getContext() {
        return context;
    }
    AppEnvironment appEnvironment;

    @Override
    protected void attachBaseContext(Context newBase) {

        super.attachBaseContext(newBase);

        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        version = pInfo.versionName;

        _androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        appEnvironment = AppEnvironment.PRODUCTION;
    }

    public AppEnvironment getAppEnvironment() {
        return appEnvironment;
    }

    public void setAppEnvironment(AppEnvironment appEnvironment) {
        this.appEnvironment = appEnvironment;
    }

}
