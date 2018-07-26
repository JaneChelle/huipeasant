package com.wlgzs.huipeasant.controller;

import com.wlgzs.huipeasant.base.BaseController;
import com.wlgzs.huipeasant.entity.User;
import com.wlgzs.huipeasant.util.CheckImage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author:胡亚星
 * @createTime 2018-07-12 8:03
 * @description:
 **/
@Controller
@RequestMapping("UserManagementController")
public class UserManagementController extends BaseController {

    //展示用户信息
    @RequestMapping("information")
    public ModelAndView displayInformation(Model model, Long userId) {
        User user = userService.findUserById(userId);
        model.addAttribute("user", user);
        return new ModelAndView("information");
    }

    //修改昵称
    @RequestMapping("changeInformation")
    public ModelAndView ModifyName(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        userService.ModifyName(request,user);
        model.addAttribute("user", user);
        return new ModelAndView("information");
    }

    //跳转到修改手机号
    @RequestMapping("toChangePhone")
    public ModelAndView toChangePhone(){ ;
        return new ModelAndView("changePhone");
    }

    //修改手机号//需要phoneNumber(新的)
    @RequestMapping("changePhone")
    public ModelAndView changePhone(Model model, HttpServletRequest request) {
        //修改手机号
        User user = userService.changePhone(request);
        model.addAttribute("user", user);
        return new ModelAndView("information");
    }

    //修改头像
    @RequestMapping("/ModifyAvatar")
    public ModelAndView add(@RequestParam("file") MultipartFile myFileName, HttpSession session,
                            Model model, HttpServletRequest request) throws IOException {
        String fileName = myFileName.getOriginalFilename();
        CheckImage checkImage = new CheckImage();
        User user = null;
        if(checkImage.verifyImage(fileName)){
            user = userService.ModifyAvatar(session,request,myFileName);
        }else{
            user = (User) session.getAttribute("user");
            model.addAttribute("mag","文件格式不正确");
        }
        model.addAttribute("user", user);
        return new ModelAndView("information");
    }

    //跳转到修改密码
    @RequestMapping("toChangePassword")
    public ModelAndView toChangePassword(){
        return new ModelAndView("changePassword");
    }

    //修改密码
    @RequestMapping("checkPassword")
    public ModelAndView checkPassword(Model model, HttpServletRequest request) {
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        long userId = user.getUserId();
        if (userService.checkPassWord(password, userId)) {//正确ze修改密码
            System.out.println("正确");
            model.addAttribute("mag", "修改成功");
            userService.changePassword(rePassword, userId);
            return new ModelAndView("redirect:/toLogin");
        } else {//原密码错误
            model.addAttribute("userId",userId);
            model.addAttribute("mag", "原密码错误");
            return new ModelAndView("redirect:/toChangePassword");
        }
    }

    //跳转到找回密码页面
    @RequestMapping("toRetrievePassword")
    public ModelAndView toSendRetrievePassword() {
        return new ModelAndView("RetrievePassword");
    }

    //找回密码（输入手机号和预留信息）
    @RequestMapping("validationIfo")
    public ModelAndView validationIfo(Model model,String phoneNumber,String reservedInf){
        boolean flag = userService.validationIfo(phoneNumber,reservedInf);//查询预留信息是否正确
        if(flag){
            model.addAttribute("mag","信息验证成功！");
            return new ModelAndView("retrievePassword");
        }else{
            model.addAttribute("mag","预留信息不正确！");
            return new ModelAndView("redirect:/toRetrievePassword");
        }
    }

    //重置密码
    @RequestMapping("retrievePassword")
    public ModelAndView retrievePassword(Model model, HttpServletRequest request,String phoneNumber) {
        String password = request.getParameter("password");
        userService.changePassword(password,phoneNumber);
        model.addAttribute("mgs", "修改成功");
        return new ModelAndView("login");
    }

    //修改性别
    @RequestMapping("changeSex")
    public ModelAndView changeSex(){
        return null;

    }

    //修改用户地区

    //设置预留信息


}
