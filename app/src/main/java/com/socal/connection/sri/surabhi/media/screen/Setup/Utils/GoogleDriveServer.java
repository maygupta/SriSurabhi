//package com.socal.connection.sri.surabhi.media.screen.Setup.Utils;
//
//
//import com.socal.connection.sri.surabhi.media.utils.Common;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.net.SocketTimeoutException;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//
//
//public class GoogleDriveServer {
//
//    public static boolean download(Drive mService, String rootFolderId, String localFileName,
//                                   String localPath, String serverFileName, String serverPath) {
//        try {
//
//            String fileId = getGoogleDriveFolderId(mService, rootFolderId, (serverPath + "/" + serverFileName).split("/"));
//            File pdfFile = new File(localPath, localFileName);
//            OutputStream outputStream = new FileOutputStream(pdfFile);
//            mService.files().get(fileId).executeMediaAndDownloadTo(outputStream);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            if (e instanceof SocketTimeoutException) {
//                download(mService, rootFolderId, localFileName, localPath, serverFileName, serverPath);
//            }
//            return false;
//        }
//    }
//
//    public static boolean downloadFile(Drive mService, String fileId, String localFileName, String localPath) {
//        try {
//
//            File pdfFile = new File(localPath, localFileName);
//            OutputStream outputStream = new FileOutputStream(pdfFile);
//            mService.files().get(fileId).executeMediaAndDownloadTo(outputStream);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            if (e instanceof SocketTimeoutException) {
//                downloadFile(mService, fileId, localFileName, localPath);
//            }
//            return false;
//        }
//    }
//
//    public static boolean upload(Drive mService, String rootId, String loaclFileName, String localPath, String serverFileName, String serverPath) {
//        try {
//            String folderId = getGoogleDriveFolderId(mService, rootId, serverPath.split("/"));
//            com.google.api.services.drive.model.File fileMetadata = new com.google.api.services.drive.model.File();
//            fileMetadata.setName(serverFileName);
//            java.io.File filePath = new java.io.File(localPath, loaclFileName);
//            FileContent mediaContent = null;
////            if (loaclFileName.contains(Common.XML_EXTENSION)) {
////                mediaContent = new FileContent(Common.XMLFileFormat, filePath);
////            } else if (loaclFileName.contains(Common.PDF_EXTENSION.toString())) {
////                mediaContent = new FileContent(Common.ApplicationPdfFileFormat, filePath);
////            } else if (loaclFileName.contains(Common.JSON_EXTENSION)) {
////                mediaContent = new FileContent(Common.TextPlaneFileFormat, filePath);
////            }
//
//            fileMetadata.setParents(Collections.singletonList(folderId));
//            mService.files().create(fileMetadata, mediaContent).setFields("id, parents").execute();
//
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            if (e instanceof SocketTimeoutException) {
//                upload(mService, rootId, loaclFileName, localPath, serverFileName, serverPath);
//            }
//            return true;
//        }
//    }
//
//    private static String getGoogleDriveFolderId(Drive mService, String fileNetworkId,
//                                                 String pathArray[]) throws IOException {
//        String[] dirList = removeBlankCellInArray(pathArray);
//
//        List<String> fileInfo = new ArrayList<String>();
//        Drive.Files.List request = null;
//
//        if (dirList.length >= 0) {
//            request = mService.files().list();
//            FileList files = request.setQ("'" + fileNetworkId.trim() + "' in parents and trashed=false").execute();
//            List<com.google.api.services.drive.model.File> filesList = files.getFiles();
//            if (files != null) {
//                for (com.google.api.services.drive.model.File file : filesList) {
//                    if (file.getName().trim().equalsIgnoreCase(dirList[0].replace("/", "").trim()))
//                        fileInfo.add(String.format("%s__%s", file.getName(), file.getId()));
//                }
//            }
//            request.setPageToken(files.getNextPageToken());
//        }
//
//        for (int index = 1; index < dirList.length; index++) {
//            String folderId = fileInfo.get(0).split("__")[1];
//            fileInfo.clear();
//            FileList filesSub = request.setQ("'" + folderId + "' in parents and trashed=false").execute();
//            List<com.google.api.services.drive.model.File> filesListSub = filesSub.getFiles();
//            if (filesSub != null) {
//                for (com.google.api.services.drive.model.File file : filesListSub) {
//                    if (file.getName().trim().equalsIgnoreCase(dirList[index].replace("/", "").trim())) {
//                        fileInfo.add(String.format("%s__%s", file.getName(), file.getId()));
//                    }
//                }
//            }
//        }
//        String folderId = fileInfo.get(0).split("__")[1];
//
//        return folderId;
//    }
//
//    private static String[] removeBlankCellInArray(String[] firstArray) {
//
//        List<String> list = new ArrayList<String>();
//
//        for (String s : firstArray) {
//            if (s != null && s.length() > 0) {
//                list.add(s);
//            }
//        }
//
//        return list.toArray(new String[list.size()]);
//    }
//
//    public static List<String> getGoogleDrivePDFFilesList(Drive mService, String fileNetworkId,
//                                                          String pathList[]) throws IOException {
//        String[] dirList = removeBlankCellInArray(pathList);
//        List<String> fileInfo = new ArrayList<String>();
//        Drive.Files.List request = null;
//
//        if (dirList.length >= 0) {
//            request = mService.files().list();
//            FileList files = request.setQ("'" + fileNetworkId.trim() + "' in parents and trashed=false").execute();
//            List<com.google.api.services.drive.model.File> filesList = files.getFiles();
//            if (files != null) {
//                for (com.google.api.services.drive.model.File file : filesList) {
//                    if (file.getName().trim().equalsIgnoreCase(dirList[0].replace("/", "").trim()))
//                        fileInfo.add(String.format("%s__%s", file.getName(), file.getId()));
//                }
//            }
//            request.setPageToken(files.getNextPageToken());
//        }
//
//        for (int index = 1; index < dirList.length; index++) {
//            String folderId = fileInfo.get(0).split("__")[1];
//            fileInfo.clear();
//            FileList filesSub = request.setQ("'" + folderId + "' in parents and trashed=false").execute();
//            List<com.google.api.services.drive.model.File> filesListSub = filesSub.getFiles();
//            if (filesSub != null) {
//                for (com.google.api.services.drive.model.File file : filesListSub) {
//                    if (file.getName().trim().equalsIgnoreCase(dirList[index].replace("/", "").trim())) {
//                        fileInfo.add(String.format("%s__%s", file.getName(), file.getId()));
//                    }
//                }
//            }
//        }
//
//        String folderId = fileInfo.get(0).split("__")[1];
//        fileInfo.clear();
//        FileList filesSub = request.setQ("'" + folderId + "' in parents and trashed=false").execute();
//        List<com.google.api.services.drive.model.File> filesListSub = filesSub.getFiles();
//        if (filesSub != null) {
//            for (com.google.api.services.drive.model.File file : filesListSub) {
//                if (file.getName().trim().contains(Common.PDF_EXTENSION))
//                    fileInfo.add(String.format("%s__%s", file.getName(), file.getId()));
//            }
//        }
//
//        return fileInfo;
//    }
//
//    public static List<String> getGoogleDriveFileFolderList(Drive mService, String fileNetworkId,
//                                                            String pathList[]) throws IOException {
//        String[] dirList = removeBlankCellInArray(pathList);
//        List<String> fileInfo = new ArrayList<String>();
//        Drive.Files.List request = null;
//
//        if (dirList.length >= 0) {
//            request = mService.files().list();
//            FileList files = request.setQ("'" + fileNetworkId.trim() + "' in parents and trashed=false").execute();
//            List<com.google.api.services.drive.model.File> filesList = files.getFiles();
//            if (files != null) {
//                for (com.google.api.services.drive.model.File file : filesList) {
//                    if (file.getName().trim().equalsIgnoreCase(dirList[0].replace("/", "").trim()))
//                        fileInfo.add(String.format("%s__%s", file.getName(), file.getId()));
//                }
//            }
//            request.setPageToken(files.getNextPageToken());
//        }
//
//        for (int index = 1; index < dirList.length; index++) {
//            String folderId = fileInfo.get(0).split("__")[1];
//            fileInfo.clear();
//            FileList filesSub = request.setQ("'" + folderId + "' in parents and trashed=false").execute();
//            List<com.google.api.services.drive.model.File> filesListSub = filesSub.getFiles();
//            if (filesSub != null) {
//                for (com.google.api.services.drive.model.File file : filesListSub) {
//                    if (file.getName().trim().equalsIgnoreCase(dirList[index].replace("/", "").trim())) {
//                        fileInfo.add(String.format("%s__%s", file.getName(), file.getId()));
//                    }
//                }
//            }
//        }
//
//        String folderId = fileInfo.get(0).split("__")[1];
//        fileInfo.clear();
//        FileList filesSub = request.setQ("'" + folderId + "' in parents and trashed=false").execute();
//        List<com.google.api.services.drive.model.File> filesListSub = filesSub.getFiles();
//        if (filesSub != null) {
//            for (com.google.api.services.drive.model.File file : filesListSub) {
//                fileInfo.add(String.format("%s__%s", file.getName(), file.getId()));
//            }
//        }
//
//        return fileInfo;
//    }
//
//    public static List<String> getGoogleDriveOnlyFolderList(Drive mService, String fileNetworkId,
//                                                            String pathList[]) throws IOException {
//        String[] dirList = removeBlankCellInArray(pathList);
//        List<String> fileInfo = new ArrayList<String>();
//        Drive.Files.List request = null;
//
//        if (dirList.length >= 0) {
//            request = mService.files().list();
//            FileList files = request.setQ("'" + fileNetworkId.trim() + "' in parents and trashed=false").execute();
//            List<com.google.api.services.drive.model.File> filesList = files.getFiles();
//            if (files != null) {
//                for (com.google.api.services.drive.model.File file : filesList) {
//                    if (file.getName().trim().equalsIgnoreCase(dirList[0].replace("/", "").trim()))
//                        fileInfo.add(String.format("%s__%s", file.getName(), file.getId()));
//                }
//            }
//            request.setPageToken(files.getNextPageToken());
//        }
//
//        for (int index = 1; index < dirList.length; index++) {
//            String folderId = fileInfo.get(0).split("__")[1];
//            fileInfo.clear();
//            FileList filesSub = request.setQ("'" + folderId + "' in parents and trashed=false").execute();
//            List<com.google.api.services.drive.model.File> filesListSub = filesSub.getFiles();
//            if (filesSub != null) {
//                for (com.google.api.services.drive.model.File file : filesListSub) {
//                    if (file.getName().trim().equalsIgnoreCase(dirList[index].replace("/", "").trim())) {
//                        fileInfo.add(String.format("%s__%s", file.getName(), file.getId()));
//                    }
//                }
//            }
//        }
//
//        String folderId = fileInfo.get(0).split("__")[1];
//        fileInfo.clear();
//        FileList filesSub = request.setQ("'" + folderId + "' in parents and trashed=false").execute();
//        List<com.google.api.services.drive.model.File> filesListSub = filesSub.getFiles();
//        if (filesSub != null) {
//            for (com.google.api.services.drive.model.File file : filesListSub) {
//                if (file.getMimeType().equals("application/vnd.google-apps.folder"))
//                    fileInfo.add(String.format("%s__%s", file.getName(), file.getId()));
//            }
//        }
//
//        return fileInfo;
//    }
//
//    public static ArrayList<String> getGoogleDriveOnlyPDFFIleList(Drive mService, String fileNetworkId,
//                                                                  String pathList[]) throws IOException {
//        String[] dirList = removeBlankCellInArray(pathList);
//        List<String> fileInfo = new ArrayList<String>();
//        Drive.Files.List request = null;
//
//        if (dirList.length >= 0) {
//            request = mService.files().list();
//            FileList files = request.setQ("'" + fileNetworkId.trim() + "' in parents and trashed=false").execute();
//            List<com.google.api.services.drive.model.File> filesList = files.getFiles();
//            if (files != null) {
//                for (com.google.api.services.drive.model.File file : filesList) {
//                    if (file.getName().trim().equalsIgnoreCase(dirList[0].replace("/", "").trim()))
//                        fileInfo.add(String.format("%s__%s", file.getName(), file.getId()));
//                }
//            }
//            request.setPageToken(files.getNextPageToken());
//        }
//
//
//        for (int index = 1; index < dirList.length; index++) {
//            String folderId = fileInfo.get(0).split("__")[1];
//            fileInfo.clear();
//            FileList filesSub = request.setQ("'" + folderId + "' in parents and trashed=false").execute();
//            List<com.google.api.services.drive.model.File> filesListSub = filesSub.getFiles();
//            if (filesSub != null) {
//                for (com.google.api.services.drive.model.File file : filesListSub) {
//                    if (file.getName().trim().equalsIgnoreCase(dirList[index].replace("/", "").trim())) {
//                        fileInfo.add(String.format("%s__%s", file.getName(), file.getId()));
//                    }
//                }
//            }
//        }
//
//        String folderId = fileInfo.get(0).split("__")[1];
//        fileInfo.clear();
//        FileList filesSub = request.setQ("'" + folderId + "' in parents and trashed=false").execute();
//        List<com.google.api.services.drive.model.File> filesListSub = filesSub.getFiles();
//        if (filesSub != null) {
//            for (com.google.api.services.drive.model.File file : filesListSub) {
//                if (file.getMimeType().equals("application/pdf")) {
//                    fileInfo.add(String.format("%s__%s", file.getName(), file.getId()));
//                }
//            }
//        }
//
//        return (ArrayList<String>) fileInfo;
//    }
//
//    public static boolean login(Drive mService, String fileNetworkId, String googleDrivePath) {
//        Drive.Files.List request = null;
//        boolean isResult = false;
//        try {
//            String fileId = getGoogleDriveFolderId(mService, fileNetworkId, googleDrivePath.split("/"));
//            request = mService.files().list();
//            String query = "'" + fileId + "' in parents and trashed=false";
//            FileList files = request.setQ(query).execute();
//            List<com.google.api.services.drive.model.File> filesList = files.getFiles();
//            if (files != null) {
//                if (filesList.size() > 0) {
//                    isResult = true;
//                }
//            }
//            return isResult;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return isResult;
//        }
//    }
//
//    public static String mkdir(Drive mService, String rootFolderId, String ftpProcessedDate) throws IOException {
//
//        com.google.api.services.drive.model.File fileMetadata = new com.google.api.services.drive.model.File();
//        fileMetadata.setName(ftpProcessedDate);
//        fileMetadata.setMimeType("application/vnd.google-apps.folder");
//        fileMetadata.setParents(Collections.singletonList(rootFolderId));
//        com.google.api.services.drive.model.File file = mService.files().create(fileMetadata).setFields("id").execute();
//
//        return file.getId();
//    }
//
//    public static boolean deleteFIle(Drive mService, String fileId) throws IOException {
//        mService.files().delete(fileId).execute();
//        return true;
//    }
//
//}
