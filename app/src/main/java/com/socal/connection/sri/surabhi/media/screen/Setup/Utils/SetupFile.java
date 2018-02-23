package com.socal.connection.sri.surabhi.media.screen.Setup.Utils;

import com.socal.connection.sri.surabhi.media.utils.JsonToMap;
import com.socal.connection.sri.surabhi.media.utils.License.SimpleCryptoException;
import com.socal.connection.sri.surabhi.media.utils.SharedStaticObjects;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.socal.connection.sri.surabhi.media.utils.Common.DFI_INC_KAY;
import static com.socal.connection.sri.surabhi.media.utils.License.SimpleCrypto.LicenseDecrypt;
import static com.socal.connection.sri.surabhi.media.utils.License.SimpleCrypto.LicenseEncrypt;

/**
 * Created by logix028 on 29/11/17.
 */

public class SetupFile {
    //required parameters

    private static String _setup_device_name_key = "Device_Name";
    private static String _setup_licenseOption_key = "License_Option";
    private static String _setup_licenseType_key = "License_Type";
    private static String _setup_server_key = "Server";
    private static String _setup_ftp_file_type_key = "FTP File Type";
    private static String _setup_fileNetwork_key = "File Network";
    private static String _setup_userName_serverId_key = "User Name Server Id";
    private static String _setup_password_serverFolder_key = "Password Server Folder";
    private static String _setup_serverPath_key = "Server Path";
    private static String _setup_orientation_key = "Orientation Type";
    private static String _setup_adminPassword_key = "Admin Password";
    private static String _setup_language_key = "Language Type";
    private static String _setup_colourType_key = "Colour Type";

    private String adminPassword = "";
    private String deviceName = "";
    private int devLicenseOptionType = 0;
    private int licenseType = 0;
    private int serverType = 0;
    private int ftpFileType = 0;
    private String fileNetwork = "";
    private String userName = "";
    private String password = "";
    private String serverPath = "";
    private int orientationFileType = 0;
    private int languageFileType = 0;
    private int colourFileType = 0;

    private SetupFile(SetupBuilder builder) {
        this.adminPassword = builder.adminPassword;
        this.deviceName = builder.deviceName;
        this.devLicenseOptionType = builder.devLicenseOptionType;
        this.licenseType = builder.licenseType;
        this.serverType = builder.serverType;
        this.ftpFileType = builder.ftpFileType;
        this.fileNetwork = builder.fileNetwork;
        this.userName = builder.userName;
        this.password = builder.password;
        this.serverPath = builder.serverPath;
        this.orientationFileType = builder.orientationFileType;
        this.languageFileType = builder.languageFileType;
        this.colourFileType = builder.colourFileType;
    }

