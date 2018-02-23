//package com.socal.connection.sri.surabhi.media.screen.Setup.FTP;
//
//import android.app.Activity;
//import android.os.AsyncTask;
//
//import com.demo.com.single.activity.sample.R;
//import com.socal.connection.sri.surabhi.media.screen.Setup.Enum.FTPServerFileType;
//import com.socal.connection.sri.surabhi.media.screen.Setup.Utils.SetupFile;
//import com.socal.connection.sri.surabhi.media.utils.CommonUtil;
//import com.socal.connection.sri.surabhi.media.utils.License.SiteLicenceCommon;
//
//import java.io.PrintWriter;
//import java.util.Properties;
//
//
///**
// * Created by logix028 on 15/03/17.
// */
//
//public class TestFtpConnection extends AsyncTask<String, Void, Boolean> {
//    private Boolean _testLicence;
//    private Activity activity;
//    private SetupFile signItSetup = null;
//    private String _errorText = "";
//
//    public TestFtpConnection(Activity activity, SetupFile signItSetup) {
//        this.activity = activity;
////        this._testLicence = signItSetup.getDoCheckLicenceOnSync();
//        this.activity = activity;
//        this.signItSetup = signItSetup;
//    }
//
//    @Override
//    protected Boolean doInBackground(String... params) {
//        boolean isLicenseActive = false;
//
//        if (signItSetup.getFtpFileType() == FTPServerFileType.FTP.getValue()) {
//            FTPClient client = new FTPClient();
//            client.enterLocalPassiveMode();
//            String test = "Connect to server";
//            String endLine = System.getProperty("line.separator");
//            // Connect to Server
//            try {
//                String fileserver = params[0];
//                String username = params[1];
//                String password = params[2];
//                String ftpPath = params[3];
//                String deviceName = params[4];
//                String ftpSetupFolder = signItSetup.getServerPath() + "setup/";
//                // connect
//                client.connect(fileserver);// "174.3.160.243"
//                test += " passed " + endLine + "Log on to server";
//                client.login(username, password);// "apptest","9AppTest9img4"
//                test += " passed " + endLine + "Find " + activity.getString(R.string.setup_server_path_label);
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
//                    return false;
//                }
//
//                client.changeWorkingDirectory(ftpPath);
//                return true;
//            } catch (Exception e) {
//                _errorText = test + " failed. \r\n\r\nError: " + e.toString();
//                return false;
//            } finally {
//                try {
//                    client.disconnect();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        } else if (signItSetup.getFtpFileType() == FTPServerFileType.FTPS.getValue()) {
//            FTPSClient clientFtps = null;
//            String test = "Connect to server";
//            String endLine = System.getProperty("line.separator");
//            try {
//                String fileserver = params[0];
//                String username = params[1];
//                String password = params[2];
//                String ftpPath = params[3];
//                String deviceName = params[4];
//                String ftpsSetupFolder = signItSetup.getServerPath() + "setup/";
//                clientFtps = new FTPSClient("SSL");
//                clientFtps.setAuthValue("SSL");
//                clientFtps.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
//                int reply;
//                clientFtps.connect(fileserver, 21);
//                reply = clientFtps.getReplyCode();
//                if (!FTPReply.isPositiveCompletion(reply)) {
//                    clientFtps.disconnect();
//                    System.exit(1);
//                }
//                clientFtps.login(username, password);
//                clientFtps.execPBSZ(0);
//                clientFtps.execPROT("P");
//                clientFtps.setBufferSize(1000);
//                clientFtps.enterLocalPassiveMode();
//                clientFtps.changeWorkingDirectory("/");
//                clientFtps.changeWorkingDirectory("/files");
//                clientFtps.setFileType(FTP.BINARY_FILE_TYPE);
//
//                try {
//                    if (this._testLicence) {
//                        isLicenseActive = SiteLicenceCommon.ReadFileFtpsLicenseFile(clientFtps, signItSetup);
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
//                return true;
//            } catch (Exception e) {
//                _errorText = test + " failed. \r\n\r\nError: " + e.toString();
//                return false;
//            } finally {
//                try {
//                    clientFtps.disconnect();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//        } else if (signItSetup.getFtpFileType() == FTPServerFileType.SFTP.getValue()) {
//
//            boolean conStatus = false;
//            Session session = null;
//            Channel channel = null;
//            Properties config = new Properties();
//            config.put("StrictHostKeyChecking", "no");
//            String test = "Connect to server";
//            String endLine = System.getProperty("line.separator");
//            try {
//                String fileserver = params[0];
//                String username = params[1];
//                String password = params[2];
//                String ftpPath = params[3];
//                String deviceName = params[4];
//                String sftpSetupFolder = signItSetup.getServerPath() + "setup/";
//                JSch ssh = new JSch();
//                session = ssh.getSession(username, fileserver, 22);
//                test += " passed " + endLine + "set UserName,fileservetpath, post";
//                session.setPassword(password);
//                test += "" + endLine + " set password";
//                session.setConfig(config);
//                test += "" + endLine + "Log on to server";
//                session.connect();
//                test += "" + endLine + "connection sucessfull";
//                conStatus = session.isConnected();
//                test += "" + endLine + "connection session check= " + conStatus;
//                channel = session.openChannel("sftp");
//                test += "" + endLine + "aleartDialog channel and connect.";
//                channel.connect();
//                ChannelSftp sftp = (ChannelSftp) channel;
//                test += "" + endLine + "channel connect sucessfully.";
//                ftpPath = sftp.getHome() + ftpPath;
//                sftp.cd(ftpPath);
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
//                return true;
//            } catch (Exception e) {
//                _errorText = test + " failed. \r\n\r\nError: " + e.toString();
//                return false;
//            } finally {
//                try {
//                    session.disconnect();
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
//    protected void onPostExecute(Boolean result) {
//        CommonUtil.progressDismiss();
//        if (result) {
//            CommonUtil.showBasicAlert(activity, activity.getString(R.string.setup_test_connection_dot),
//                    activity.getString(R.string.setup_server_has_been_connected_successfully));
//        } else {
//            CommonUtil.showBasicAlert(activity, activity.getString(R.string.setup_test_connection_dot), _errorText);
//        }
//    }
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//        CommonUtil.progressShow(activity, activity.getString(R.string.setup_test));
//    }
//}