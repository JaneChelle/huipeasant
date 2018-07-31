package com.wlgzs.huipeasant.service.impl;


import com.wlgzs.huipeasant.dao.LogUserRepository;
import com.wlgzs.huipeasant.entity.User;

import com.wlgzs.huipeasant.service.LogUserService;
import com.wlgzs.huipeasant.util.RandonNumberUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author:胡亚星
 * @createTime 2018-07-11 10:21
 * @description:
 **/
@Service
public class LogUserServiceImpl implements LogUserService {

    @Autowired
    private LogUserRepository logUserRepository;

    //注册
    @Override
    public String register(HttpServletRequest request) {
        User user = new User();
        RandonNumberUtils randonNumberUtils = new RandonNumberUtils();
        Map<String, String[]> properties = request.getParameterMap();
        try {
            BeanUtils.populate(user, properties);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        user.setHeadPortrait("/upload/moorende.jpg");
        user.setNickName("用户" + randonNumberUtils.getNumber(6));
        user.setAddress("北京市 北京城区 东城区");
        user.setSex("男");
        user.setRoleId(2);
        if(logUserRepository.checkPhoneNumber(user.getPhoneNumber()) == null){
            logUserRepository.save(user);
            return "注册成功！";
        }
        return "注册失败！";
    }

    //登录
    @Override
    public String login(HttpServletRequest request, String phoneNumber, String password) {
        User user = logUserRepository.checkPhoneNumber(phoneNumber);
        if (user != null) {
            if (user.getPassword().equals(password)) {//正确
                HttpSession session = request.getSession(true);
                if (user.getRoleId() == 1) {//管理员
                    session.setAttribute("adminUser", user);
                    return "管理员登录成功！";
                } else {

                    session.setAttribute("user", user);
                    return "登录成功！";
                }
            } else {
                return "密码不正确！";
            }
        } else {
            return "该手机还未注册！";
        }
    }

    //用户退出
    @Override
    public void cancellation(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if (session.getAttribute("user") != null) {
            session.removeAttribute("user");
        }
    }

    //管理员退出
    @Override
    public void adminCancellation(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if (session.getAttribute("adminUser") != null) {
            session.removeAttribute("adminUser");
        }
    }

    //验证手机号是否存在
    @Override
    public boolean validationPhone(String phoneNumber) {
        if (logUserRepository.checkPhoneNumber(phoneNumber) != null) {
            return false;
        }
        return true;
    }


}
