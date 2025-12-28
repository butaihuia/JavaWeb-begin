package com.awake.controller;

import com.awake.pojo.Result;
import com.awake.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    /*
     * 本地磁盘存储
     * */
    /*
    @PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
        log.info("接受到的参数:{},{},{}", name, age, file);
        //获取原始文件名
        String originalFilename = file.getOriginalFilename();
        //新的文件名
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + extension;

        //保存文件
        file.transferTo(new File("D:/JavaWebLog/images/" + newFileName));
        return Result.success();
    }
    */

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("接受到的参数:{}", file.getOriginalFilename());
        //文件交给阿里云OSS管理
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("文件上传Oss,url:{}", url);
        return Result.success(url);

    }
}
