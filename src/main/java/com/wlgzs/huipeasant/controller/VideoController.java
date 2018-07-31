package com.wlgzs.huipeasant.controller;

import com.wlgzs.huipeasant.base.BaseController;
import com.wlgzs.huipeasant.entity.Video;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author:胡亚星
 * @createTime 2018-07-11 22:30
 * @description:
 **/
@Controller
@RequestMapping("VideoController")
public class VideoController extends BaseController {

    //按ID查询视频（视频详情）
    @RequestMapping("/videoDetails")
    public ModelAndView detailsVideo(long videoId,Model model){
        Video video = videoService.detailsVideo(videoId);
        model.addAttribute("video",video);
        return new ModelAndView("train");
    }

    //全部视频
    @RequestMapping("allVideoList")
    public ModelAndView allVideoList(Model model){
        List<Video> videoList = videoService.findAllVideo();
        model.addAttribute("videoList",videoList);
        return new ModelAndView("VideoList");
    }

    //更多
    @RequestMapping("videoMore")
    public ModelAndView videoMore(long moduleId,Model model){
        List<Video> videoList = videoService.videoList(moduleId);
        model.addAttribute("videoList",videoList);
        return new ModelAndView("videomore");
    }

    //按点击量查询视频
    @RequestMapping("videoRanking")
    public ModelAndView videoRanking(Model model){
        List<Video> videoList = videoService.videoRanking();
        model.addAttribute("videoList",videoList);
        return new ModelAndView("VideoList");
    }

    //视频播放页
    @RequestMapping("toPcvideo")
    public ModelAndView toPcvideo(Model model,String videoAddress){
        model.addAttribute("videoAddress",videoAddress);
        return new ModelAndView("pcvideo");
    }

}
