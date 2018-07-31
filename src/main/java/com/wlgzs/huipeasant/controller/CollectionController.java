package com.wlgzs.huipeasant.controller;

import com.wlgzs.huipeasant.base.BaseController;
import com.wlgzs.huipeasant.entity.Collection;
import com.wlgzs.huipeasant.entity.User;
import com.wlgzs.huipeasant.service.CollectionService;
import org.apache.catalina.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author:胡亚星
 * @createTime 2018-07-18 17:16
 * @description:
 **/
@Controller
@RequestMapping("CollectionController")
public class CollectionController extends BaseController {

    //添加收藏
    @RequestMapping("/addCollectionVideo")
    public ModelAndView addCollectionVideo(HttpSession session,long videoId){
        User user = (User)session.getAttribute("user");
        long userId = user.getUserId();
        if(!collectionService.isCollection(videoId,session)){
            collectionService.collectionVideo(userId,videoId);
        }
        return new ModelAndView("redirect:/VideoController/videoDetails?videoId="+videoId);
    }

    //删除收藏
    @RequestMapping("/deleteCollection")
    public ModelAndView deleteCollection(long videoId,HttpSession session){
        collectionService.deleteCollection(videoId,session);
        return new ModelAndView("redirect:/VideoController/videoDetails?videoId="+videoId);
    }

    //查看收藏
    @RequestMapping("/Viewcollection")
    public ModelAndView toCollection( Model model){
        User user = (User)session.getAttribute("user");
        long userId = user.getUserId();
        List<Collection> collections = collectionService.toCollection(userId);
        model.addAttribute("collections",collections);
        return new ModelAndView("mycollection");
    }

    //查看收藏(手机)
    @RequestMapping("/ViewIpcollection")
    public ModelAndView ViewIpcollection( Model model){
        User user = (User)session.getAttribute("user");
        long userId = user.getUserId();
        List<Collection> collections = collectionService.toCollection(userId);
        model.addAttribute("collections",collections);
        return new ModelAndView("phone/ipcollection");
    }

    //添加收藏
    @RequestMapping("/addIpCollectionVideo")
    public ModelAndView addIpCollectionVideo(long videoId,HttpSession session){
        User user = (User)session.getAttribute("user");
        long userId = user.getUserId();
        collectionService.collectionVideo(userId,videoId);
        return new ModelAndView("");
    }

}
