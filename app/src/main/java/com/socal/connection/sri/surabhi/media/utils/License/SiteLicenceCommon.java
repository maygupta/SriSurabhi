//package com.socal.connection.sri.surabhi.media.utils.License;
//
//import android.content.Context;
//
//import com.socal.connection.sri.surabhi.media.screen.Setup.Utils.SetupFile;
//import com.socal.connection.sri.surabhi.media.utils.SharedStaticObjects;
//
//import com.jcraft.jsch.ChannelSftp;
//
//import org.apache.commons.net.ftp.FTPClient;
//import org.apache.commons.net.ftp.FTPSClient;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//
//import static com.socal.connection.sri.surabhi.media.utils.SharedStaticObjects.SMB_TAG;
//
///**
// * Created by logix028 on 12/05/17.
// */
//
//public class SiteLicenceCommon {
//
//    /*
//    * This method is responsible to check Site Licencing to Google Drive Server.
//    *
//    * */
//
////    public static boolean ReadFileGoogleDriveLicenseFile(Drive mService, SetupFile setupFile) {
////        return ReadFileGoogleDriveLicenseFile(mService, setupFile.getFileNetwork(), setupFile);
////    }
//
////    public static boolean ReadFileGoogleDriveLicenseFile(Drive mService, String fileId, SetupFile setupFile) {
////        File licencePath = new File(SharedStaticObjects.AppSetupPath);
////        File licenseFile = new File(licencePath, SharedStaticObjects.TempLicenseFileName);
////        boolean isDownloaded = download(mService, fileId, licenseFile.getNameID(), licenseFile.getPath(),
////                SharedStaticObjects.LicenseFileName, setupFile.getServerPath() + SharedStaticObjects.SiteLicenseFolderName);
////        if (isDownloaded) {
////            String licenceEncText = readText(licenseFile.getPath(), licenseFile.getNameID());
////            try {
////                String licenceDecText = SimpleCrypto.LicenseDecrypt(licenceEncText, Common.DFI_DEC_KAY);
////                String[] licenceTextSplit = licenceDecText.split("_");
////                String currentLicenseName = getLicenseText(setupFile.getLicenseType());
////                if (licenceTextSplit[1].equals(setupFile.getFileNetwork()) && licenceTextSplit[2].equals(currentLicenseName)) {
////                    return true;
////                }
////            } catch (SimpleCryptoException e) {
////                e.printStackTrace();
////                return false;
////            }
////        }
////        return false;
////    }
//
//    public static boolean ReadFileFtpLicenseFile(FTPClient connectedClient, SetupFile setupInfo) throws IllegalStateException {
//        return ReadFileFtpLicenseFile(connectedClient, setupInfo.getDeviceName(), setupInfo.getServerPath(), setupInfo.getFileNetwork(), setupInfo);
//    }
//
//    public static boolean ReadFileFtpLicenseFile(FTPClient connectedClient, String deviceName, String ftpPath,
//                                                 String fileNetwork, SetupFile setupInfo) throws IllegalStateException {
//
////        String fileLine = null;
////        String licenseFileValue = "";
////        BufferedReader streamReader;
////        FileReader inputStream;
////
////        File licencePath = new File(SharedStaticObjects.AppSetupPath);
////        File licenseFile = new File(licencePath, SharedStaticObjects.TempLicenseFileName);
////
////        try {
////            licencePath.mkdirs();
////            if (!licencePath.isDirectory())
////                return false;
////            if (licenseFile.exists() && !licenseFile.delete()) {
////                return false;
////            }
////            connectedClient.changeWorkingDirectory(ftpPath);
////            OutputStream outputStream = new FileOutputStream(licenseFile);
////            connectedClient.retrieveFile(ftpPath + SharedStaticObjects.SiteLicenseFileName, outputStream);
////            inputStream = new FileReader(licenseFile);
////            streamReader = new BufferedReader(inputStream);
////            try {
////                while ((fileLine = streamReader.readLine()) != null) {
////                    licenseFileValue += fileLine;
////                }
////            } finally {
////                streamReader.close();
////                inputStream.close();
////                licenseFile.delete();
////            }
////            try {
////                String licenceDecText = SimpleCrypto.LicenseDecrypt(licenseFileValue, Common.DFI_DEC_KAY);
////                String[] licenceTextSplit = licenceDecText.split("_");
////                String currentLicenseName = getLicenseText(setupInfo.getLicenseType());
////                if (licenceTextSplit[1].equals(setupInfo.getFileNetwork()) && licenceTextSplit[2].equals(currentLicenseName)) {
////                    return true;
////                }
////            } catch (SimpleCryptoException e) {
////                e.printStackTrace();
////                return false;
////            }
////        } catch (FileNotFoundException e) {
////            e.printStackTrace();
////            return false;
////        } catch (IOException e) {
////            e.printStackTrace();
////            return false;
////        } finally {
////            licenseFile.delete();
////        }
//        return false;
//    }
//
//    public static boolean ReadFileFtpsLicenseFile(FTPSClient connectedClient, SetupFile setupInfo) throws IllegalStateException {
//        return ReadFileFtpsLicenseFile(connectedClient, setupInfo.getDeviceName(), setupInfo.getServerPath(), setupInfo.getFileNetwork(), setupInfo);
//    }
//
//    public static boolean ReadFileFtpsLicenseFile(FTPSClient connectedClient, String deviceName, String ftpPath,
//                                                  String fileNetwork, SetupFile setupInfo) throws IllegalStateException {
//
////        String fileLine = null;
////        String licenseFileValue = "";
////        BufferedReader streamReader;
////        FileReader inputStream;
////        File licencePath = new File(SharedStaticObjects.AppSetupPath);
////        File licenseFile = new File(licencePath, SharedStaticObjects.TempLicenseFileName);
////        try {
////            licencePath.mkdirs();
////            if (!licencePath.isDirectory())
////                return false;
////            if (licenseFile.exists() && !licenseFile.delete())
////                return false;
////            connectedClient.changeWorkingDirectory(ftpPath);
////            connectedClient.retrieveFile(ftpPath + SharedStaticObjects.SiteLicenseFileName, new FileOutputStream(licenseFile));
////            inputStream = new FileReader(licenseFile);
////            streamReader = new BufferedReader(inputStream);
////            try {
////                while ((fileLine = streamReader.readLine()) != null) {
////                    licenseFileValue += fileLine;
////                }
////            } finally {
////                streamReader.close();
////                inputStream.close();
////                licenseFile.delete();
////            }
////            try {
////                String licenceDecText = SimpleCrypto.LicenseDecrypt(licenseFileValue, Common.DFI_DEC_KAY);
////                String[] licenceTextSplit = licenceDecText.split("_");
////                String currentLicenseName = getLicenseText(setupInfo.getLicenseType());
////                if (licenceTextSplit[1].equals(setupInfo.getFileNetwork()) && licenceTextSplit[2].equals(currentLicenseName)) {
////                    return true;
////                }
////            } catch (SimpleCryptoException e) {
////                e.printStackTrace();
////                return false;
////            }
////        } catch (FileNotFoundException e) {
////            e.printStackTrace();
////            return false;
////        } catch (IOException e) {
////            e.printStackTrace();
////            return false;
////        } finally {
////            licenseFile.delete();
////        }
//
//        return false;
//    }
//
//    public static boolean ReadFileSftpLicenseFile(ChannelSftp channelSftp, SetupFile setupInfo) throws IllegalStateException {
//        return ReadFileSftpLicenseFile(channelSftp, setupInfo.getDeviceName(), setupInfo.getServerPath(), setupInfo.getFileNetwork(), setupInfo);
//    }
//
//    public static boolean ReadFileSftpLicenseFile(ChannelSftp channelSftp, String deviceName, String ftpPath,
//                                                  String fileNetwork, SetupFile setupInfo) throws IllegalStateException {
//
////        String fileLine = null;
////        String licenseFileValue = "";
////        BufferedReader streamReader;
////        FileReader inputStream;
////
////        File licencePath = new File(SharedStaticObjects.AppSetupPath);
////        File licenseFile = new File(licencePath, SharedStaticObjects.TempLicenseFileName);
////        try {
////            licencePath.mkdirs();
////            if (!licencePath.isDirectory()) {
////                return false;
////            }
////            if (licenseFile.exists() && !licenseFile.delete()) {
////                return false;
////            }
////            channelSftp.cd(ftpPath);
////            channelSftp.get(channelSftp.getHome() + ftpPath + SharedStaticObjects.SiteLicenseFileName, licencePath + "/" + licenseFile.getNameID());
////            inputStream = new FileReader(licenseFile.getAbsolutePath());
////            streamReader = new BufferedReader(inputStream);
////            try {
////
////                while ((fileLine = streamReader.readLine()) != null) {
////                    licenseFileValue += fileLine;
////                }
////            } finally {
////                streamReader.close();
////                inputStream.close();
////                licenseFile.delete();
////            }
////            try {
////                String licenceDecText = SimpleCrypto.LicenseDecrypt(licenseFileValue, Common.DFI_DEC_KAY);
////                String[] licenceTextSplit = licenceDecText.split("_");
////                String currentLicenseName = getLicenseText(setupInfo.getLicenseType());
////                if (licenceTextSplit[1].equals(setupInfo.getFileNetwork()) && licenceTextSplit[2].equals(currentLicenseName)) {
////                    return true;
////                }
////            } catch (SimpleCryptoException e) {
////                e.printStackTrace();
////                return false;
////            }
////        } catch (FileNotFoundException e) {
////            return false;
////        } catch (IOException e) {
////            return false;
////        } catch (SftpException e) {
////            e.printStackTrace();
////            return false;
////        } finally {
////            licenseFile.delete();
////        }
//        return false;
//    }
//
//    public static boolean ReadFileSambaLicenseFile(SetupFile setupInfo) throws IllegalStateException {
//        return ReadFileSambaLicenseFile(setupInfo.getUserName(), setupInfo.getPassword(), setupInfo.getServerPath(),
//                setupInfo.getDeviceName(), setupInfo.getFileNetwork(), setupInfo);
//    }
//
//    public static boolean ReadFileSambaLicenseFile(String username, String password, String sambaPath,
//                                                   String deviceName, String fileNetwork, SetupFile setupInfo) {
//
////        String fileLine = null;
////        String licenseFileValue = "";
////        BufferedReader streamReader;
////        FileReader inputStream;
////
////        File licencePath = new File(SharedStaticObjects.AppSetupPath);
////        File licenseFile = new File(licencePath, SharedStaticObjects.TempLicenseFileName);
////
////        try {
////            licencePath.mkdirs();
////            if (!licencePath.isDirectory())
////                return false;
////            if (licenseFile.exists() && !licenseFile.delete()) {
////                return false;
////            }
////            String sambaLicensePath = SMB_TAG + fileNetwork + sambaPath + SharedStaticObjects.SiteLicenseFileName;
////            SambaServer.download(username, password, licenseFile, sambaLicensePath);
////
////            inputStream = new FileReader(licenseFile);
////            streamReader = new BufferedReader(inputStream);
////            try {
////                while ((fileLine = streamReader.readLine()) != null) {
////                    licenseFileValue += fileLine;
////                }
////            } finally {
////                streamReader.close();
////                inputStream.close();
////                licenseFile.delete();
////            }
////            try {
////                String licenceDecText = SimpleCrypto.LicenseDecrypt(licenseFileValue, Common.DFI_DEC_KAY);
////                String[] licenceTextSplit = licenceDecText.split("_");
////                String currentLicenseName = getLicenseText(setupInfo.getLicenseType());
////                if (licenceTextSplit[1].equals(setupInfo.getFileNetwork()) && licenceTextSplit[2].equals(currentLicenseName)) {
////                    return true;
////                }
////            } catch (SimpleCryptoException e) {
////                e.printStackTrace();
////                return false;
////            }
////        } catch (FileNotFoundException e) {
////            e.printStackTrace();
////            return false;
////        } catch (IOException e) {
////            e.printStackTrace();
////            return false;
////        } finally {
////            licenseFile.delete();
////        }
//        return false;
//    }
//
//    /*
//    * This method is responsible to check local licensing.
//    *
//    * */
//
////    private static String getLicenseText(int index) {
////        if (index == 0) {
////            return Common.SIGN_FREE;
////        } else if (index == 1) {
////            return Common.SIGN_LITE;
////        } else if (index == 2) {
////            return Common.SIGN_STANDARD;
////        } else if (index == 3) {
////            return Common.SIGN_ENTERPRISE;
////        } else {
////            return Common.SIGN_FREE;
////        }
////    }
//
//    public static boolean checkLocalLicence(Context activity) {
//        String licenceEncText = readText(SharedStaticObjects.AppSetupPath, SharedStaticObjects.LicenseFileName);
//        SignItLicenseFile licenseToValidate = new SignItLicenseFile(activity, licenceEncText);
//        return licenseToValidate.isValid();
//    }
//
//    private static String readText(String path, String filename) {
//
//        File file = new File(path, filename);
//
//        StringBuilder text = new StringBuilder();
//
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(file));
//            String line;
//
//            while ((line = br.readLine()) != null) {
//                text.append(line);
//                text.append('\n');
//            }
//            br.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return text.toString();
//    }
//}