    public boolean saveFile() throws IOException, SimpleCryptoException {
        Map<String, Object> setupLinkedHashMap = new LinkedHashMap<String, Object>();

        setupLinkedHashMap.put(_setup_device_name_key, LicenseEncrypt(this.getDeviceName(), DFI_INC_KAY));

        setupLinkedHashMap.put(_setup_fileNetwork_key, LicenseEncrypt(this.getFileNetwork(), DFI_INC_KAY));

        setupLinkedHashMap.put(_setup_userName_serverId_key, LicenseEncrypt(this.getUserName(), DFI_INC_KAY));

        setupLinkedHashMap.put(_setup_password_serverFolder_key, LicenseEncrypt(this.getPassword(), DFI_INC_KAY));

        setupLinkedHashMap.put(_setup_serverPath_key, LicenseEncrypt(this.getServerPath(), DFI_INC_KAY));

        setupLinkedHashMap.put(_setup_adminPassword_key, LicenseEncrypt(this.getAdminPassword(), DFI_INC_KAY));

        setupLinkedHashMap.put(_setup_licenseOption_key, LicenseEncrypt(String.valueOf(this.getDevLicenseOptionType()), DFI_INC_KAY));

        setupLinkedHashMap.put(_setup_licenseType_key, LicenseEncrypt(String.valueOf(this.getLicenseType()), DFI_INC_KAY));

        setupLinkedHashMap.put(_setup_server_key, LicenseEncrypt(String.valueOf(this.getServerType()), DFI_INC_KAY));

        setupLinkedHashMap.put(_setup_ftp_file_type_key, LicenseEncrypt(String.valueOf(this.getFtpFileType()), DFI_INC_KAY));

        setupLinkedHashMap.put(_setup_orientation_key, LicenseEncrypt(String.valueOf(this.getOrientationFileType()), DFI_INC_KAY));

        setupLinkedHashMap.put(_setup_language_key, LicenseEncrypt(String.valueOf(this.getLanguageFileType()), DFI_INC_KAY));

        setupLinkedHashMap.put(_setup_colourType_key, LicenseEncrypt(String.valueOf(this.getColourFileType()), DFI_INC_KAY));

        try {
            JSONObject json = new JSONObject(setupLinkedHashMap);

            try {
                FileWriter file = new FileWriter(SharedStaticObjects.SetupFilePath, false);
                file.write(json.toString());
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public int getDevLicenseOptionType() {
        return devLicenseOptionType;
    }

    public int getLicenseType() {
        return licenseType;
    }

    public int getServerType() {
        return serverType;
    }

    public int getFtpFileType() {
        return ftpFileType;
    }

    public String getFileNetwork() {
        return fileNetwork;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getServerPath() {
        return serverPath;
    }

    public int getOrientationFileType() {
        return orientationFileType;
    }

    public int getLanguageFileType() {
        return languageFileType;
    }

    public int getColourFileType() {
        return colourFileType;
    }

    //Builder Class
    public static class SetupBuilder {

        private String adminPassword = "";
        private String deviceName = "";
        private int devLicenseOptionType = 0;
        private int licenseType = 0;
        private int serverType = 0;
        private int ftpFileType = 0;
        private String fileNetwork = "";
        private String userName = "";
        private String password = "";
        private String serverPath = "";
        private int orientationFileType = 0;
        private int languageFileType = 0;
        private int colourFileType = 0;

        public SetupBuilder() {
            try {
                readFile();
            }  catch (SimpleCryptoException e) {
                e.printStackTrace();
            }
        }

        public static Map<String, Object> readSetupFile() throws IOException, JSONException {
            String jString = null;

            File yourFile = new File(SharedStaticObjects.SetupFilePath);
            if (!yourFile.exists()) {
                return new LinkedHashMap<String, Object>();
            }

            FileInputStream stream = new FileInputStream(yourFile);

            try {
                FileChannel fc = stream.getChannel();
                MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
                jString = Charset.defaultCharset().decode(bb).toString();
            } finally {
                stream.close();
            }

            JSONObject jsonObjMain = new JSONObject(jString);
            return JsonToMap.jsonToMap(jsonObjMain);
        }

        public SetupFile readFile() throws SimpleCryptoException {

            Map<String, Object> setupAllValue = null;
            try {
                setupAllValue = readSetupFile();
            } catch (IOException e) {
                e.printStackTrace();
                setupAllValue = new LinkedHashMap<>();
            } catch (JSONException e) {
                e.printStackTrace();
                setupAllValue = new LinkedHashMap<>();
            }
            if (setupAllValue.containsKey(_setup_device_name_key))
                this.setDeviceName(LicenseDecrypt((String) setupAllValue.get(_setup_device_name_key), DFI_INC_KAY));

            if (setupAllValue.containsKey(_setup_fileNetwork_key))
                this.setFileNetwork(LicenseDecrypt((String) setupAllValue.get(_setup_fileNetwork_key), DFI_INC_KAY));

            if (setupAllValue.containsKey(_setup_userName_serverId_key))
                this.setUserName(LicenseDecrypt((String) setupAllValue.get(_setup_userName_serverId_key), DFI_INC_KAY));

            if (setupAllValue.containsKey(_setup_password_serverFolder_key))
                this.setPassword(LicenseDecrypt((String) setupAllValue.get(_setup_password_serverFolder_key), DFI_INC_KAY));

            if (setupAllValue.containsKey(_setup_serverPath_key))
                this.setServerFilePath(LicenseDecrypt((String) setupAllValue.get(_setup_serverPath_key), DFI_INC_KAY));

            if (setupAllValue.containsKey(_setup_adminPassword_key))
                this.setAdminPassword(LicenseDecrypt((String) setupAllValue.get(_setup_adminPassword_key), DFI_INC_KAY));

            if (setupAllValue.containsKey(_setup_licenseOption_key))
                this.setDevLicenseOptionType(Integer.parseInt(LicenseDecrypt((String) setupAllValue.get(_setup_licenseOption_key), DFI_INC_KAY)));

            if (setupAllValue.containsKey(_setup_licenseType_key))
                this.setLicenseType(Integer.parseInt(LicenseDecrypt((String) setupAllValue.get(_setup_licenseType_key), DFI_INC_KAY)));

            if (setupAllValue.containsKey(_setup_server_key))
                this.setServerType(Integer.parseInt(LicenseDecrypt((String) setupAllValue.get(_setup_server_key), DFI_INC_KAY)));

            if (setupAllValue.containsKey(_setup_ftp_file_type_key))
                this.setFtpFileType(Integer.parseInt(LicenseDecrypt((String) setupAllValue.get(_setup_ftp_file_type_key), DFI_INC_KAY)));

            if (setupAllValue.containsKey(_setup_orientation_key))
                this.setOrientationFileType(Integer.parseInt(LicenseDecrypt((String) setupAllValue.get(_setup_orientation_key), DFI_INC_KAY)));

            if (setupAllValue.containsKey(_setup_language_key))
                this.setLanguageFileType(Integer.parseInt(LicenseDecrypt((String) setupAllValue.get(_setup_language_key), DFI_INC_KAY)));

            if (setupAllValue.containsKey(_setup_colourType_key))
                this.setColourFileType(Integer.parseInt(LicenseDecrypt((String) setupAllValue.get(_setup_colourType_key), DFI_INC_KAY)));

            return build();
        }

        public SetupBuilder setAdminPassword(String adminPassword) {
            this.adminPassword = adminPassword;
            return this;
        }

        public SetupBuilder setDeviceName(String deviceName) {
            this.deviceName = deviceName;
            return this;
        }

        public SetupBuilder setDevLicenseOptionType(int devLicenseOptionType) {
            this.devLicenseOptionType = devLicenseOptionType;
            return this;
        }

        public SetupBuilder setLicenseType(int licenseType) {
            this.licenseType = licenseType;
            return this;
        }

        public SetupBuilder setServerType(int serverType) {
            this.serverType = serverType;
            return this;
        }

        public SetupBuilder setFtpFileType(int ftpFileType) {
            this.ftpFileType = ftpFileType;
            return this;
        }

        public SetupBuilder setFileNetwork(String fileNetwork) {
            this.fileNetwork = fileNetwork;
            return this;
        }

        public SetupBuilder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public SetupBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public SetupBuilder setServerFilePath(String serverPath) {
            this.serverPath = serverPath;
            return this;
        }

        public SetupBuilder setOrientationFileType(int orientationFileType) {
            this.orientationFileType = orientationFileType;
            return this;
        }

        public SetupBuilder setLanguageFileType(int languageFileType) {
            this.languageFileType = languageFileType;
            return this;
        }

        public SetupBuilder setColourFileType(int colourFileType) {
            this.colourFileType = colourFileType;
            return this;
        }

        public SetupFile build() {
            return new SetupFile(this);
        }
    }
}
