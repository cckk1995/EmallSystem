package com.emall.controller;

import com.emall.response.CommonReturnType;
import com.emall.utils.FtpUtil;
import jdk.internal.util.xml.impl.Input;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 *  Created by cckk1995 on 2019/3/14
 */
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@Controller
@RequestMapping("/file")
public class FileUploadController {

    @Value("${FTP_ADDRESS}")
    private String host;

    @Value("${FTP_USERNAME}")
    private String username;

    @Value("${FTP_PASSWORD}")
    private String password;

    @Value("${FTP_BASE_PATH}")
    private String basePath;

    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;

    @RequestMapping(value="/uploading",method = RequestMethod.POST)
    public @ResponseBody CommonReturnType uploadImg(@RequestParam(value = "upfile",required = true) MultipartFile file){
        InputStream is = null;
        String fileName = file.getOriginalFilename();
        try{
            is = new ByteArrayInputStream(file.getBytes());
            FtpUtil.uploadFile(host,username,password,basePath+"/"+fileName,is);
        }catch (Exception e){
            e.printStackTrace();
            return CommonReturnType.create("上传文件失败","false");
        }
        return CommonReturnType.create(IMAGE_BASE_URL+"/"+fileName);
    }
}
