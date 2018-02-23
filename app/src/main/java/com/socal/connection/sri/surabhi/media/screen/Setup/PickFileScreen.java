package com.socal.connection.sri.surabhi.media.screen.Setup;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;


import com.demo.com.single.activity.sample.R;
import com.socal.connection.sri.surabhi.media.controller.SriSurabhiActivity;
import com.socal.connection.sri.surabhi.media.screen.Screen;
import com.socal.connection.sri.surabhi.media.screen.Setup.Enum.ColourThemeFileType;
import com.socal.connection.sri.surabhi.media.screen.Setup.Utils.SetupFile;
import com.socal.connection.sri.surabhi.media.utils.CommonUtil;
import com.socal.connection.sri.surabhi.media.utils.Enums.ScreenEnums;
import com.socal.connection.sri.surabhi.media.utils.SharedStaticObjects;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static com.socal.connection.sri.surabhi.media.utils.Common.SS_HELP_SCREEN_NAME;
import static com.socal.connection.sri.surabhi.media.utils.CommonUtil.getIconImage;

/*
* This class used for Send Email.
*
* */


public class PickFileScreen extends Screen implements AdapterView.OnItemSelectedListener {

    public static final int ResultExitApp = 1;
    public static final int ResultLicensePicked = ResultExitApp << 1;
    public static final int ResultSetupPicked = ResultLicensePicked << 1;
    public static final int ResultAcceptPicked = ResultSetupPicked << 1;
    public static final int ResultTranslatorPicked = ResultAcceptPicked << 1;
    public static final int ResultAmlTranslatorPicked = ResultTranslatorPicked << 1;
    public static final int ResultFolderPicked = ResultAmlTranslatorPicked << 1;
    public static String inFolderCopyFilePath;
    public static String currentFileName;
    FrameLayout rootView = null;
    FolderShort folderShort = FolderShort.Name;
    ListView pick_file_list = null;
    private Activity activity = null;
    private int screen_name;
    private List<String> item = null;
    private List<String> path = null;
    private String root = "/";
    private String _downloadPath;
    private TextView myPath;
    private int _indexFolderShortDate;
    private int _indexFolderShortName;
    private int _indexLicenseOption;
    private int _indexLicenseCopyToServerOption;


    private int _indexSetupOption;
    private int _indexCopyFileOnInFolder;
    private int _indexFolderOption;
    private int _indexSubfolderOption;
    private String _outputPath;
    private String _currentPath;
    private boolean _copyFolderSelected;
    private boolean _copySubfolders;
    private boolean _copyFileOnServer;
    private int _resultSelected;
    private int _currentResult;
    private SetupFile setupFile;
    private String setupFilePath = null;

    public PickFileScreen(Activity activity, Object data, SetupFile setupFile) {
        this.activity = activity;
        this.setupFile = setupFile;
    }

    @Override
    public boolean onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ((SriSurabhiActivity) activity).getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        File licenceFile = new File(SharedStaticObjects.LicenseFilePath);
        if (id == R.id.menuSetup) {
            if (!licenceFile.exists()) {
                SriSurabhiActivity.getInstance().switchScreen(ScreenEnums.SCREEN_LICENCE, activity, null, setupFile);
            } else {
                SriSurabhiActivity.getInstance().switchScreen(ScreenEnums.SCREEN_SETUP, activity, null, setupFile);
            }
            return true;
        } else if (id == R.id.menuExit) {
            activity.finish();
            return true;
        } else if (id == R.id.menuAbout) {
            CommonUtil.aboutSignIt(activity, setupFile.getLicenseType());
            return true;
        } else if (id == R.id.updateLicence) {
            SriSurabhiActivity.getInstance().switchScreen(ScreenEnums.SCREEN_LICENCE, activity, null, setupFile);
            return true;
        }

