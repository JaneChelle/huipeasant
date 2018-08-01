package com.wlgzs.huipeasant.controller;

import com.wlgzs.huipeasant.base.BaseController;
import com.wlgzs.huipeasant.entity.User;
import com.wlgzs.huipeasant.util.CheckImage;
import com.wlgzs.huipeasant.util.Result;
import com.wlgzs.huipeasant.util.ResultCode;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
@RestController
@RequestMapping("UserManagementController")
public class UserManagementController extends BaseController {

    //展示用户信息
    @RequestMapping("ipinfo")
    public ModelAndView displayIpinfo(Model model,HttpSession session) {
        User user = (User)session.getAttribute("user");
        System.out.println(user);
        model.addAttribute("user", user);
        return new ModelAndView("phone/ipinfo");
    }
    //展示用户信息
    @RequestMapping("information")
    public ModelAndView displayInformation(Model model,HttpSession session) {
        User user = (User)session.getAttribute("user");
        System.out.println(user);
        model.addAttribute("user", user);
        return new ModelAndView("information");
    }

    //手机端展示用户信息
    @RequestMapping("ipinfor")
    public ModelAndView displayIpInfor(Model model,HttpSession session) {
        User user = (User)session.getAttribute("user");
        System.out.println(user);
        model.addAttribute("user", user);
        return new ModelAndView("phone/ipinfo");
    }
    //跳转到修改昵称
    @RequestMapping("toChangeNickname")
    public ModelAndView toChangeNickname(){
        return new ModelAndView("phone/Nickname");
    }
    //跳转到修改地址
    @RequestMapping("toChangeAdress")
    public ModelAndView toChangeAdress(){
        return new ModelAndView("phone/address");
    }

    //跳转到修改性别
    @RequestMapping("toChangeSex")
    public ModelAndView toChangeSex(){
        return new ModelAndView("phone/ipEditSex");
    }

    //手机端修改昵称
    @RequestMapping("changeIPInformation")
    public ModelAndView changeIPInformation(Model model, HttpServletRequest request,String NickName) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println("sessionuser"+user);
        userService.ModifyName(request,user,NickName);
        model.addAttribute("user", user);
        return new ModelAndView("redirect:/UserManagementController/ipinfor");
    }

    //修改昵称
    @RequestMapping("changeInformation")
    public ModelAndView ModifyName(Model model, HttpServletRequest request,String NickName) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println("sessionuser"+user);
        userService.ModifyName(request,user,NickName);
        model.addAttribute("user", user);
        return new ModelAndView("redirect:/UserManagementController/information");
    }

    //跳转到修改手机号
    @RequestMapping("toChangePhone")
    public ModelAndView toChangePhone(){
        return new ModelAndView("changePhone");
    }

    //修改手机号//需要phoneNumber(新的)
    @RequestMapping("changePhone")
    public ModelAndView changePhone(Model model, HttpServletRequest request,String phoneNumber) {
        System.out.println(phoneNumber);
        //修改手机号
        User user = userService.changePhone(request,phoneNumber);
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
        session.setAttribute("user",user);
        model.addAttribute("user", user);
        return new ModelAndView("information");
    }

    //修改头像(shouj)
    @RequestMapping("/ModifyIpAvatar")
    public ModelAndView ModifyIpAvatar(@RequestParam("file") MultipartFile myFileName, HttpSession session,
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
        return new ModelAndView("redirect:/UserManagementController/ipinfor");
    }


    //跳转到修改密码
    @RequestMapping("/toChangePassword")
    public ModelAndView toChangePassword(){
        return new ModelAndView("changePassword");
    }

    //修改密码
    @PostMapping(value = "/checkPassword")
    public Result checkPassword(Model model, HttpServletRequest request,String password,String rePassword) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        long userId = user.getUserId();
        if (userService.checkPassWord(password, userId)) {//正确ze修改密码
            System.out.println("正确");
            userService.changePassword(rePassword, userId);
            return new Result(ResultCode.SUCCESS,"修改成功！");
        } else {//原密码错误
            model.addAttribute("userId",userId);
            return new Result(ResultCode.FAIL,"原密码错误！");
        }
    }

    //跳转到找回密码页面
    @RequestMapping("/toRetrievePassword")
    public ModelAndView toSendRetrievePassword() {
        return new ModelAndView("RetrievePassword");
    }

    //找回密码（输入手机号和预留信息）
    @RequestMapping("/validationIfo")
    public ModelAndView validationIfo(Model model,String phoneNumber,String reservedInf){
        boolean flag = userService.validationIfo(phoneNumber,reservedInf);//查询预留信息是否正确
        if(flag){
            model.addAttribute("mag","信息验证成功！");
            model.addAttribute("phoneNumber",phoneNumber);
            return new ModelAndView("Password");
        }else{
            model.addAttribute("mag","预留信息不正确！");
//              return new ModelAndView("redirect:/toRetrievePassword");
            return new ModelAndView("RetrievePassword");
        }
    }

    //重置密码
    @RequestMapping("/retrievePassword")
    public ModelAndView retrievePassword(Model model, HttpServletRequest request,String phoneNumber) {

        String password = request.getParameter("password");
        userService.changePassword(password,phoneNumber);
        model.addAttribute("mgs", "修改成功");
        return new ModelAndView("login");
    }

    //修改性别
    @RequestMapping("changeSex")
    public ModelAndView changeSex(Model model,HttpSession session,String sex){
        User user = (User)session.getAttribute("user");
        userService.ModifySex(user,sex,session);
        return new ModelAndView("redirect:/UserManagementController/information");
    }

    //手机端修改性别
    @RequestMapping("changeIpSex")
    public ModelAndView changeIpSex(Model model,HttpSession session,String sex){
        User user = (User)session.getAttribute("user");
        userService.ModifySex(user,sex,session);
        return new ModelAndView("redirect:/UserManagementController/ipinfor");
    }

    //修改用户地区
    @RequestMapping("changeAddress")
    public ModelAndView changeAddress(Model model,HttpSession session,String address){
        User user = (User)session.getAttribute("user");
        userService.changeAddress(user,address,session);
        return new ModelAndView("redirect:/UserManagementController/information");
    }
    //地区
    @RequestMapping("area")
    public ModelAndView area(){
        return new ModelAndView("area");
    }
    //个人
    @RequestMapping("personal")
    public ModelAndView personal(){
        return new ModelAndView("personal");
    }


    //手机端修改用户地区
    @RequestMapping("changeIpAddress")
    public ModelAndView changeIpAddress(Model model,HttpSession session,String address){
        User user = (User)session.getAttribute("user");
        userService.changeAddress(user,address,session);
        return new ModelAndView("redirect:/UserManagementController/ipinfor");
    }


}
