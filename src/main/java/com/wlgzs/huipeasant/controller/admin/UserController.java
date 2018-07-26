package com.wlgzs.huipeasant.controller.admin;

import com.wlgzs.huipeasant.base.BaseController;
import com.wlgzs.huipeasant.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author:胡亚星
 * @createTime 2018-07-11 9:56
 * @description: 后台操作用户增删改查
 **/
@Controller
@RequestMapping("adminUserController")
public class UserController extends BaseController {

    //后台遍历用户
    @RequestMapping("/adminUserList")
    public String list(Model model, @RequestParam(value = "page",defaultValue = "0") int page,
                       @RequestParam(value = "limit",defaultValue = "10") int limit) {
        System.out.println("123456789");
        String nickName="";
        if(page != 0) page--;
        Page pages = userService.findUserPage(nickName,page,limit);
        model.addAttribute("TotalPages", pages.getTotalPages());//查询的页数
        model.addAttribute("Number", pages.getNumber()+1);//查询的当前第几页
        List<User> users = pages.getContent();
        model.addAttribute("users", users);//查询的当前页的集合
        return "admin/adminUserList";
    }

    //搜索用户
    @RequestMapping("/adminFindUser")
    public  String findUserName(Model model,String user_name,@RequestParam(value = "page",defaultValue = "0") int page,
                                @RequestParam(value = "limit",defaultValue = "10") int limit){
        if(page != 0) page--;
        Page pages = userService.findUserPage(user_name,page,limit);
        model.addAttribute("TotalPages", pages.getTotalPages());//查询的页数
        model.addAttribute("Number", pages.getNumber()+1);//查询的当前第几页
        List<User> users = pages.getContent();
        model.addAttribute("users", users);//查询的当前页的集合
        model.addAttribute("user_name",user_name);
        return "admin/adminUserList";
    }

    //增加用户
    @RequestMapping("/adminAddUser")
    public String add(User user) {
        userService.save(user);
        return "redirect:/AdminUserController/adminUserList";
    }

    //跳转到修改用户
    @RequestMapping("toAdminEditUser")
    public ModelAndView toAdminEditUser(long userId,Model model){
        User user = userService.findUserById(userId);
        model.addAttribute("user",user);
        return new ModelAndView("adminEditUser");
    }

    //修改用户
    @RequestMapping("/adminEditUser")
    public String edit(Model model,User user) {
        String mag = userService.edit(user);
        model.addAttribute("mag",mag);
        return "redirect:/AdminUserController/adminUserList";
    }

    //删除用户
    @RequestMapping("/adminDeleteUser")
    public String delete(Model model,Long userId, HttpServletRequest request) {
        String mag = userService.delete(userId, request);
        model.addAttribute("mag",mag);
        return "redirect:/AdminUserController/adminUserList";
    }
}
