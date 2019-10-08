package com.example.demo.controller;

import com.example.demo.dto.ServerResponse;
import com.example.demo.utils.FileCompareUtil;
import com.example.demo.utils.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Package： com.example.demo.controller
 * Author:  hujin
 * Date: 2019/8/26 19:43
 * Description:
 * Version：
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class UploadController {


    @Autowired
    FileCompareUtil fileCompareUtil;
//    @RequestMapping("/upload")
//    @ResponseBody
//    public ServerResponse upload(@RequestParam MultipartFile file,
//                                 @RequestParam(required = false) String id,
//                                 @RequestParam(required = false) String ftpPath,
//                                 @RequestParam(required = false, defaultValue = "true") Boolean needConvert) throws IOException {
    @PostMapping("/upload")
    public  ServerResponse<String> upload (HttpServletRequest request) {
        try {
            StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
            MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
            List<MultipartFile> files = multipartRequest
                    .getFiles("file");
            log.info("对比文件数量: " + files.size());
            if(files.size()>1){
                return new ServerResponse<>(200,"success",fileCompareUtil.fileCompare(files));
            }else {
                return new ServerResponse<>(-1,"一个文件无法对比",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ServerResponse<>(-1,"服务器异常",null);
        }
    }
}
