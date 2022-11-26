package com.nanjing.oss.controller;

import com.nanjing.juc.commonutils.R;
import com.nanjing.oss.service.OssService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author yimo
 * @version 1.0
 * @date 2022/3/26 16:51
 */
@RestController
@RequestMapping("/oss/fileoss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    @ApiOperation(value = "文件上传")
    @PostMapping("/upload")
    public R uploadOssFile(@ApiParam(name = "file", value = "文件", required = true)
                           @RequestParam("file") MultipartFile file){
        //返回上传到oss的路径
        String url = ossService.uploadFileAvator(file);
        return R.ok().data("url",url);
    }

}
