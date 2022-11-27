package com.nanjing.edu.client;

import com.nanjing.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author yimo
 * @version 1.0
 * @date 2022/4/12 14:27
 */
@FeignClient("service-vod")
@Component
public interface VodClient {

    /**
     * 定义调用的方法路径
     * 根据视频id删除阿里云视频
     * @PathVariable 注解一定要指定参数名称，否则会报错
     * @param id
     * @return
     */
    @DeleteMapping("/eduvod/video/removeAliyVideo/{id}")//注意：这里的访问路径是全路径
    public R removeAliyVideo(@PathVariable("id") String id);

}
