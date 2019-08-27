package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Package： com.example.demo.service
 * Author:  hujin
 * Date: 2019/8/26 20:04
 * Description:
 * Version：
 */
public interface UploadService {

    String upload(String fileName, String filePath, MultipartFile multipartFile) throws IOException;

    String uploadLocal(String fileName, String filePath, MultipartFile multipartFile);

    String upLoadZip(File file) throws FileNotFoundException;
}
