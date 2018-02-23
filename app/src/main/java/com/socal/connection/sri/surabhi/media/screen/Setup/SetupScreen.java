package com.socal.connection.sri.surabhi.media.screen.Setup;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.com.single.activity.sample.R;
import com.socal.connection.sri.surabhi.media.controller.SriSurabhiActivity;
import com.socal.connection.sri.surabhi.media.screen.Screen;
import com.socal.connection.sri.surabhi.media.screen.Setup.Enum.FTPServerFileType;
import com.socal.connection.sri.surabhi.media.screen.Setup.Enum.LicenseFileType;
import com.socal.connection.sri.surabhi.media.screen.Setup.Enum.ColourThemeFileType;
import com.socal.connection.sri.surabhi.media.screen.Setup.Enum.DevLicenseOptionType;
import com.socal.connection.sri.surabhi.media.screen.Setup.Enum.LanguageFileType;
import com.socal.connection.sri.surabhi.media.screen.Setup.Enum.OrientationFileType;
import com.socal.connection.sri.surabhi.media.screen.Setup.Enum.ServerFileType;
import com.socal.connection.sri.surabhi.media.screen.Setup.Utils.ChangePasswordDialog;
import com.socal.connection.sri.surabhi.media.screen.Setup.Utils.SetupFile;
import com.socal.connection.sri.surabhi.media.screen.Setup.Utils.TestConnectionCallBack;
import com.socal.connection.sri.surabhi.media.utils.Common;
import com.socal.connection.sri.surabhi.media.utils.Enums.ScreenEnums;
import com.socal.connection.sri.surabhi.media.utils.License.SimpleCryptoException;
import com.socal.connection.sri.surabhi.media.utils.SharedStaticObjects;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.socal.connection.sri.surabhi.media.screen.Setup.Enum.ServerFileType.*;
import static com.socal.connection.sri.surabhi.media.screen.Setup.Utils.SetupCommonUtils.*;
import static com.socal.connection.sri.surabhi.media.utils.Common.SS_HELP_SCREEN_NAME;
import static com.socal.connection.sri.surabhi.media.utils.CommonUtil.showBasicAlert;

public class SetupScreen extends Screen implements TestConnectionCallBack {

    @BindView(R.id.toolbar)
    public Toolbar myToolbar;

    @BindView(R.id.setup_main_layout)
    public LinearLayout setupMainLayout;

    @BindView(R.id.security_tableRow_layout)
    public LinearLayout ftpServerTableRowLayout;

    @BindView(R.id.filenetwork_tableRow_layout)
    public LinearLayout filenetworkTableRowLayout;

    @BindView(R.id.username_tableRow_layout)
    public LinearLayout userNameTableRowLayout;

    @BindView(R.id.password_tableRow_layout)
    public LinearLayout passwordTableRowLayout;

    @BindView(R.id.ftppath_tableRow_layout)
    public LinearLayout serverPathTableRowLayout;

    @BindView(R.id.admin_include_view)
    public LinearLayout adminIncludeView;

    @BindView(R.id.normal_include_view)
    public LinearLayout normalIncludeView;

    @BindView(R.id.dev_license_option_spinner)
    public MaterialBetterSpinner devLicenseOptionSpinner;

    @BindView(R.id.license_type_spinner)
    public MaterialBetterSpinner licenseTypeSpinner;

    @BindView(R.id.servers_type_spinner)
    public MaterialBetterSpinner serversTypeSpinner;

    @BindView(R.id.ftp_server_type_spinner)
    public MaterialBetterSpinner ftpServerTypeSpinner;

    @BindView(R.id.orientation_type_spinner)
    public MaterialBetterSpinner orientationTypeSpinner;

    @BindView(R.id.language_type_spinner)
    public MaterialBetterSpinner languageTypeSpinner;

    @BindView(R.id.color_type_spinner)
    public MaterialBetterSpinner colorTypeSpinner;

    @BindView(R.id.configpassword)
    public TextView configPassword;

    @BindView(R.id.username)
    public TextView userNameEditText;

