package com.example.demo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Package： com.example.demo.utils
 * Author:  hujin
 * Date: 2019/8/27 12:02
 * Description: 将文件打包压缩为zip文件
 * Version：
 */
public class ZipUtil {
    /**
     * 文件压缩
     * @param children
     * @param fileName
     * @param zipOut
     * @throws IOException
     */
    public static void zipFile(List<File> children, String fileName, ZipOutputStream zipOut) throws IOException {
        for (File fileToZip : children) {
            FileInputStream fis = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            fis.close();
        }
    }
}
