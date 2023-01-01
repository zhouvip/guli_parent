package com.nanjing.ossservice.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author yimo
 * @version 1.0
 * @date 2022/3/26 16:48
 */
public interface OssService {
    String uploadFileAvator(MultipartFile file);
}