    @BindView(R.id.setup_password_textview)
    public TextView passwordTextView;

    @BindView(R.id.password)
    public TextView passwordEditText;

    @BindView(R.id.filenetwork)
    public EditText fileNetworkEditText;

    @BindView(R.id.server_file_path)
    public TextView serverFilePathTextView;

    @BindView(R.id.setup_user_textview)
    public TextView setupUserTextView;

    @BindView(R.id.device_name)
    public EditText deviceNameTextView;

    @BindView(R.id.adminOptions)
    public Button adminOptionsBtn;

    @BindView(R.id.testconnect)
    public Button testConnectBtn;

    private SriSurabhiActivity mContext = SriSurabhiActivity.getInstance();

    private Unbinder unbinder;
    private FrameLayout rootView;
    private Activity activity = null;
    private ScreenEnums screen_name;

    private SetupFile setupFile;

    public SetupScreen(Activity _activity, Object data, SetupFile setupFile) {
        this.activity = _activity;
        this.setupFile = setupFile;
    }

    @Override
    public ScreenEnums onBackPressed() {
        return screen_name;
    }

    @Override
    public void onLeaveScreen() {
        if (unbinder != null)
            unbinder.unbind();

        rootView = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        RelativeLayout setupLayout = (RelativeLayout) rootView.findViewById(R.id.setup_screen);
        rootView.removeView(setupLayout);
    }

    @Override
    public void onClickAction(View view) {
        if (view.getId() == R.id.picksetupfileother) {
            mContext.switchScreen(ScreenEnums.SCREEN_PICK_FILE, activity, null, setupFile);

        } else if (view.getId() == R.id.setupsave) {
            try {
                onSaveSetup(true);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else if (view.getId() == R.id.adminOptions) {
            unlockAdminOptions();

        } else if (view.getId() == R.id.testconnect) {
            testConnect();

        } else if (view.getId() == R.id.exit_setup) {
            mContext.switchScreen(ScreenEnums.SCREEN_HOME, activity, null, setupFile);

        } else if (view.getId() == R.id.chnage_admin_password_btn) {
            changeAdminPassword();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ((SriSurabhiActivity) activity).getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return onCreateOptionsMenu(item.getSubMenu());
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(SS_HELP_SCREEN_NAME, screen_name);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        screen_name = (ScreenEnums) savedInstanceState.get(SS_HELP_SCREEN_NAME);
    }

    @Override
    public boolean onCreate(Bundle savedInstanceState) {
        return true;
    }

    @Override
    public void onCurrentScreen() {
        screen_name = ScreenEnums.SCREEN_HOME;

        rootView = (FrameLayout) activity.findViewById(R.id.application_main_framelayout);
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.screen_setup, null);
        unbinder = ButterKnife.bind(this, view);
        rootView.addView(view);

        //ToolBar component
        myToolbar.setTitle(activity.getString(R.string.screen_setup));
        myToolbar.setTitleTextColor(ColourThemeFileType.setTextColor(activity, ColourThemeFileType.getType(setupFile.getColourFileType())));
        ((SriSurabhiActivity) activity).setSupportActionBar(myToolbar);

        setupNormalLayout();

        File setupFile = new File(SharedStaticObjects.SetupFilePath);
        if (!setupFile.exists()) {
            setupAdminLayout();
        }
    }

    private void setupNormalLayout() {
        normalIncludeView.setVisibility(View.VISIBLE);

        /*  Device Name loading*/
        deviceNameTextView.setText(setupFile.getDeviceName());

        /*  Device License Type related start  */
        devLicenseOptionSpinner.setAdapter(new ArrayAdapter<String>(activity, R.layout.spinner_item, DevLicenseOptionType.getList(activity)));
        devLicenseOptionSpinner.setText(DevLicenseOptionType.getType(setupFile.getDevLicenseOptionType()).getName(activity));
        devLicenseOptionSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                loadLicenseTypeSpinnerData();
            }
        });

