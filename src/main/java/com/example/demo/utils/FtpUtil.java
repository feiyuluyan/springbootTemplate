package com.example.demo.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.io.InputStream;

/**
 * Package： com.example.demo.utils
 * Author:  hujin
 * Date: 2019/8/26 20:42
 * Description:
 * Version：
 */
public class FtpUtil {

    // ftp服务器ip地址
    private static String FTP_ADDRESS;
    // 端口号
    private static int FTP_PORT;
    // 用户名
    private static String FTP_USERNAME;
    // 密码
    private static String FTP_PASSWORD;
    // 图片路径
    private static String FTP_BASEPATH;
    // http路径
    private static String FTP_HTTPPATH;

    private static String uploadFile(String originFileName, String filePath, InputStream input) {
        String result = "";
        FTPClient ftp = new FTPClient();
        ftp.setControlEncoding("UTF-8");
        String path = FTP_BASEPATH + filePath + "/" + DateUtil.date().substring(0, 4) + "/" +
                DateUtil.date().substring(5, 7) + "/";

//        String path = FTP_BASEPATH;
        try {
            int reply;
            ftp.connect(FTP_ADDRESS, FTP_PORT);// 连接FTP服务器
            ftp.login(FTP_USERNAME, FTP_PASSWORD);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return result;
            }
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            for (String single: path.split("/")) {
                if (!single.equals("")) {
                    ftp.makeDirectory(single);
                    ftp.changeWorkingDirectory(single);
                }
            }
            ftp.storeFile(originFileName, input);
            input.close();
            ftp.logout();
            result = FTP_HTTPPATH + path + originFileName;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }

    public static String uploadFile(String name, String filePath, InputStream inputStream, String ftpAddress, int ftpPort,
                                    String ftpName, String ftpPassWord, String ftpBasePath, String httpPath) {
        FTP_ADDRESS = ftpAddress;
        FTP_PORT = ftpPort;
        FTP_USERNAME = ftpName;
        FTP_PASSWORD = ftpPassWord;
        FTP_BASEPATH = ftpBasePath;
        FTP_HTTPPATH = httpPath;
        return uploadFile(name, filePath,inputStream);
    }

}
