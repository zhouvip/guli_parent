package com.nanjing.eduservice.controller;

import com.nanjing.eduservice.client.VodClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yimo
 * @version 1.0
 * @date 2022/4/12 15:18
 */
public class EduVideoController {

    //注入vodClient
    @Autowired
    private VodClient vodClient;

    /*@ApiOperation(value = "删除小节")
    @DeleteMapping("deleteVideo/{id}")
    public R deleteVideo(@PathVariable String id){

        //根据小节id获取视频id，调用方法实现视频删除
        EduVideo eduVideo = videoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        //判断小节里面是否有视频id
        if(!StringUtils.isEmpty(videoSourceId)) {
            //根据视频id，远程调用实现视频删除
            vodClient.removeAliyVideo(videoSourceId);
        }
        //删除小节
        videoService.removeById(id);
        return R.ok();
    }*/

}
