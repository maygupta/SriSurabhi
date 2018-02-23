//package com.socal.connection.sri.surabhi.media.screen.Setup.FTP;
//
//import android.app.Activity;
//import android.os.AsyncTask;
//import android.widget.Toast;
//
//import com.demo.com.single.activity.sample.R;
//import com.socal.connection.sri.surabhi.media.screen.Setup.SpinnerEnum.FTPServerType;
//import com.socal.connection.sri.surabhi.media.screen.Setup.Utils.SetupFile;
//import com.socal.connection.sri.surabhi.media.utils.CommonUtil;
//import com.socal.connection.sri.surabhi.media.utils.License.SiteLicenceCommon;
//import com.socal.connection.sri.surabhi.media.utils.SharedStaticObjects;
//import com.jcraft.jsch.Channel;
//import com.jcraft.jsch.ChannelSftp;
//import com.jcraft.jsch.JSch;
//import com.jcraft.jsch.Session;
//import com.jcraft.jsch.SftpATTRS;
//import com.jcraft.jsch.SftpException;
//
//import org.apache.commons.net.PrintCommandListener;
//import org.apache.commons.net.ftp.FTP;
//import org.apache.commons.net.ftp.FTPClient;
//import org.apache.commons.net.ftp.FTPReply;
//import org.apache.commons.net.ftp.FTPSClient;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Properties;
//import java.util.Vector;
//
///**
// * Created by logix028 on 15/03/17.
// */
//
//public class FTPExportImportSetupTask extends AsyncTask<String, Void, Boolean> {
//    private final int _acceptTypeIndex = 0;
//    private final int _setupFileIndex = 1;
//    private final int _passwordFileIndex = 2;
//    private final int _pinFileIndex = 3;
//    private boolean _importExportSetup = false;
//    private boolean _testLicence = false;
//    Activity activity;
//    SetupFile signItSetup;
//
//    String _errorText = "";
//
//    public FTPExportImportSetupTask(Activity activity, SetupFile signItSetup, boolean _importExportSetup) {
//        this.activity = activity;
//        this.signItSetup = signItSetup;
//        this._importExportSetup = _importExportSetup;
//        this._testLicence = signItSetup.getDoCheckLicenceOnSync();
//    }
//
//    @Override
//    protected Boolean doInBackground(String... params) {
//        boolean isLicenseActive;
//
//        if (signItSetup.getSecurityType() == FTPServerType.FTP.getValue()) {
//
//            String[] filesToDowload;
//            FTPClient client = new FTPClient();
//            String test = "Connecting to server failed";
//            File setupFolderPath = null;
//            String ftpSetupPath;
//
//            // Connect to Server
//            filesToDowload = new String[4];
//            filesToDowload[_acceptTypeIndex] = SharedStaticObjects.AcceptTypeFileName;
//            filesToDowload[_setupFileIndex] = SharedStaticObjects.SetupFileName;
//            filesToDowload[_passwordFileIndex] = SharedStaticObjects.PasswordFileName;
//            filesToDowload[_pinFileIndex] = SharedStaticObjects.PinFileName;
//
//            try {
//                String fileserver = params[0];
//                String username = params[1];
//                String password = params[2];
//                String ftpPath = params[3];
//                setupFolderPath = new File(ftpPath, "setup");
//                ftpSetupPath = setupFolderPath.getPath() + '/';
//
//                // connect
//                client.connect(fileserver);
//                test = "Log on to server failed";
//                client.login(username, password);
//                test = "Find " + activity.getString(R.string.setup_server_path_label) + " failed.";
//                client.changeWorkingDirectory(ftpPath);
//
//                try {
//                    if (this._testLicence) {
//                        isLicenseActive = SiteLicenceCommon.ReadFileFtpLicenseFile(client, signItSetup);
//                    } else {
//                        isLicenseActive = SiteLicenceCommon.checkLocalLicence(activity);
//                    }
//                    if (!isLicenseActive) {
//                        _errorText = "Licence is not verify, so please contact with Admin or Try again!";
//                        return false;
//                    }
//                } catch (Exception e) {
//                    _errorText = "Licence is not verify, so please contact with Admin or Try again!";
//
//                    return false;
//                }
//
//                if (_importExportSetup) {
//                    _errorText = doImportInBackground_FTP(filesToDowload, client, ftpSetupPath);
//                    if (_errorText == null) {
//                        return true;
//                    }
//                    return false;
//                }
//                _errorText = this.doExportInBackground_FTP(client, ftpSetupPath);
//                if (_errorText == null) {
//                    return true;
//                }
//                return false;
//            } catch (Exception e) {
//                _errorText = test + " failed. \r\n\r\nError: " + e.toString();
//                return false;
//            } finally {
//                try {
//                    client.disconnect();
//                } catch (Exception e) {
//                    // do nothing
//                    e.printStackTrace();
//                }
//            }
//        } else if (signItSetup.getSecurityType() == FTPServerType.FTPS.getValue()) {
//            String[] filesToDowload;
//
//            FTPSClient client = null;
//            String test = "Connecting to server failed";
//            File setupFolderPath = null;
//            String ftpSetupPath;
//
//            // Connect to Server
//            filesToDowload = new String[4];
//            filesToDowload[_acceptTypeIndex] = SharedStaticObjects.AcceptTypeFileName;
//            filesToDowload[_setupFileIndex] = SharedStaticObjects.SetupFileName;
//            filesToDowload[_passwordFileIndex] = SharedStaticObjects.PasswordFileName;
//            filesToDowload[_pinFileIndex] = SharedStaticObjects.PinFileName;
//
//            try {
//                String fileserver = params[0];
//                String username = params[1];
//                String password = params[2];
//                String ftpPath = params[3];
//                setupFolderPath = new File(ftpPath, "setup");
//                ftpSetupPath = setupFolderPath.getPath() + '/';
//                // connect
//                client = new FTPSClient("SSL");
//                client.setAuthValue("SSL");
//                client.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
//                int reply;
//                client.connect(fileserver, 21);
//                test = "Log on to server failed";
//                reply = client.getReplyCode();
//                if (!FTPReply.isPositiveCompletion(reply)) {
//                    client.disconnect();
//                    System.err.println("PDF server refused connection.");
//                    System.exit(1);
//                }
//                client.login(username, password);
//                test = "Find " + activity.getString(R.string.setup_server_path_label) + " failed.";
//                client.execPBSZ(0);
//                client.execPROT("P");
//                client.setBufferSize(1000);
//                client.enterLocalPassiveMode();
//                client.changeWorkingDirectory("/");
//                client.changeWorkingDirectory(ftpPath); //path where my files are
//                client.setFileType(FTP.BINARY_FILE_TYPE);
//
//                try {
//                    if (this._testLicence) {
//                        isLicenseActive = SiteLicenceCommon.ReadFileFtpsLicenseFile(client, signItSetup);
//                    } else {
//                        isLicenseActive = SiteLicenceCommon.checkLocalLicence(activity);
//                    }
//                    if (!isLicenseActive) {
//                        _errorText = "Licence is not verify, so please contact with Admin or Try again!";
//                        return false;
//                    }
//                } catch (Exception e) {
//                    _errorText = "Licence is not verify, so please contact with Admin or Try again!";
//                    return false;
//                }
//
//                if (_importExportSetup) {
//                    _errorText = doImportInBackground_FTPS(filesToDowload, client, ftpSetupPath);
//                    if (_errorText == null) {
//                        return true;
//                    }
//                    return false;
//                }
//                _errorText = this.doExportInBackground_FTPS(client, ftpSetupPath);
//                if (_errorText == null) {
//                    return true;
//                }
//
//                return false;
//            } catch (Exception e) {
//                _errorText = test + " failed. \r\n\r\nError: " + e.toString();
//                return false;
//            } finally {
//                try {
//                    client.disconnect();
//                } catch (Exception e) {
//                    // do nothing
//                    e.printStackTrace();
//                }
//            }
//        } else if (signItSetup.getSecurityType() == FTPServerType.SFTP.getValue()) {
//
//            String[] filesToDowload;
//            String test = "Connecting to server failed";
//            File setupFolderPath = null;
//            String sftpSetupPath;
//            Session session = null;
//            Channel channel = null;
//            Properties config = new Properties();
//            config.put("StrictHostKeyChecking", "no");
//
//            // Connect to Server
//            filesToDowload = new String[4];
//            filesToDowload[_acceptTypeIndex] = SharedStaticObjects.AcceptTypeFileName;
//            filesToDowload[_setupFileIndex] = SharedStaticObjects.SetupFileName;
//            filesToDowload[_passwordFileIndex] = SharedStaticObjects.PasswordFileName;
//            filesToDowload[_pinFileIndex] = SharedStaticObjects.PinFileName;
//
//            try {
//                String fileserver = params[0];
//                String username = params[1];
//                String password = params[2];
//                String ftpPath = params[3];
//                setupFolderPath = new File(ftpPath, "setup");
//
//                test = "Log on to server failed";
//                // connect
//                JSch ssh = new JSch();
//                session = ssh.getSession(username, fileserver, 22);
//                session.setPassword(password);
//                session.setConfig(config);
//                session.connect();
//                test = "Find " + activity.getString(R.string.setup_server_path_label) + " failed.";
//                channel = session.openChannel("sftp");
//                channel.connect();
//                ChannelSftp sftp = (ChannelSftp) channel;
//                sftpSetupPath = sftp.getHome() + "" + setupFolderPath.getPath() + '/';
//
//                try {
//                    if (this._testLicence) {
//                        isLicenseActive = SiteLicenceCommon.ReadFileSftpLicenseFile(sftp, signItSetup);
//                    } else {
//                        isLicenseActive = SiteLicenceCommon.checkLocalLicence(activity);
//                    }
//                    if (!isLicenseActive) {
//                        _errorText = "Licence is not verify, so please contact with Admin or Try again!";
//                        return false;
//                    }
//                } catch (Exception e) {
//                    _errorText = "Licence is not verify, so please contact with Admin or Try again!";
//                    return false;
//                }
//
//                if (_importExportSetup) {
//                    _errorText = doImportInBackground_SFTP(filesToDowload, sftp, sftpSetupPath);
//                    if (_errorText == null) {
//                        return true;
//                    }
//                    return false;
//                }
//                _errorText = this.doExportInBackground_SFTP(sftp, sftpSetupPath);
//                if (_errorText == null) {
//                    return true;
//                }
//
//                return false;
//            } catch (Exception e) {
//                _errorText = test + " failed. \r\n\r\nError: " + e.toString();
//                return false;
//            } finally {
//                try {
//                    session.disconnect();
//                    channel.disconnect();
//                } catch (Exception e) {
//                    // do nothing
//                    e.printStackTrace();
//                }
//            }
//        }
//        return false;
//    }
//
//    @Override
//    protected void onPostExecute(Boolean isImportExportSetup) {
//        super.onPostExecute(isImportExportSetup);
//        CommonUtil.progressDismiss();
//        if (isImportExportSetup) {
//            if (this._importExportSetup) {
//                Toast.makeText(activity, activity.getString(R.string.file_is_imported_successfully),
//                        Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(activity, activity.getString(R.string.file_is_exported_successfully),
//                        Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            CommonUtil.showBasicAlert(activity, activity.getString(R.string.setup_import_export_dot), _errorText);
//        }
//    }
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//        if (this._importExportSetup) {
//            progressShow(activity, activity.getString(R.string.setupImport));
//        } else {
//            progressShow(activity, activity.getString(R.string.setupExport));
//        }
//    }
//
//    private String doImportInBackground_FTP(String[] filesToDowload, FTPClient client, String ftpSetupPath)
//            throws IOException, SimpleCryptoException {
//
//        File setupfilePath;
//        Boolean foundFile = false;
//        String value = "No setup files to import";
//        client.changeWorkingDirectory(ftpSetupPath);
//        String[] names = client.listNames();
//        if (names.length == 0) {
//            return value;
//        }
//        for (String name : names) {
//            for (int loopFile = 0; loopFile < filesToDowload.length; loopFile++) {
//                if (name.equalsIgnoreCase(filesToDowload[loopFile]) == true) {
//                    switch (loopFile) {
//                        case _acceptTypeIndex:
//                            setupfilePath = new File(SharedStaticObjects.AcceptTypeFilePath);
//                            break;
//                        case _setupFileIndex:
//                            setupfilePath = new File(SharedStaticObjects.SetupFilePath);
//                            break;
//                        case _passwordFileIndex:
//                            setupfilePath = new File(SharedStaticObjects.PdfOpenPasswordFilePath);
//                            break;
//                        case _pinFileIndex:
//                            setupfilePath = new File(SharedStaticObjects.PdfOpenPinFilePath);
//                            break;
//                        default:
//                            continue;
//                    }
//                    foundFile = true;
//                    client.retrieveFile(ftpSetupPath + name, new FileOutputStream(setupfilePath));
//                    SignITActivity mainActivity = SignITActivity.getInstance();
//                    mainActivity.setSetupFile(SetupFile.readFile());
//                    break;
//                }
//            }
//        }
//
//        if (!foundFile)
//            return value;
//
//        return null;
//    }
//
//    private String doImportInBackground_FTPS(String[] filesToDowload, FTPSClient client, String ftpSetupPath) throws IOException {
//        File setupfilePath;
//        Boolean foundFile = false;
//        String test = "No setup files to import";
//        client.changeWorkingDirectory(ftpSetupPath);
//        String[] names = client.listNames();
//        if (names.length == 0)
//            return test;
//
//        for (String name : names) {
//            for (int loopFile = 0; loopFile < filesToDowload.length; loopFile++) {
//                if (name.equalsIgnoreCase(filesToDowload[loopFile]) == true) {
//                    switch (loopFile) {
//                        case _acceptTypeIndex:
//                            setupfilePath = new File(SharedStaticObjects.AcceptTypeFilePath);
//                            break;
//                        case _setupFileIndex:
//                            setupfilePath = new File(SharedStaticObjects.SetupFilePath);
//                            break;
//                        case _passwordFileIndex:
//                            setupfilePath = new File(SharedStaticObjects.PdfOpenPasswordFilePath);
//                            break;
//                        case _pinFileIndex:
//                            setupfilePath = new File(SharedStaticObjects.PdfOpenPinFilePath);
//                            break;
//                        default:
//                            continue;
//                    }
//                    foundFile = true;
//                    client.retrieveFile(name, new FileOutputStream(setupfilePath));
//                    break;
//                }
//            }
//        }
//
//        if (!foundFile)
//            return test;
//
//        return null;
//    }
//
//    private String doImportInBackground_SFTP(String[] filesToDowload, ChannelSftp channelSftp, String sftpSetupPath) {
//        try {
//            String setupfilePath;
//            Boolean foundFile = false;
//            String test = "No setup files to import";
//            channelSftp.cd(sftpSetupPath);
//            Vector<ChannelSftp.LsEntry> entries = channelSftp.ls("*.*");
//            String[] names = new String[entries.size()];
//            for (ChannelSftp.LsEntry name : entries) {
//                names[entries.indexOf(name)] = name.getFilename();
//            }
//            if (names.length == 0) {
//                return test;
//            }
//            for (String name : names) {
//                for (int loopFile = 0; loopFile < filesToDowload.length; loopFile++) {
//                    if (name.equalsIgnoreCase(filesToDowload[loopFile]) == true) {
//                        switch (loopFile) {
//                            case _acceptTypeIndex:
//                                setupfilePath = (SharedStaticObjects.AcceptTypeFilePath);
//                                break;
//                            case _setupFileIndex:
//                                setupfilePath = (SharedStaticObjects.SetupFilePath);
//                                break;
//                            case _passwordFileIndex:
//                                setupfilePath = (SharedStaticObjects.PdfOpenPasswordFilePath);
//                                break;
//                            case _pinFileIndex:
//                                setupfilePath = (SharedStaticObjects.PdfOpenPinFilePath);
//                                break;
//                            default:
//                                continue;
//                        }
//                        foundFile = true;
//                        channelSftp.get(name, setupfilePath);
//                        break;
//                    }
//                }
//            }
//            if (!foundFile) {
//                return test;
//            }
//        } catch (SftpException e) {
//            e.getStackTrace();
//        } catch (Exception e) {
//            e.getStackTrace();
//        }
//        return null;
//    }
//
//    private String doExportInBackground_FTP(FTPClient connectedClient, String ftpSetupPath) {
//        File setupFileToExport = null;
//        try {
//            try {
//                connectedClient.changeWorkingDirectory(ftpSetupPath);
//            } catch (Exception e) {
//                try {
//                    connectedClient.changeWorkingDirectory(ftpSetupPath);
//                } catch (Exception e1) {
//                    return "Unable to create setup folder on PDF server";
//                }
//                connectedClient.changeWorkingDirectory(ftpSetupPath);
//            }
//        } catch (Exception e) {
//            return "Unable to find or create setup folder on PDF server";
//        }
//        try {
//            // No need to check if file exists as it is saved just before export
//            setupFileToExport = new File(SharedStaticObjects.SetupFilePath);
//            connectedClient.appendFile(ftpSetupPath + SharedStaticObjects.SetupFileName, new FileInputStream(setupFileToExport));
//        } catch (Exception e) {
//            return "Unable to create Setup file on PDF server";
//        }
//        try {
//            // No need to check if file exists as it is saved just before export
//            setupFileToExport = new File(SharedStaticObjects.PdfOpenPasswordFilePath);
//            if (setupFileToExport.exists()) {
//                connectedClient.appendFile(ftpSetupPath + SharedStaticObjects.PasswordFileName, new FileInputStream(setupFileToExport));
//            }
//        } catch (Exception e) {
//            return "Unable to create password file on PDF server";
//        }
//        try {
//            // No need to check if file exists as it is saved just before export
//            setupFileToExport = new File(SharedStaticObjects.PdfOpenPinFilePath);
//            if (setupFileToExport.exists()) {
//                connectedClient.appendFile(ftpSetupPath + SharedStaticObjects.PinFileName, new FileInputStream(setupFileToExport));
//            }
//        } catch (Exception e) {
//            return "Unable to create pin file on PDF server";
//        }
//        try {
//            setupFileToExport = new File(SharedStaticObjects.AcceptTypeFilePath);
//            if (!setupFileToExport.exists()) {
//                AcceptType.createDefaultAcceptTypeFile(setupFileToExport);
//            }
//            connectedClient.appendFile(ftpSetupPath + SharedStaticObjects.AcceptTypeFileName, new FileInputStream(setupFileToExport));
//        } catch (Exception e) {
//            return "Unable to create Accept Type file on PDF server";
//        }
//
//        return null;
//    }
//
//    private String doExportInBackground_FTPS(FTPSClient connectedClient, String ftpSetupPath) {
//        File setupFileToExport = null;
//
//        try {
//            boolean isExitDir = connectedClient.changeWorkingDirectory(ftpSetupPath);
//            if (!isExitDir) {
//                connectedClient.makeDirectory(ftpSetupPath);
//                connectedClient.changeWorkingDirectory(ftpSetupPath);
//            }
//        } catch (Exception e) {
//            return "Unable to find or create setup folder on FTPS server";
//        }
//
//        try {
//            // No need to check if file exists as it is saved just before export
//            setupFileToExport = new File(SharedStaticObjects.SetupFilePath);
//            connectedClient.storeFile(ftpSetupPath + setupFileToExport.getName(), new FileInputStream(setupFileToExport));
//        } catch (Exception e) {
//            e.getStackTrace();
//            return "Unable to create Setup file on PDF server";
//        }
//
//        try {
//            // No need to check if file exists as it is saved just before export
//            setupFileToExport = new File(SharedStaticObjects.PdfOpenPasswordFilePath);
//            connectedClient.storeFile(ftpSetupPath + setupFileToExport.getName(), new FileInputStream(setupFileToExport));
//        } catch (Exception e) {
//            e.getStackTrace();
//            return "Unable to create password file on PDF server";
//        }
//
//        try {
//            // No need to check if file exists as it is saved just before export
//            setupFileToExport = new File(SharedStaticObjects.PdfOpenPinFilePath);
//            connectedClient.storeFile(ftpSetupPath + setupFileToExport.getName(), new FileInputStream(setupFileToExport));
//        } catch (Exception e) {
//            e.getStackTrace();
//            return "Unable to create pin file on PDF server";
//        }
//
//        try {
//            setupFileToExport = new File(SharedStaticObjects.AcceptTypeFilePath);
//            if (!setupFileToExport.exists()) {
//                AcceptType.createDefaultAcceptTypeFile(setupFileToExport);
//            }
//            connectedClient.storeFile(ftpSetupPath + setupFileToExport.getName(), new FileInputStream(setupFileToExport));
//        } catch (Exception e) {
//            return "Unable to create Accept Type file on PDF server";
//        }
//
//
//        return null;
//    }
//
//    private String doExportInBackground_SFTP(ChannelSftp channelSftp, String ftpSetupPath) {
//
//        File setupFileToExport = null;
//        try {
//            try {
//                channelSftp.cd(ftpSetupPath);
//            } catch (SftpException ex) {
//                try {
//                    createDirectory(channelSftp);
//                } catch (Exception e1) {
//                    return "Unable to create setup folder on PDF server";
//                }
//                channelSftp.cd(ftpSetupPath);
//            }
//        } catch (Exception e) {
//            return "Unable to find or create setup folder on PDF server";
//        }
//
//        try {
//            // No need to check if file exists as it is saved just before export
//            setupFileToExport = new File(SharedStaticObjects.SetupFilePath);
//            channelSftp.put(new FileInputStream(setupFileToExport), setupFileToExport.getName());
//        } catch (Exception e) {
//            return "Unable to create Setup file on PDF server";
//        }
//
//        try {
//            // No need to check if file exists as it is saved just before export
//            setupFileToExport = new File(SharedStaticObjects.PdfOpenPasswordFilePath);
//            channelSftp.put(new FileInputStream(setupFileToExport), setupFileToExport.getName());
//        } catch (Exception e) {
//            return "Unable to create password file on PDF server";
//        }
//
//        try {
//            // No need to check if file exists as it is saved just before export
//            setupFileToExport = new File(SharedStaticObjects.PdfOpenPinFilePath);
//            channelSftp.put(new FileInputStream(setupFileToExport), setupFileToExport.getName());
//        } catch (Exception e) {
//            return "Unable to create pin file on PDF server";
//        }
//
//        try {
//            setupFileToExport = new File(SharedStaticObjects.AcceptTypeFilePath);
//            if (!setupFileToExport.exists()) {
//                AcceptType.createDefaultAcceptTypeFile(setupFileToExport);
//            }
//            channelSftp.put(new FileInputStream(setupFileToExport), setupFileToExport.getName());
//        } catch (Exception e) {
//            return "Unable to create Accept Type file on PDF server";
//        }
//
//        return null;
//    }
//
//    private void createDirectory(ChannelSftp channelSftp) throws SftpException {
//        String currentDirectory = channelSftp.pwd();
//        String dir = "abc";
//        SftpATTRS attrs = null;
//        try {
//            attrs = channelSftp.stat(currentDirectory + "/" + dir);
//        } catch (Exception e) {
//            System.out.println(currentDirectory + "/" + dir + " not found");
//        }
//
//        if (attrs != null) {
//            System.out.println("Directory exists IsDir=" + attrs.isDir());
//        } else {
//            System.out.println("Creating dir " + dir);
//            channelSftp.mkdir(dir);
//        }
//    }
//}