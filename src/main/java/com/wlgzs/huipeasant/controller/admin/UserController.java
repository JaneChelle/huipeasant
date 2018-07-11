package com.wlgzs.huipeasant.controller.admin;

import com.wlgzs.huipeasant.base.BaseController;
import com.wlgzs.huipeasant.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        String user_name="";
        if(page != 0) page--;
        Page pages = userService.findUserPage(user_name,page,limit);
        model.addAttribute("TotalPages", pages.getTotalPages());//查询的页数
        model.addAttribute("Number", pages.getNumber()+1);//查询的当前第几页
        List<User> users = pages.getContent();
        model.addAttribute("users", users);//查询的当前页的集合
        return "admin/adminUserList";
    }

    

}