        return onCreateOptionsMenu(item.getSubMenu());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        screen_name = savedInstanceState.getInt(SS_HELP_SCREEN_NAME);
    }

    @Override
    public void onCurrentScreen() {
        rootView = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        layoutInflater.inflate(R.layout.screen_pick_file, rootView);
        RelativeLayout helpLayout = (RelativeLayout) rootView.findViewById(R.id.pickfile_screen);
        helpLayout.setVisibility(View.VISIBLE);

        Toolbar myToolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        myToolbar.setNavigationIcon(getIconImage(setupFile.getLicenseType()));
        myToolbar.setTitle(activity.getString(R.string.pick_license_setup));
        myToolbar.setTitleTextColor(ColourThemeFileType.setTextColor(activity, ColourThemeFileType.getType(setupFile.getColourFileType())));
        ((SriSurabhiActivity) activity).setSupportActionBar(myToolbar);

        setupFilePath = SharedStaticObjects.SetupFilePath;

        pick_file_list = (ListView) activity.findViewById(R.id.pick_file_list);
        pick_file_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                File file = new File(path.get(position));

                if (file.isDirectory()) {
                    if (file.canRead())
                        getDir(path.get(position));
                    else {
                        new AlertDialog.Builder(activity).setIcon(R.mipmap.ic_launcher)
                                .setTitle("[" + file.getName() + activity.getString(R.string.folder_can_be_read))
                                .setPositiveButton(activity.getResources().getString(R.string.was_copied_to_signIT_ADG_folder), null)
                                .show();
                    }
                } else {
                    if (PickFileScreen.this._copyFolderSelected)
                        return;
                    PickFileScreen.this.copySelectedToAppRoot(file);
                }
            }
        });
        loadOnCraeateData();
    }

    private void copySelectedToAppRoot(final File file) {
//        final File outputFile;
//
//        outputFile = new File(PickFileScreen.this._outputPath);
//        if (PickFileScreen.this._outputPath == inFolderCopyFilePath && this._copyFileOnServer) {
//
//            if (!file.getNameID().contains(Common.PDF_EXTENSION.toString())) {
//
//                fileUpload = FileUpload.FILE_COPY_ON_IN_FOLDER;
//
//                new AlertDialog.Builder(activity).setIcon(R.mipmap.ic_launcher).setTitle(file.getNameID())
//                        .setMessage(activity.getResources().getString(R.string.do_you_want_to_sure) + "\n" + file.getNameID() + " "
//                                + activity.getResources().getString(R.string.is_copy_to_signIT_ADG_folder))
//                        .setPositiveButton(R.string.button_Ok, new DialogInterface.OnClickListener() {
//
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//
//                                currentFileName = file.getNameID();
//                                inFolderCopyFilePath = inFolderCopyFilePath + file.getNameID();
//                                File destination = new File(inFolderCopyFilePath);
//                                boolean isFileCopy = SignITApp.getInstance().copyFile(file, destination);
//
//                                if (isFileCopy) {
//
//                                    displaySyncProgressDialog(activity, activity.getResources().getString(R.string.file_copy_check));
//                                    String inFolderFtpPath = setupFile.getServerPath() + setupFile.getDeviceName() + "/" + SharedStaticObjects.InputFolderName;
//
//                                    if (setupFile.getServerType() == ServerFileType.FREE.getValue()) {
//
//                                        new ShareOnServerFileFTPAsyncTask(activity, new ConnectionHandler(activity), setupFile).executeTest(setupFile.getFileNetwork(),
//                                                setupFile.getUserName(), setupFile.getPassword(), inFolderFtpPath, setupFile.getDeviceName(), setupFile.getDoCheckLicenceOnSync());
//
//                                    } else if (setupFile.getServerType() == ServerFileType.STANDARD.getValue()) {
//
//                                        new ShareOnServerFileSambaAsyncTask(activity, new ConnectionHandler(activity), setupFile).executeTest(setupFile.getFileNetwork(),
//                                                setupFile.getUserName(), setupFile.getPassword(), inFolderFtpPath, setupFile.getDeviceName(), setupFile.getDoCheckLicenceOnSync());
//
//                                    } else if (setupFile.getServerType() == ServerFileType.ENTERPRISE.getValue()) {
//
//                                        SignITActivity.methodsEnum = MethodsEnum.PICK_FILE_SCREEN_FILE_UPLOAD;
//                                        SignITActivity mainActivity = SignITActivity.getInstance();
//                                        mainActivity.getResultsFromApi(null);
//
//                                    }
//                                }
//                            }
//                        }).setNegativeButton(R.string.button_Cancel, null).show();
//            } else {
//
//                Toast.makeText(activity, R.string.please_choose_currect_file, Toast.LENGTH_SHORT).show();
//
//            }
//
//        } else {

//            new AlertDialog.Builder(activity).setIcon(R.mipmap.ic_launcher).setTitle(file.getNameID())
//                    .setMessage(activity.getResources().getString(R.string.do_you_want_to_sure) + "\n" + file.getNameID() + " "
//                            + activity.getResources().getString(R.string.is_copy_to_signIT_ADG_folder))
//                    .setPositiveButton(R.string.button_Ok, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//
//                            if (!file.equals(outputFile)) {
//
//                                if (PickFileScreen.this._copyFolderSelected) {
//
//                                    SignITApp.getInstance().copyDirectory(file, outputFile, PickFileScreen.this._copySubfolders);
//
//                                } else {
//
//                                    SignITApp.getInstance().copyFile(file, outputFile);
//
//                                }
//
//                                new AlertDialog.Builder(activity).setIcon(R.mipmap.ic_launcher).setTitle(file.getNameID())
//                                        .setMessage(file.getNameID() + " " + activity.getResources().getString(R.string.was_copied_to_signIT_ADG_folder))
//                                        .setPositiveButton(R.string.button_Ok, null).show();
//
//                            } else {
//
//                                Toast.makeText(activity, R.string.please_choose_currect_file, Toast.LENGTH_SHORT).show();
//
//                            }
//                        }
//                    }).setNegativeButton(R.string.button_Cancel, null).show();
//
//        }
//
//        PickFileScreen.this._currentResult |= PickFileScreen.this._resultSelected;
    }

    private void loadOnCraeateData() {
        myPath = (TextView) activity.findViewById(R.id.path);
        _downloadPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        getDir(_downloadPath);

        ArrayList<String> foilderShareOptions = new ArrayList<String>(4);
        this._indexFolderShortName = foilderShareOptions.size();
        foilderShareOptions.add(activity.getString(R.string.folder_short_by_name));
        this._indexFolderShortDate = foilderShareOptions.size();
        foilderShareOptions.add(activity.getString(R.string.folder_short_by_date));

        ArrayList<String> spinnerOptions = new ArrayList<String>(4);
        this._indexLicenseOption = spinnerOptions.size();
        spinnerOptions.add(activity.getString(R.string.pickfile_options_license));
        this._indexLicenseCopyToServerOption = spinnerOptions.size();
        spinnerOptions.add(activity.getString(R.string.pickfile_copy_to_server_options_license));
        this._indexSetupOption = spinnerOptions.size();
        spinnerOptions.add(activity.getString(R.string.pickfile_options_setup));
        this._indexFolderOption = spinnerOptions.size();
        spinnerOptions.add(activity.getString(R.string.pickfile_options_folder));
        this._indexSubfolderOption = spinnerOptions.size();
        spinnerOptions.add(activity.getString(R.string.pickfile_options_subfolder));
        this._indexCopyFileOnInFolder = spinnerOptions.size();
        spinnerOptions.add(activity.getString(R.string.pickfile_options_copy_file_on_in_folder));

        final Spinner folderShortSpinner = (Spinner) activity.findViewById(R.id.folderShortPicker);
        ArrayAdapter<String> folderShortAdapter = new ArrayAdapter<String>(activity, R.layout.spinner_item_text_view, foilderShareOptions.toArray(new String[foilderShareOptions.size()]));
        folderShortAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        folderShortSpinner.setAdapter(folderShortAdapter);
        folderShortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == PickFileScreen.this._indexFolderShortName) {
                    folderShort = FolderShort.Name;
                    myPath = (TextView) activity.findViewById(R.id.path);
                    _downloadPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
                    getDir(_downloadPath);
                } else if (position == PickFileScreen.this._indexFolderShortDate) {
                    folderShort = FolderShort.Date;
                    myPath = (TextView) activity.findViewById(R.id.path);
                    _downloadPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
                    getDir(_downloadPath);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final Spinner actionSpinner = (Spinner) activity.findViewById(R.id.actionPicker);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, R.layout.spinner_item_text_view, spinnerOptions.toArray(new String[spinnerOptions.size()]));
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        actionSpinner.setAdapter(adapter);
        actionSpinner.setOnItemSelectedListener(this);

        this._copyFolderSelected = false;

        Handler openHandler = new Handler();
        Runnable openRunner = new Runnable() {
            public void run() {
                actionSpinner.performClick();

            }
        };
        openHandler.postDelayed(openRunner, 100);

    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub

    }

    @Override
    public ScreenEnums onBackPressed() {
        return ScreenEnums.SCREEN_SETUP;
    }

    @Override
    public void onLeaveScreen() {
        rootView = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        RelativeLayout helpLayout = (RelativeLayout) rootView.findViewById(R.id.pickfile_screen);
        rootView.removeView(helpLayout);
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStop() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(SS_HELP_SCREEN_NAME, screen_name);

    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub

    }


    @Override
    public void onClickAction(View view) {

    }

    private String getCleanViewText(int textViewId) {
        return ((TextView) rootView.findViewById(textViewId)).getText().toString().trim();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

    }

    @Override
    public void getResultsFromApi() {

    }

    private void getDir(String dirPath) {
        this._currentPath = dirPath;
        myPath.setText("Location: " + dirPath);
        item = new ArrayList<String>();
        path = new ArrayList<String>();
        File f = new File(dirPath);
        File[] files = f.listFiles();

        if (!dirPath.equals(root)) {
            item.add(root);
            path.add(root);
            item.add("../");
            path.add(f.getParent());
        }

        if (!dirPath.equals(this._downloadPath)) {
            item.add("Downloads Folder");
            path.add(_downloadPath);
        }

        if (folderShort == FolderShort.Name) {
            Arrays.sort(files);
        } else if (folderShort == FolderShort.Date) {
            Arrays.sort(files, new Comparator() {
                public int compare(Object o1, Object o2) {

                    if (((File) o1).lastModified() > ((File) o2).lastModified()) {
                        return -1;
                    } else if (((File) o1).lastModified() < ((File) o2).lastModified()) {
                        return +1;
                    } else {
                        return 0;
                    }
                }
            });
        }

        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            path.add(file.getPath());
            if (file.isDirectory()) {
                item.add(file.getName() + "/");
            } else {
                item.add(file.getName());
            }
        }

        ArrayAdapter<String> fileList = new ArrayAdapter<String>(activity, R.layout.item_pick_file_list, item);
        pick_file_list.setAdapter(fileList);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        Button copyFolderButton;
        boolean newCopyFolderSelectedValue = false;
        this._copySubfolders = false;
        this._copyFileOnServer = false;
        if (position == this._indexLicenseOption) {
            this._outputPath = SharedStaticObjects.LicenseFilePath;
            this._resultSelected = PickFileScreen.ResultLicensePicked;
        } else if (position == this._indexLicenseCopyToServerOption) {
            this._outputPath = SharedStaticObjects.LicenseFilePath;
            this._resultSelected = PickFileScreen.ResultLicensePicked;
            this._copyFileOnServer = true;
        } else if (position == this._indexSetupOption) {
            this._outputPath = setupFilePath;
            this._resultSelected = PickFileScreen.ResultSetupPicked;
        } else if (position == this._indexFolderOption) {
            this._outputPath = SharedStaticObjects.AppRootPath;
            this._resultSelected = PickFileScreen.ResultFolderPicked;
            newCopyFolderSelectedValue = true;
        } else if (position == this._indexSubfolderOption) {
            this._outputPath = SharedStaticObjects.AppRootPath;
            newCopyFolderSelectedValue = true;
            this._copySubfolders = true;
            this._resultSelected = PickFileScreen.ResultFolderPicked;
        } else if (position == this._indexCopyFileOnInFolder) {
            inFolderCopyFilePath = SharedStaticObjects.AppRootPath + setupFile.getDeviceName() + "/" + SharedStaticObjects.InputFolderName + "/";
            this._outputPath = inFolderCopyFilePath;
            this._resultSelected = PickFileScreen.ResultLicensePicked;
            this._copyFileOnServer = true;
        }

        if (this._copyFolderSelected != newCopyFolderSelectedValue) {
            copyFolderButton = (Button) activity.findViewById(R.id.pickFolder);

            if (newCopyFolderSelectedValue)
                copyFolderButton.setVisibility(View.VISIBLE);
            else
                copyFolderButton.setVisibility(View.INVISIBLE);

            this._copyFolderSelected = newCopyFolderSelectedValue;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public enum FolderShort {
        Date,
        Name
    }

}
