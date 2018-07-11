package com.wlgzs.huipeasant.controller;

import com.wlgzs.huipeasant.base.BaseController;
import com.wlgzs.huipeasant.entity.User;
import com.wlgzs.huipeasant.service.LogUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author:胡亚星
 * @createTime 2018-07-11 9:51
 * @description: 登录注册控制层
 **/
@Controller
@RequestMapping("LogUserController")
public class LogUserController extends BaseController {
    //去注册
    @RequestMapping("/toRegistered")
    public String toRegister() {
        return "registered";
    }

    //去登陆
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    //用户注册
    @RequestMapping("registered")
    public String register(Model model, HttpServletRequest request){
        String mag = logUserService.register(request);
        model.addAttribute("mag",mag);
        return "login";
    }

    //用户登录
    @RequestMapping("login")
    public String login(HttpServletRequest request, Model model, String phoneNumber, String password){
        String mag = logUserService.login(request,phoneNumber,password);
        model.addAttribute("mag",mag);
        if(mag.equals("管理员登录成功！")){
            return "adminIndex";
        }else if(mag.equals("登录成功！")){
            return "Index";
        }else{
            return "login";
        }
    }

    //用户退出
    @RequestMapping("cancellation")
    public String cancellation(HttpServletRequest request) {
        logUserService.cancellation(request);
        return "redirect:/toLogin";
    }

    //管理员退出
    @RequestMapping("adminCancellation")
    public String adminCancellation(HttpServletRequest request){
        logUserService.adminCancellation(request);
        return "redirect:/toLogin";
    }

}
