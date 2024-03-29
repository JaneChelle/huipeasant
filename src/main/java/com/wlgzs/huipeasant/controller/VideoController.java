package com.wlgzs.huipeasant.controller;

import com.wlgzs.huipeasant.base.BaseController;
import com.wlgzs.huipeasant.entity.Video;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public ModelAndView detailsVideo(long videoId, Model model, HttpSession session){
        Video video = videoService.detailsVideo(videoId);
        //判断视频是否被收藏
        if(collectionService.isCollection(videoId,session)){//被收藏 1
            video.setCode(1);
        }else{
            video.setCode(0);
        }
        model.addAttribute("video",video);
        return new ModelAndView("train");
    }
    //按ID查询视频（视频详情）
    @RequestMapping("/videoIpDetails")
    public ModelAndView detailsIpVideo(long videoId, Model model, HttpSession session){
        Video video = videoService.detailsVideo(videoId);
        //判断视频是否被收藏
        if(collectionService.isCollection(videoId,session)){//被收藏 1
            video.setCode(1);
        }else{
            video.setCode(0);
        }
        model.addAttribute("video",video);
        return new ModelAndView("phone/ipDvideo");
    }

    //全部视频
    @RequestMapping("allVideoList")
    public ModelAndView allVideoList(Model model){
        List<Video> videoList = videoService.findAllVideo();
        model.addAttribute("videoList",videoList);
        return new ModelAndView("VideoList");
    }
    //全部视频 （手机）
    @RequestMapping("allIpVideoList")
    public ModelAndView allipVideoList(Model model){
        List<Video> videoList = videoService.findAllVideo();
        model.addAttribute("videoList",videoList);
        return new ModelAndView("/phone/VideoList");
    }

    //更多 pc
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

    //2.按点击量查询视频
    @RequestMapping("videoIpRanking")
    public ModelAndView videoIpRanking(Model model){
        List<Video> videoList = videoService.videoRanking();
        for(int i = 0;i < videoList.size();i++){
            videoList.get(i).setCode(i+1);
        }
        model.addAttribute("videoList",videoList);
        return new ModelAndView("/phone/Video-rankings");
    }

    //视频播放页
    @RequestMapping("toPcvideo")
    public ModelAndView toPcvideo(Model model,String videoAddress){
        model.addAttribute("videoAddress",videoAddress);
        return new ModelAndView("pcvideo");
    }
    @RequestMapping("toIpPcvideo")
    public ModelAndView toIpPcvideo(Model model,String videoAddress){
        System.out.println(videoAddress);
        model.addAttribute("videoAddress",videoAddress);
        return new ModelAndView("/phone/video");
    }
    //病虫图害
    @RequestMapping("insect")
    public ModelAndView insect(){
        return new ModelAndView("/phone/insect");
    }
}
