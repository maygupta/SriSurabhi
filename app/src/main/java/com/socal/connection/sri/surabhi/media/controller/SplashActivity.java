package com.socal.connection.sri.surabhi.media.controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.demo.com.single.activity.sample.R;
import com.socal.connection.sri.surabhi.media.screen.Setup.Enum.ColourThemeFileType;
import com.socal.connection.sri.surabhi.media.screen.Setup.Utils.SetupFile;
import com.socal.connection.sri.surabhi.media.utils.Common;
import com.socal.connection.sri.surabhi.media.utils.SharedPref;

import static com.socal.connection.sri.surabhi.media.utils.License.SimpleCrypto.LicenseDecrypt;
import static com.socal.connection.sri.surabhi.media.utils.License.SimpleCrypto.LicenseEncrypt;


public class SplashActivity extends AppCompatActivity {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SetupFile setupFile = new SetupFile.SetupBuilder().build();

        ColourThemeFileType.setTheme(this, ColourThemeFileType.getType(setupFile.getColourFileType()));

        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {

                // This method is call to open Main Screen
                callMainScreen();

            }
        }, SPLASH_TIME_OUT);
    }

    /*
     * This Class is create license.ini file, when application is install first time,
     * This licens is represent free version and after create license application will be
     * move to next screen.
     *
     */

    /*
    * Move Current Active to SignITActivity
    * */
    private void callMainScreen() {
        Intent intent;
        if (SharedPref.getString(this, Common.Login_CREDENTIALS_TAG).equals("")) {
            intent = new Intent(SplashActivity.this, LoginActivity.class);
        } else {
            intent = new Intent(SplashActivity.this, SriSurabhiActivity.class);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

}
