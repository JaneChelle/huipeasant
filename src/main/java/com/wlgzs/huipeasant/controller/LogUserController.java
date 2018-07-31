package com.wlgzs.huipeasant.controller;

import com.wlgzs.huipeasant.base.BaseController;
import com.wlgzs.huipeasant.entity.User;
import com.wlgzs.huipeasant.util.Result;
import com.wlgzs.huipeasant.util.ResultCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author:胡亚星
 * @createTime 2018-07-11 9:51
 * @description: 登录注册控制层
 **/
@Controller
@ResponseBody
@RequestMapping("LogUserController")
public class LogUserController extends BaseController {
    //去注册
    @RequestMapping("/toRegistered")
    public ModelAndView toRegister() {
        return new ModelAndView("register") ;
    }
    //2.去注册
    @RequestMapping("/toipRegistered")
    public ModelAndView toipRegister() {
        return new ModelAndView("ipregister") ;
    }

    //ip去注册
    @RequestMapping("/toIpRegistered")
    public ModelAndView toIpRegistered() {
        return new ModelAndView("phone/ipregister") ;
    }

    //去登陆
    @RequestMapping("/toLogin")
    public ModelAndView toLogin() {
        return new ModelAndView("login");
    }
    //手机端
    @RequestMapping("/toipLogin")
    public ModelAndView toipLogin() {
        return new ModelAndView("iplogin");
    }

    //ip去登陆
    @RequestMapping("/toIpLogin")
    public ModelAndView toIpLogin() {
        return new ModelAndView("phone/iplogin");
    }

    //用户注册
    @RequestMapping("registered")
    public ModelAndView register(Model model, HttpServletRequest request){
        String mag = logUserService.register(request);
        model.addAttribute("mag",mag);
        return new ModelAndView("register-1");
    }
    //用户ip注册
    @RequestMapping("ipRegistered")
    public ModelAndView ipRegistered(Model model, HttpServletRequest request){
        String mag = logUserService.register(request);
        model.addAttribute("mag",mag);
        return new ModelAndView("phone/iplogin");
    }

    //用户登录
    @RequestMapping("login")
    public ModelAndView login(HttpServletRequest request, Model model, String phoneNumber, String password){
        String mag = logUserService.login(request,phoneNumber,password);
        System.out.println(phoneNumber+""+password);
        System.out.println(mag);
        model.addAttribute("mag",mag);
        if(mag.equals("管理员登录成功！")){
            return new ModelAndView("admin/adminIndex");
        }else if(mag.equals("登录成功！")){
            return new ModelAndView("redirect:/user/toindex");
        }else{
            return new ModelAndView("login");
        }
    }

    //用户登录
    @RequestMapping("Iplogin")
    public ModelAndView Iplogin(HttpServletRequest request, Model model, String phoneNumber, String password){
        String mag = logUserService.login(request,phoneNumber,password);
        System.out.println(phoneNumber+""+password);
        System.out.println(mag);
        model.addAttribute("mag",mag);
        if(mag.equals("登录成功！")){
            return new ModelAndView("redirect:/user/toipindex");
        }else{
            return new ModelAndView("phone/iplogin");
        }
    }

    //用户退出
    @RequestMapping("cancellation")
    public ModelAndView cancellation(HttpServletRequest request) {
        logUserService.cancellation(request);
        return new ModelAndView("redirect:/LogUserController/toLogin");
    }

    //管理员退出
    @RequestMapping("adminCancellation")
    public ModelAndView adminCancellation(HttpServletRequest request){
        logUserService.adminCancellation(request);
        return new ModelAndView("redirect:/LogUserController/toLogin");
    }

    //验证手机号是否存在
    @RequestMapping("validationPhone")
    public Result validationPhone(String phoneNumber, HttpServletRequest request, Model model){
        if(logUserService.validationPhone(phoneNumber)){
            return new Result(ResultCode.SUCCESS, "手机号可用!");
        }else{
            return new Result(ResultCode.FAIL,"手机号已被注册!");
        }
    }
}
