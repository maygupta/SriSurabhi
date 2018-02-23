package com.socal.connection.sri.surabhi.media.screen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.socal.connection.sri.surabhi.media.utils.Enums.ScreenEnums;

public abstract class Screen {

    public abstract void onCurrentScreen();

    public abstract void onLeaveScreen();

    public abstract void onClickAction(View view);

    public abstract boolean onCreate(Bundle savedInstanceState);

    public abstract boolean onCreateOptionsMenu(Menu menu);

    public abstract boolean onOptionsItemSelected(MenuItem item);

    public void onStart() {
    }

    public void onResume() {
    }

    public abstract ScreenEnums onBackPressed();

    public void onPause() {

    }

    public void onStop() {
    }

    public void onDestroy() {
    }

    public void onSaveInstanceState(Bundle outState) {
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    }

    public void getResultsFromApi() {
    }
}
