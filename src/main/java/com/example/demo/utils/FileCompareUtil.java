package com.example.demo.utils;

import com.example.demo.common.CommonUtil;
import com.example.demo.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipOutputStream;

/**
 * Package： com.example.demo.utils
 * Author:  hujin
 * Date: 2019/8/27 9:21
 * Description:
 * Version：
 */
@Slf4j
@Component
public class FileCompareUtil {

    @Autowired
    UploadService uploadService;
    /**
     * 读取word文件内容
     *
     * @param file
     * @return buffer
     */

    private static String readWord(File file) throws Exception {
        String buffer = "";
        if (file.getName().endsWith(".doc")) {
            InputStream is = new FileInputStream(file);
            WordExtractor ex = new WordExtractor(is);
            buffer = ex.getText();
            ex.close();
        } else if (file.getName().endsWith("docx")) {
            OPCPackage opcPackage = POIXMLDocument.openPackage(file.getPath());
            POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
            buffer = extractor.getText();
            extractor.close();
        }
        return buffer;
    }

    /**
     * 去除文件中多余空格换行符
     * @param str
     * @return
     */
    private static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public String fileCompare(List<MultipartFile> files) throws Exception {
        List<Map<String,String>> contentList = new ArrayList<Map<String,String>>();
        String zipName = "";
        for(MultipartFile file : files){
            if(!CommonUtil.isEmpty(file)) {
                // 存储文件
                String path = "D:\\logs\\data\\" + file.getOriginalFilename();
                File savePathDir = new File(path);
                if(!savePathDir.exists()){
                    savePathDir.mkdirs();
                }
                File fileData = new File(path);
                file.transferTo(fileData);
                // 文件处理
                Map<String,String> map = new HashMap<String,String>();
                map.put("name",file.getOriginalFilename());
                if(zipName.equals(""))zipName=file.getOriginalFilename();
                map.put("file",FileCompareUtil.readWord(fileData));
                contentList.add(map);
            }
        }
        int size= contentList.size();
        List<File> fileList = new ArrayList<File>();
        for (int i = 0;i<size;i++) {
            for (int j = i+1;j<size;j++){
                String result = "";
                int flag =0;
                int lengthi = contentList.get(i).get("file").length();
                for(int m =10;m<lengthi;m++){
                    String key = contentList.get(i).get("file").substring(m-10, m);
                    if(contentList.get(j).get("file").indexOf(key)>-1){
                        if(m>10&&flag ==m-1){
                            result+=key.substring(9);
                        }else if(m==10){
                            result = key;
                        }
                        else{
                            result+=key+"*******\n";
                        }
                        flag = m;
                    }
                }
                String 	savePath = "D:\\logs\\result\\"+contentList.get(i).get("name")+"---"+contentList.get(j).get("name")+"  result.txt";
                String path=savePath;
                File file = new File(savePath);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                int k=0;
                while(file.exists()){
                    k++;
                    path =savePath.substring(0, savePath.indexOf("."))+"("+k+").txt";
                    file = new File(path);
                }
                FileWriter  writer = new FileWriter (path);
                writer.write(result);
                writer.flush();
                writer.close();
                fileList.add(file);
            }
        }
        String outPath =  "D:\\logs\\zip\\"+ zipName+".zip";
        File fileDirs = new File("\\logs\\zip");
        if(!fileDirs.exists()){
            fileDirs.mkdirs();
        }
        File fileDir = new File(outPath);
        if(!fileDir.exists()){
            fileDir.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(
                outPath);
        ZipOutputStream zipOut = new ZipOutputStream(fos);

        ZipUtil.zipFile(fileList,zipName, zipOut);
        zipOut.close();
        fos.close();
        uploadService.upLoadZip(fileDir);
        return outPath;
    }
}
