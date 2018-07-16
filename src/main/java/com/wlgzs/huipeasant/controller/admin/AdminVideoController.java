package com.wlgzs.huipeasant.controller.admin;

import com.wlgzs.huipeasant.base.BaseController;
import com.wlgzs.huipeasant.entity.Video;
import com.wlgzs.huipeasant.service.VideoService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author:胡亚星
 * @createTime 2018-07-11 22:33
 * @description: 后台对视频的操作
 **/
@Controller
@RequestMapping("AdminVideoController")
public class AdminVideoController extends BaseController {

    //遍历视频
    @RequestMapping("/adminVideoList")
    public ModelAndView adminVideoList(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                                       @RequestParam(value = "limit", defaultValue = "8") int limit){
        String videoKeyWord = "";
        if (page != 0) page--;
        Page pages = videoService.getVideoListPage(videoKeyWord,page,limit);
        model.addAttribute("TotalPages", pages.getTotalPages());//查询的页数
        model.addAttribute("Number", pages.getNumber() + 1);//查询的当前第几页
        List<Video> adminVideoList = pages.getContent();
        model.addAttribute("adminVideoList",adminVideoList);
        return new ModelAndView("admin/adminVideoList");
    }

    //搜索视频
    @RequestMapping("/adminFindProduct")
    public ModelAndView findProduct(Model model, String videoKeyWord, @RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "limit", defaultValue = "8") int limit) {
        if (page != 0) page--;
        Page pages = videoService.getVideoListPage(videoKeyWord,page,limit);
        model.addAttribute("TotalPages", pages.getTotalPages());//查询的页数
        model.addAttribute("Number", pages.getNumber() + 1);//查询的当前第几页
        List<Video> adminVideoList = pages.getContent();
        model.addAttribute("adminVideoList", adminVideoList);//查询的当前页的集合
        model.addAttribute("videoKeyWord", videoKeyWord);
        return new ModelAndView("admin/adminVideoList");
    }

    //跳转到添加视频
    @RequestMapping("/toAddVideo")
    public ModelAndView toAddVideo(){
        return new ModelAndView("admin/addVideo");
    }

    //添加视频
    @RequestMapping("/addVideo")
    public ModelAndView  addVideo(@RequestParam("file") MultipartFile[] myFileNames,Model model,
                                  HttpSession session,HttpServletRequest request){
        //添加视频
        videoService.saveVideo(myFileNames,session,request);
        return new ModelAndView("redirect:/adminVideoList");
    }

    //按ID删除视频
    @RequestMapping("/adminDeleteVideo")
    public ModelAndView delete(Model model,long videoId ,HttpServletRequest request){
        videoService.delete(videoId,request);
        return new ModelAndView("redirect:/adminVideoList");
    }

    //修改视频
    @RequestMapping("/adminEditProduct")
    public ModelAndView edit(){


    }



}
