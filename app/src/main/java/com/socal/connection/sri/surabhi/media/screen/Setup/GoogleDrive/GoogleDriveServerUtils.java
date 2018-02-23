//package com.socal.connection.sri.surabhi.media.screen.Setup.GoogleDrive;
//
//import android.app.Activity;
//
//
//import com.demo.com.single.activity.sample.R;
//import com.socal.connection.sri.surabhi.media.utils.CommonUtil;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.net.UnknownHostException;
//import java.util.ArrayList;
//
//import jcifs.smb.NtlmPasswordAuthentication;
//import jcifs.smb.SmbException;
//import jcifs.smb.SmbFile;
//import jcifs.smb.SmbFileInputStream;
//import jcifs.smb.SmbFileOutputStream;
//
//public class GoogleDriveServerUtils {
//
//    public static boolean login(final String userName, final String password, final String destinationPath) {
//
//        final String user = userName + ":" + password;
//        final NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(user);
//        final SmbFile sFile;
//        try {
//            sFile = new SmbFile(destinationPath, auth);
//            sFile.listFiles();
//            return false;
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//            return true;
//        } catch (SmbException e) {
//            e.printStackTrace();
//            return true;
//        }
//    }
//
//    public static SmbFile[] getList(final String userName, final String password, final String destinationPath) {
//        SmbFile[] domains;
//        final String user = userName + ":" + password;
//        final NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(user);
//        final SmbFile sFile;
//        try {
//            sFile = new SmbFile(destinationPath, auth);
//            domains = sFile.listFiles();
//            return domains;
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//            return null;
//        } catch (SmbException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public static String[] getListName(final String userName, final String password, final String destinationPath) {
//        SmbFile[] domains;
//        final String user = userName + ":" + password;
//        final NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(user);
//        final SmbFile sFile;
//        try {
//            sFile = new SmbFile(destinationPath, auth);
//            domains = sFile.listFiles();
//            ArrayList<String> fileName = new ArrayList<String>();
//            for (int i = 0; i < domains.length; i++) {
//                if (!domains[i].isDirectory()) {
//                    fileName.add(domains[i].getName());
//                }
//            }
//            return fileName.toArray(new String[fileName.size()]);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//            return null;
//        } catch (SmbException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public static boolean mkdir(final String userName, final String password, final String destinationPath) {
//        SmbFile[] domains;
//        final String user = userName + ":" + password;
//        final NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(user);
//        final SmbFile sFile;
//        try {
//            sFile = new SmbFile(destinationPath, auth);
//            sFile.mkdirs();
//            return true;
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//            return false;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public static boolean upload(final String userName, final String password, final File sourcePath, final String destinationPath) {
//
//        final String user = userName + ":" + password;
//        final NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(user);
//        final SmbFile sFile;
//        try {
//            sFile = new SmbFile(destinationPath, auth);
//
//            final SmbFileOutputStream smbFileOutputStream = new SmbFileOutputStream(sFile);
//            final FileInputStream fileInputStream = new FileInputStream(sourcePath);
//
//            final byte[] buf = new byte[16 * 1024 * 1024];
//            int len;
//            while ((len = fileInputStream.read(buf)) > 0) {
//                smbFileOutputStream.write(buf, 0, len);
//            }
//            fileInputStream.close();
//            smbFileOutputStream.close();
//            return true;
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//            return false;
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//            return false;
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            return false;
//        } catch (SmbException e) {
//            e.printStackTrace();
//            return false;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public static void rename(String userName, String password, String _pathFrom, String _pathTo) throws IOException {
//        final String user = userName + ":" + password;
//        final NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(user);
//        try {
//            SmbFile smbFileFrom = new SmbFile(_pathFrom, auth);
//            SmbFile smbFileTo = new SmbFile(_pathTo, auth);
//            if (smbFileTo.exists()) {
//                smbFileTo.delete();
//            }
//            smbFileFrom.renameTo(smbFileTo);
//        } catch (Exception _ex) {
//            _ex.printStackTrace();
//        }
//    }
//
//    public static boolean remove(Activity activity, String userName, String password, String _path)
//            throws IOException {
//        final String user = userName + ":" + password;
//        final NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(user);
//        boolean removed = false;
//        try {
//            SmbFile removeFile = new SmbFile(_path, auth);
//            if (removeFile.exists()) {
//                removeFile.delete();
//                removed = true;
//            }
//            if (!removed) {
//                CommonUtil.showBasicAlert(activity, activity.getString(R.string.error_dot),
//                        activity.getString(R.string.could_not_remove_non_existing) + " [" + _path + "].");
//            } else {
//                CommonUtil.showBasicAlert(activity, activity.getString(R.string.error_dot),
//                        activity.getString(R.string.successfully_removed_resource) + " [" + _path + "].");
//            }
//        } catch (Exception e) {
//
//        }
//        return removed;
//    }
//
//
//    public static boolean download(final String userName, final String password, final File sourcePath,
//                                   final String destinationPath) {
//
//        final String user = userName + ":" + password;
//        final NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(user);
//        final SmbFile sFile;
//        try {
//            sFile = new SmbFile(destinationPath, auth);
//            SmbFileInputStream mFStream = new SmbFileInputStream(sFile);
//            FileOutputStream mFileOutputStream = new FileOutputStream(sourcePath);
//            byte[] buffer = new byte[1024];
//            int len1 = 0;
//            while ((len1 = mFStream.read(buffer)) > 0) {
//                mFileOutputStream.write(buffer, 0, len1);
//            }
//            mFileOutputStream.close();
//            mFStream.close();
//            return true;
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//            return false;
//        } catch (SmbException e) {
//            e.printStackTrace();
//            return false;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//}
