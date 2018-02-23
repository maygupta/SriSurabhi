package com.socal.connection.sri.surabhi.media.screen.Setup.FTP;

//import com.socal.connection.sri.surabhi.media.screen.Setup.Utils.JsonXmlReadWrite;
//import com.socal.connection.sri.surabhi.media.utils.Common;
//import com.socal.connection.sri.surabhi.media.utils.SharedStaticObjects;
//import com.jcraft.jsch.ChannelSftp;
//import com.jcraft.jsch.SftpException;
//
//import org.apache.commons.net.ftp.FTPClient;
//import org.apache.commons.net.ftp.FTPSClient;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Locale;

/**
 * Created by logix028 on 15/03/17.
 */

public class FTPServerUtils {

//    public static void syncPinPasswordFileFTP(FTPClient client, String ftpSetupPath) {
//        try {
//            client.changeWorkingDirectory(ftpSetupPath);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        File setupPasswordFileToExport = new File(SharedStaticObjects.PdfOpenPasswordFilePath);
//
//        /* password file operation */
//        if (setupPasswordFileToExport.exists()) {
//            File passwordfilePath = new File(SharedStaticObjects._PdfOpenPasswordFilePath);
//            try {
//                client.retrieveFile(ftpSetupPath + SharedStaticObjects.PasswordFileName, new FileOutputStream(passwordfilePath));
//                JsonXmlReadWrite._readPasswordJsonFile(SharedStaticObjects.AppSetupPath + SharedStaticObjects._PasswordFileName);
//                Date start = new SimpleDateFormat(Common.DATE_TIME_FORMAT, Locale.ENGLISH).parse(SetupFile.passwordFileTime);
//                Date end = new SimpleDateFormat(Common.DATE_TIME_FORMAT, Locale.ENGLISH).parse(SetupFile._passwordFileTime);
//                if (start.compareTo(end) > 0) {
//                    if (setupPasswordFileToExport.exists()) {
//                        client.appendFile(ftpSetupPath + SharedStaticObjects.PasswordFileName, new FileInputStream(setupPasswordFileToExport));
//                    }
//                } else {
//                    SetupFile.userPassword.clear();
//                    for (String string : SetupFile._userPassword) {
//                        SetupFile.userPassword.add(string);
//                    }
//                    JsonXmlReadWrite.writePasswordJsonFile(SharedStaticObjects.AppSetupPath + SharedStaticObjects.PasswordFileName);
//                    if (setupPasswordFileToExport.exists()) {
//                        client.appendFile(ftpSetupPath + SharedStaticObjects.PasswordFileName, new FileInputStream(setupPasswordFileToExport));
//                    }
//                }
//            } catch (Exception e) {
//                if (setupPasswordFileToExport.exists()) {
//                    try {
//                        client.appendFile(ftpSetupPath + SharedStaticObjects.PasswordFileName, new FileInputStream(setupPasswordFileToExport));
//                    } catch (Exception e1) {
//                        e1.printStackTrace();
//                    }
//                }
//            }
//            if (passwordfilePath.exists()) {
//                passwordfilePath.delete();
//            }
//        } else {
//            try {
//                client.retrieveFile(ftpSetupPath + setupPasswordFileToExport.getName(), new FileOutputStream(setupPasswordFileToExport));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        /* pin file operation */
//        File setupPinFileToExport = new File(SharedStaticObjects.PdfOpenPinFilePath);
//        if (setupPinFileToExport.exists()) {
//            File pinfilePath = new File(SharedStaticObjects._PdfOpenPinFilePath);
//            try {
//                client.retrieveFile(ftpSetupPath + SharedStaticObjects.PinFileName, new FileOutputStream(pinfilePath));
//                JsonXmlReadWrite._readPinJsonFile(SharedStaticObjects.AppSetupPath + SharedStaticObjects._PinFileName);
//
//                Date start = new SimpleDateFormat(Common.DATE_TIME_FORMAT, Locale.ENGLISH).parse(SetupFile.pinFileTime);
//                Date end = new SimpleDateFormat(Common.DATE_TIME_FORMAT, Locale.ENGLISH).parse(SetupFile._pinFileTime);
//                if (start.compareTo(end) > 0) {
//                    if (setupPinFileToExport.exists()) {
//                        client.appendFile(ftpSetupPath + SharedStaticObjects.PinFileName, new FileInputStream(setupPinFileToExport));
//                    }
//                } else {
//                    SetupFile.userPinCode.clear();
//                    for (PinData pinData : SetupFile._userPinCode) {
//                        PinData data = new PinData();
//                        data.setNane((String) pinData.getName());
//                        data.setInitial((String) pinData.getInitial());
//                        data.setPin((String) pinData.getPin());
//                        SetupFile.userPinCode.add(data);
//                    }
//
//                    JsonXmlReadWrite.writePinJsonFile(SharedStaticObjects.AppSetupPath + SharedStaticObjects.PinFileName);
//                    if (setupPinFileToExport.exists()) {
//                        client.appendFile(ftpSetupPath + SharedStaticObjects.PinFileName, new FileInputStream(setupPinFileToExport));
//                    }
//                }
//            } catch (Exception e) {
//                if (setupPinFileToExport.exists()) {
//                    try {
//                        client.appendFile(ftpSetupPath + SharedStaticObjects.PinFileName, new FileInputStream(setupPinFileToExport));
//                    } catch (Exception e1) {
//                        e1.printStackTrace();
//                    }
//                }
//            }
//            if (pinfilePath.exists()) {
//                pinfilePath.delete();
//            }
//        } else {
//            try {
//                client.retrieveFile(ftpSetupPath + setupPinFileToExport.getName(), new FileOutputStream(setupPinFileToExport));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        JsonXmlReadWrite.readPasswordJsonFile(SharedStaticObjects.AppSetupPath + SharedStaticObjects.PasswordFileName);
//        JsonXmlReadWrite.readPinJsonFile(SharedStaticObjects.AppSetupPath + SharedStaticObjects.PinFileName);
//    }

//    public static void syncPinPasswordFileFTPS(FTPSClient client, String ftpSetupPath) throws IOException, ParseException {
//        client.changeWorkingDirectory(ftpSetupPath);
//
//        /* password file operation */
//        File setupPasswordFileToExport = new File(SharedStaticObjects.PdfOpenPasswordFilePath);
//        if (setupPasswordFileToExport.exists()) {
//            File passwordfilePath = new File(SharedStaticObjects._PdfOpenPasswordFilePath);
//            try {
//                client.retrieveFile(ftpSetupPath + SharedStaticObjects.PasswordFileName, new FileOutputStream(SharedStaticObjects._PdfOpenPasswordFilePath));
//                JsonXmlReadWrite._readPasswordJsonFile(SharedStaticObjects.AppSetupPath + SharedStaticObjects._PasswordFileName);
//
//                Date start = new SimpleDateFormat(Common.DATE_TIME_FORMAT, Locale.ENGLISH).parse(SetupFile.passwordFileTime);
//                Date end = new SimpleDateFormat(Common.DATE_TIME_FORMAT, Locale.ENGLISH).parse(SetupFile._passwordFileTime);
//                if (start.compareTo(end) > 0) {
//                    if (setupPasswordFileToExport.exists()) {
//                        client.storeFile(ftpSetupPath + setupPasswordFileToExport.getName(), new FileInputStream(setupPasswordFileToExport));
//                    }
//                } else {
//                    SetupFile.userPassword.clear();
//                    for (String string : SetupFile._userPassword) {
//                        SetupFile.userPassword.add(string);
//                    }
//
//                    JsonXmlReadWrite.writePasswordJsonFile(SharedStaticObjects.AppSetupPath + SharedStaticObjects.PasswordFileName);
//                    if (setupPasswordFileToExport.exists()) {
//                        client.storeFile(ftpSetupPath + setupPasswordFileToExport.getName(), new FileInputStream(setupPasswordFileToExport));
//                    }
//                }
//            } catch (Exception e) {
//                if (setupPasswordFileToExport.exists()) {
//                    client.storeFile(ftpSetupPath + setupPasswordFileToExport.getName(), new FileInputStream(setupPasswordFileToExport));
//                }
//            }
//            if (passwordfilePath.exists()) {
//                passwordfilePath.delete();
//            }
//        } else {
//            client.retrieveFile(ftpSetupPath + SharedStaticObjects.PasswordFileName, new FileOutputStream(SharedStaticObjects.PdfOpenPasswordFilePath));
//        }
//        /* pin file operation */
//        File setupPinFileToExport = new File(SharedStaticObjects.PdfOpenPinFilePath);
//        if (setupPinFileToExport.exists()) {
//            File pinfilePath = new File(SharedStaticObjects._PdfOpenPinFilePath);
//            try {
//                client.retrieveFile(ftpSetupPath + SharedStaticObjects.PinFileName, new FileOutputStream(SharedStaticObjects._PdfOpenPinFilePath));
//                JsonXmlReadWrite._readPinJsonFile(SharedStaticObjects.AppSetupPath + SharedStaticObjects._PinFileName);
//
//                Date start = new SimpleDateFormat(Common.DATE_TIME_FORMAT, Locale.ENGLISH).parse(SetupFile.pinFileTime);
//                Date end = new SimpleDateFormat(Common.DATE_TIME_FORMAT, Locale.ENGLISH).parse(SetupFile._pinFileTime);
//                if (start.compareTo(end) > 0) {
//                    if (setupPinFileToExport.exists()) {
//                        client.storeFile(ftpSetupPath + setupPinFileToExport.getName(), new FileInputStream(setupPinFileToExport));
//                    }
//                } else {
//                    SetupFile.userPinCode.clear();
//                    for (PinData pinData : SetupFile._userPinCode) {
//                        PinData data = new PinData();
//                        data.setNane((String) pinData.getName());
//                        data.setInitial((String) pinData.getInitial());
//                        data.setPin((String) pinData.getPin());
//                        SetupFile.userPinCode.add(data);
//                    }
//
//                    JsonXmlReadWrite.writePinJsonFile(SharedStaticObjects.AppSetupPath + SharedStaticObjects.PinFileName);
//                    if (setupPinFileToExport.exists()) {
//                        client.storeFile(ftpSetupPath + setupPinFileToExport.getName(), new FileInputStream(setupPinFileToExport));
//                    }
//                }
//            } catch (Exception e) {
//                if (setupPinFileToExport.exists()) {
//                    client.storeFile(ftpSetupPath + setupPinFileToExport.getName(), new FileInputStream(setupPinFileToExport));
//                }
//            }
//            if (pinfilePath.exists()) {
//                pinfilePath.delete();
//            }
//        } else {
//
//        }
//        JsonXmlReadWrite.readPasswordJsonFile(SharedStaticObjects.AppSetupPath + SharedStaticObjects.PasswordFileName);
//        JsonXmlReadWrite.readPinJsonFile(SharedStaticObjects.AppSetupPath + SharedStaticObjects.PinFileName);
//    }

//    public static void syncPinPasswordFileSFTP(ChannelSftp channelSftp, String sftpSetupPath) throws SftpException {
//        channelSftp.cd(sftpSetupPath);
//        /* password file operation */
//        File setupPasswordFileToExport = new File(SharedStaticObjects.PdfOpenPasswordFilePath);
//        if (setupPasswordFileToExport.exists()) {
//            File passwordfilePath = new File(SharedStaticObjects._PdfOpenPasswordFilePath);
//            try {
//                channelSftp.get(sftpSetupPath + SharedStaticObjects.PasswordFileName, SharedStaticObjects._PdfOpenPasswordFilePath);
//                JsonXmlReadWrite._readPasswordJsonFile(SharedStaticObjects.AppSetupPath + SharedStaticObjects._PasswordFileName);
//
//                Date start = new SimpleDateFormat(Common.DATE_TIME_FORMAT, Locale.ENGLISH).parse(SetupFile.passwordFileTime);
//                Date end = new SimpleDateFormat(Common.DATE_TIME_FORMAT, Locale.ENGLISH).parse(SetupFile._passwordFileTime);
//
//                if (start.compareTo(end) > 0) {
//                    File setupFileToExport = new File(SharedStaticObjects.PdfOpenPasswordFilePath);
//                    if (setupFileToExport.exists()) {
//                        channelSftp.put(new FileInputStream(SharedStaticObjects.PdfOpenPasswordFilePath), SharedStaticObjects.PasswordFileName);
//                    }
//                } else {
//
//                    SetupFile.userPassword.clear();
//                    for (String string : SetupFile._userPassword) {
//                        SetupFile.userPassword.add(string);
//                    }
//
//                    JsonXmlReadWrite.writePasswordJsonFile(SharedStaticObjects.AppSetupPath + SharedStaticObjects.PasswordFileName);
//                    if (setupPasswordFileToExport.exists()) {
//                        channelSftp.put(new FileInputStream(SharedStaticObjects.PdfOpenPasswordFilePath), SharedStaticObjects.PasswordFileName);
//                    }
//                }
//            } catch (Exception e) {
//                if (setupPasswordFileToExport.exists()) {
//                    try {
//                        channelSftp.put(new FileInputStream(SharedStaticObjects.PdfOpenPasswordFilePath), SharedStaticObjects.PasswordFileName);
//                    } catch (FileNotFoundException e1) {
//                        e1.printStackTrace();
//                    }
//                }
//            }
//            if (passwordfilePath.exists()) {
//                passwordfilePath.delete();
//            }
//
//        } else {
//            try {
//                channelSftp.put(new FileInputStream(SharedStaticObjects.PdfOpenPasswordFilePath), SharedStaticObjects.PasswordFileName);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//
//        /* pin file operation */
//        File setupPinFileToExport = new File(SharedStaticObjects.PdfOpenPinFilePath);
//        if (setupPinFileToExport.exists()) {
//            File pinfilePath = new File(SharedStaticObjects._PdfOpenPinFilePath);
//            try {
//                channelSftp.get(sftpSetupPath + SharedStaticObjects.PinFileName, SharedStaticObjects._PdfOpenPinFilePath);
//                JsonXmlReadWrite._readPinJsonFile(SharedStaticObjects.AppSetupPath + SharedStaticObjects._PinFileName);
//
//                Date start = new SimpleDateFormat(Common.DATE_TIME_FORMAT, Locale.ENGLISH).parse(SetupFile.pinFileTime);
//                Date end = new SimpleDateFormat(Common.DATE_TIME_FORMAT, Locale.ENGLISH).parse(SetupFile._pinFileTime);
//
//                if (start.compareTo(end) > 0) {
//                    if (setupPinFileToExport.exists()) {
//                        channelSftp.put(new FileInputStream(SharedStaticObjects.PdfOpenPinFilePath), SharedStaticObjects.PinFileName);
//                    }
//                } else {
//                    SetupFile.userPinCode.clear();
//                    for (PinData pinData : SetupFile._userPinCode) {
//                        PinData data = new PinData();
//                        data.setNane((String) pinData.getName());
//                        data.setInitial((String) pinData.getInitial());
//                        data.setPin((String) pinData.getPin());
//                        SetupFile.userPinCode.add(data);
//                    }
//
//                    JsonXmlReadWrite.writePinJsonFile(SharedStaticObjects.AppSetupPath + SharedStaticObjects.PinFileName);
//                    if (setupPinFileToExport.exists()) {
//                        channelSftp.put(new FileInputStream(SharedStaticObjects.PdfOpenPinFilePath), SharedStaticObjects.PinFileName);
//                    }
//                }
//            } catch (Exception e) {
//                if (setupPinFileToExport.exists()) {
//                    try {
//                        channelSftp.put(new FileInputStream(SharedStaticObjects.PdfOpenPinFilePath), SharedStaticObjects.PinFileName);
//                    } catch (FileNotFoundException e1) {
//                        e1.printStackTrace();
//                    }
//                }
//            }
//            if (pinfilePath.exists()) {
//                pinfilePath.delete();
//            }
//        } else {
//            if (setupPinFileToExport.exists()) {
//                try {
//                    channelSftp.put(new FileInputStream(SharedStaticObjects.PdfOpenPinFilePath), SharedStaticObjects.PinFileName);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        JsonXmlReadWrite.readPasswordJsonFile(SharedStaticObjects.AppSetupPath + SharedStaticObjects.PasswordFileName);
//        JsonXmlReadWrite.readPinJsonFile(SharedStaticObjects.AppSetupPath + SharedStaticObjects.PinFileName);
//    }

}