        //License Type related start
        licenseTypeSpinner.setAdapter(new ArrayAdapter<String>(activity, R.layout.spinner_item,
                LicenseFileType.getList(activity, DevLicenseOptionType.getType(activity, devLicenseOptionSpinner.getText().toString()).getValue())));
        licenseTypeSpinner.setText(LicenseFileType.getType(setupFile.getLicenseType()).getName(activity));
        licenseTypeSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                loadServerTypeSpinnerData(position);
            }
        });

        //Sever Type related start
        serversTypeSpinner.setAdapter(new ArrayAdapter<String>(activity, R.layout.spinner_item, ServerFileType.getList(activity,
                LicenseFileType.getType(activity, licenseTypeSpinner.getText().toString()).getValue())));
        serversTypeSpinner.setText(ServerFileType.getType(setupFile.getServerType()).getName(activity));
        serversTypeSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                loadServerSetupView(position);
            }
        });
        loadServerSetupView(ServerFileType.getType(activity, serversTypeSpinner.getText().toString()).getValue());

        fileNetworkEditText.setText(setupFile.getFileNetwork());

        userNameEditText.setText(setupFile.getUserName());

        passwordEditText.setText(setupFile.getPassword());

        serverFilePathTextView.setText(setupFile.getServerPath());

        //orientTypeArray related start
        orientationTypeSpinner.setAdapter(new ArrayAdapter<String>(activity, R.layout.spinner_item, OrientationFileType.getList(activity)));
        orientationTypeSpinner.setText(OrientationFileType.getType(setupFile.getOrientationFileType()).getName(activity));

        //language related start
        languageTypeSpinner.setAdapter(new ArrayAdapter<String>(activity, R.layout.spinner_item, LanguageFileType.getList(activity)));
        languageTypeSpinner.setText(LanguageFileType.getType(setupFile.getLanguageFileType()).getName(activity));

        //orientTypeArray related start
        colorTypeSpinner.setAdapter(new ArrayAdapter<String>(activity, R.layout.spinner_item, ColourThemeFileType.getList(activity)));
        colorTypeSpinner.setText(ColourThemeFileType.getType(setupFile.getColourFileType()).getName(activity));

        if (adminIncludeView.isShown()) {
            loadNormalSetupFile();
        }
    }

    private void loadServerSetupView(final int position) {

        //FTP file Serverrelated start
        if (position == ServerFileType.FTP_SERVER.getValue()) {
            ftpServerTableRowLayout.setVisibility(View.VISIBLE);
            ftpServerTypeSpinner.setAdapter(new ArrayAdapter<String>(activity, R.layout.spinner_item, FTPServerFileType.getList(activity)));
            ftpServerTypeSpinner.setText(FTPServerFileType.getType(setupFile.getFtpFileType()).getName(activity));
        } else {
            ftpServerTableRowLayout.setVisibility(View.GONE);
        }

        if (position == ServerFileType.GOOGLE_DRIVE_SERVER.getValue()) {
            setSharedLayoutInvisible(false, position);

            fileNetworkEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            fileNetworkEditText.setSelection(fileNetworkEditText.getText().length());
            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT);

            setupUserTextView.setText(activity.getString(R.string.setup_server_id));
            passwordTextView.setText(activity.getString(R.string.setup_server_folder));
        } else {
            if (position == ServerFileType.DEVICE_SERVER.getValue()) {
                setSharedLayoutInvisible(true, position);
            } else {
                setSharedLayoutInvisible(false, position);
            }

            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            fileNetworkEditText.setInputType(InputType.TYPE_CLASS_TEXT);
            fileNetworkEditText.setSelection(fileNetworkEditText.getText().length());
            setupUserTextView.setText(activity.getString(R.string.setup_user_name));
            passwordTextView.setText(activity.getString(R.string.setup_password_label));
        }

        /*
        * This method is show admin password
        * */

        configPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (position == GOOGLE_DRIVE_SERVER.getValue()) {
                    if (setupFile == null) {
                        return;
                    }
                    String adminPassword = (String) editable.toString();
                    if (adminPassword.equals(setupFile.getAdminPassword())) {
                        fileNetworkEditText.setInputType(InputType.TYPE_CLASS_TEXT);
                    } else {
                        fileNetworkEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    }
                } else {
                    fileNetworkEditText.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            }
        });
    }

    /*
    * This method is responsible is hide/show view, which is buind with licence.ini file.
    * @Param
    * @isVisible - This boolean value, using this value setup view VISIBLE/GONE.
    * @position - This integer value, using this value known licence arrray list index.
    *
    * */

    private void setSharedLayoutInvisible(boolean isVisible, int position) {
        if (isVisible) {
            filenetworkTableRowLayout.setVisibility(View.GONE);
            userNameTableRowLayout.setVisibility(View.GONE);
            passwordTableRowLayout.setVisibility(View.GONE);
            serverPathTableRowLayout.setVisibility(View.GONE);
            testConnectBtn.setEnabled(false);

        } else {
            filenetworkTableRowLayout.setVisibility(View.VISIBLE);
            userNameTableRowLayout.setVisibility(View.VISIBLE);
            passwordTableRowLayout.setVisibility(View.VISIBLE);
            serverPathTableRowLayout.setVisibility(View.VISIBLE);
            testConnectBtn.setEnabled(true);
        }
    }

    private void unlockAdminOptions() {
        Log.i("eclipse", "admin password=" + setupFile.getAdminPassword());
        String adminPassword = configPassword.getText().toString();

        if (adminPassword.length() == 0) {
            Toast.makeText(activity, R.string.setup_Please_enter_the_administrator_password, Toast.LENGTH_SHORT).show();
            return;
        }

        if (!adminPassword.equals(setupFile.getAdminPassword())) {
            Toast.makeText(activity, R.string.setup_The_administrator_password_entered_is_invalid, Toast.LENGTH_SHORT).show();
            return;
        }

        setupAdminLayout();

        onSetLayoutVisiblity(setupFile);
    }

    public void changeAdminPassword() {
        ChangePasswordDialog changePasswordDialog = new ChangePasswordDialog();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString(Common.SS_ADMIN_PASSWORD, setupFile.getAdminPassword());
        changePasswordDialog.setArguments(args);
        changePasswordDialog.show(activity.getFragmentManager(), "Change Admin Password Dialog Fragment");
    }

    private void onSaveSetup(boolean isCallToSave) throws JSONException {
        boolean isAdminPasswordAsk = false;
        try {
            String deviceName = deviceNameTextView.getText().toString();
            if (deviceName.equals("")) {
                showBasicAlert(activity, activity.getString(R.string.error_dot), activity.getString(R.string.setup_please_put_device_name));
                return;
            }

            String licenseOptionFileTypeText = devLicenseOptionSpinner.getText().toString();
            if (licenseOptionFileTypeText.equals("")) {
                showBasicAlert(activity, activity.getString(R.string.error_dot), activity.getString(R.string.setup_please_select_license_type));
                return;
            }

            String licenseFileTypeText = licenseTypeSpinner.getText().toString();
            if (licenseFileTypeText.equals("")) {
                showBasicAlert(activity, activity.getString(R.string.error_dot), activity.getString(R.string.setup_please_select_license_type));
                return;
            }

            String serverFileTypeText = serversTypeSpinner.getText().toString();
            if (serverFileTypeText.equals("")) {
                showBasicAlert(activity, activity.getString(R.string.error_dot), activity.getString(R.string.setup_please_select_server_type));
                return;
            }

            String ftpFileTypeText = ftpServerTypeSpinner.getText().toString();
            if (ftpFileTypeText.equals("") && ServerFileType.getType(activity, ftpServerTypeSpinner.getText().toString()) == FTP_SERVER) {
                showBasicAlert(activity, activity.getString(R.string.error_dot), activity.getString(R.string.setup_please_select_security_type));
                return;
            }

            String fileNetwork = fileNetworkEditText.getText().toString();
            if (fileNetwork.equals("") && ServerFileType.getType(activity, serversTypeSpinner.getText().toString()) != DEVICE_SERVER) {
                showBasicAlert(activity, activity.getString(R.string.error_dot), activity.getString(R.string.setup_please_put_file_name));
                return;
            }

            String userName = userNameEditText.getText().toString();
            if (userName.equals("") && ServerFileType.getType(activity, serversTypeSpinner.getText().toString()) != DEVICE_SERVER) {
                showBasicAlert(activity, activity.getString(R.string.error_dot), activity.getString(R.string.setup_please_put_user_name));
                return;
            }

            String password = passwordEditText.getText().toString();
            if (password.equals("") && ServerFileType.getType(activity, serversTypeSpinner.getText().toString()) != DEVICE_SERVER) {
                showBasicAlert(activity, activity.getString(R.string.error_dot), activity.getString(R.string.setup_please_put_password));
                return;
            }

            String serverFilePath = serverFilePathTextView.getText().toString();
            if (serverFilePath.equals("") && ServerFileType.getType(activity, serversTypeSpinner.getText().toString()) != DEVICE_SERVER) {
                showBasicAlert(activity, activity.getString(R.string.error_dot), activity.getString(R.string.setup_please_put_pdf_path));
                return;
            }

            if (ServerFileType.getType(activity, serversTypeSpinner.getText().toString()) != DEVICE_SERVER) {
                if (serverFilePath.charAt(serverFilePath.length() - 1) != '\\' && serverFilePath.charAt(serverFilePath.length() - 1) != '/') {
                    serverFilePathTextView.setText(serverFilePath += '/');
                }
            }

            // Get Orientaion Type Value
            if (isOrientationLevelVisible(setupFile.getLicenseType())) {
                String orientationFileTypeText = orientationTypeSpinner.getText().toString();
                if (orientationFileTypeText.equals("")) {
                    showBasicAlert(activity, activity.getString(R.string.error_dot), activity.getString(R.string.setup_please_select_file_type));
                    return;
                }
            }

            String adminPassword = configPassword.getText().toString();
            if (adminPassword.equals("") && setupFile == null) {
                if (isCallToSave) {
                    showBasicAlert(activity, activity.getString(R.string.error_dot), activity.getString(R.string.setup_please_put_admin_password1));
                    return;
                }
            }
            String adminPasswordText = "";
            if (setupFile.getAdminPassword().equals("")) {
                adminPasswordText = adminPassword;
            } else {
                adminPasswordText = setupFile.getAdminPassword();
            }

            if (isAdminPasswordAsk) {
                if (adminPassword.equals("")) {
                    if (isCallToSave) {
                        showBasicAlert(activity, activity.getString(R.string.error_dot), activity.getString(R.string.setup_please_put_admin_password1));
                        return;
                    }
                }
            }

            SetupFile setupFile = new SetupFile.SetupBuilder().setDeviceName(deviceName)
                    .setDevLicenseOptionType(DevLicenseOptionType.getType(activity, licenseOptionFileTypeText).getValue())
                    .setLicenseType(LicenseFileType.getType(activity, licenseFileTypeText).getValue())
                    .setServerType(ServerFileType.getType(activity, serverFileTypeText).getValue())
                    .setFtpFileType(FTPServerFileType.getType(activity, ftpFileTypeText).getValue())
                    .setFileNetwork(fileNetwork)
                    .setUserName(userName)
                    .setPassword(password)
                    .setServerFilePath(serverFilePath)
                    .setOrientationFileType(OrientationFileType.getType(activity, orientationTypeSpinner.getText().toString()).getValue())
                    .setAdminPassword(adminPasswordText)
                    .setLanguageFileType(LanguageFileType.getType(activity, languageTypeSpinner.getText().toString()).getValue())
                    .setColourFileType(ColourThemeFileType.getType(activity, colorTypeSpinner.getText().toString()).getValue()).build();
            setupFile.saveFile();
            mContext.setSetupFile(setupFile);
            this.setupFile = setupFile;
            if (isCallToSave) {
                changeLanguage(LanguageFileType.getLocal(LanguageFileType.getType(setupFile.getLanguageFileType())));
                setCustomeTheme(ColourThemeFileType.getType(setupFile.getColourFileType()).getValue());
            }

            Toast.makeText(activity, activity.getString(R.string.setup_setup_has_been_saved), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(activity, activity.getString(R.string.error_dot) + Common.BACK_N + e.toString(),
                    Toast.LENGTH_SHORT).show();
        } catch (SimpleCryptoException e) {
            Toast.makeText(activity, activity.getString(R.string.error_dot) + Common.BACK_N + e.toString(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void changeLanguage(String languageToLoad) {
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        activity.getResources().updateConfiguration(config, activity.getResources().getDisplayMetrics());
    }

    private void setCustomeTheme(int themeIndex) {
        ColourThemeFileType.setTheme(activity, ColourThemeFileType.getType(themeIndex));
        activity.recreate();
    }

    private void loadNormalSetupFile() {
        if (setupFile == null) {
            return;
        }

        onSetLayoutVisiblity(setupFile);
    }

    public void onSetLayoutVisiblity(SetupFile signItSetup) {
        if (signItSetup.getServerType() == FTP_SERVER.getValue()) {
            setBtnVisiblity(true);

        } else if (signItSetup.getServerType() == FILE_SERVER.getValue()) {
            setBtnVisiblity(true);

        } else if (signItSetup.getServerType() == DEVICE_SERVER.getValue()) {
            setBtnVisiblity(false);

        }
    }

    private void setBtnVisiblity(Boolean visiblity) {
        Button button = (Button) rootView.findViewById(R.id.testconnect);
        if (button != null)
            button.setEnabled(visiblity);

        if (adminOptionsBtn != null)
            adminOptionsBtn.setEnabled(true);

        if (testConnectBtn != null)
            testConnectBtn.setEnabled(visiblity);

    }

    private void loadLicenseTypeSpinnerData() {
        // Load License Type array acording to License Option selection
        licenseTypeSpinner.setAdapter(new ArrayAdapter<String>(activity, R.layout.spinner_item, LicenseFileType.getList(activity,
                DevLicenseOptionType.getType(activity, devLicenseOptionSpinner.getText().toString()).getValue())));
        licenseTypeSpinner.invalidate();

    }

    private void loadServerTypeSpinnerData(int position) {
        serversTypeSpinner.setAdapter(new ArrayAdapter<String>(activity, R.layout.spinner_item,
                ServerFileType.getList(activity, position)));
        serversTypeSpinner.invalidate();
        loadServerSetupView(ServerFileType.getType(activity, serversTypeSpinner.getText().toString()).getValue());
    }

    private void setupAdminLayout() {
        adminIncludeView.setVisibility(View.VISIBLE);

        loadNormalSetupFile();
    }


    public void testConnect() {

        try {
            onSaveSetup(false);
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        if (setupFile.getServerType() == FTP_SERVER.getValue()) {
//
//            new TestFtpConnection(activity, setupFile).execute(setupFile.getFileNetwork(), setupFile.getUserName(),
//                    setupFile.getPassword(), setupFile.getServerPath(), setupFile.getDeviceName());
//
//        } else if (setupFile.getServerType() == FILE_SERVER.getValue()) {
//
//            new TestSambaConnection(activity, setupFile).execute(setupFile.getFileNetwork(), setupFile.getUserName(),
//                    setupFile.getPassword(), setupFile.getServerPath(), setupFile.getDeviceName());
//
//        } else if (setupFile.getServerType() == GOOGLE_DRIVE_SERVER.getValue()) {
//            getResultsFromApi();
//        }

    }

    @Override
    public void onComplite(boolean isComplite) {
        if (isComplite) {
            File licenseFile = new File(SharedStaticObjects.LicenseFilePath);
            if (!licenseFile.exists()) {
                try {
                    onSaveSetup(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mContext.switchScreen(ScreenEnums.SCREEN_LICENCE, activity, null, setupFile);
            }
        }
    }
}
