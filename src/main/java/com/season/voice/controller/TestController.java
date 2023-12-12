package com.season.voice.controller;


import com.season.voice.dto.DataResult;
import com.season.voice.dto.R;
import com.season.voice.service.AliyunOssService;
import com.season.voice.util.QRCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequestMapping("/voice")
@Api(tags = "测试api")
public class TestController {
    @Resource
    private AliyunOssService aliyunOssService;


    @PostMapping("/uploadPicture")
    @ApiOperation(value = "上传图片")
    public DataResult uploadPicture(@RequestParam("file") MultipartFile file) {
        String ossUrl;
        try {
            System.out.println(file.getBytes().length/1024/1024);
            String md5 = DigestUtils.md5Hex(file.getBytes());
            ossUrl = aliyunOssService.uploadByInputStream(file.getInputStream(), "chendabucket", md5);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return R.success(ossUrl);
    }

    @GetMapping(value = "/getQRCode")
    public void getQRCode(HttpServletResponse response, @RequestParam("content") String content) throws Exception {
        ServletOutputStream stream = null;
        ClassPathResource resource = new ClassPathResource("img/logo.jpg");
        String logoUrl = null;
        if(resource.exists()){
            logoUrl = "file:///" +resource.getFile().getPath();
        }
        try {
            stream = response.getOutputStream();
            QRCodeUtil.getQRCode(content, logoUrl, stream);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }
}
