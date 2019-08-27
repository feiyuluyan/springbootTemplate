package com.example.demo.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Package： com.example.demo.vo
 * Author:  hujin
 * Date: 2019/8/26 19:47
 * Description:
 * Version：
 */
@Data
public class UploadFileVo {

    // 文件列表
   private MultipartFile[] fileList;

    // ftp 服务器路径
    private String ftpPath;
}
