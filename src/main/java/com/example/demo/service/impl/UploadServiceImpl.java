package com.example.demo.service.impl;

import com.example.demo.service.UploadService;
import com.example.demo.utils.DateUtil;
import com.example.demo.utils.FtpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Package： com.example.demo.service.impl
 * Author:  hujin
 * Date: 2019/8/26 20:04
 * Description:
 * Version：
 */
@Service
public class UploadServiceImpl implements UploadService {

    @Value("${ftp.ip}")
    private String ip;

    @Value("${ftp.port}")
    private int port;

    @Value("${ftp.username}")
    private String username;

    @Value("${ftp.password}")
    private String password;

    @Value("${ftp.path}")
    private String path;

    @Value("${ftp.httpPath}")
    private String httpPath;

    @Value("${storage.absolutePath}")
    private String localBasePath;

    @Value("${storage.httpPath}")
    private String storageHttpPath;

    @Value("${switch.useFtp}")
    private Integer useFtp;

    public String upload(String fileName, String filePath, MultipartFile multipartFile) throws IOException {
        if (useFtp != 1) {
            return uploadLocal(fileName, filePath, multipartFile);
        }
        return FtpUtil.uploadFile(
                fileName,
                filePath,
                multipartFile.getInputStream(),
                ip,
                port,
                username,
                password,
                path,
                httpPath
        );
    }

    @Override
    public String uploadLocal(String fileName, String filePath, MultipartFile multipartFile) {
        String relativePath = filePath + "/" + DateUtil.now().substring(0,4) + "/" + DateUtil.now().substring(5,7) +
                "/" + fileName;
        String localPath = localBasePath + relativePath;
        try {
            File file = new File(localPath);
            file.getParentFile().mkdirs();
            file.createNewFile();
            multipartFile.transferTo(file);
            return storageHttpPath + relativePath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String upLoadZip(File file) throws FileNotFoundException {
        InputStream is= new FileInputStream(file);
        return FtpUtil.uploadFile(
                file.getName(),
                file.getPath(),
                is,
                ip,
                port,
                username,
                password,
                path,
                httpPath
        );
    }
}
