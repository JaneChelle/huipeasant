package com.wlgzs.huipeasant.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author:胡亚星
 * @createTime 2018-07-24 9:27
 * @description:
 **/
public interface LogUserService {

    //用户注册
    String register(HttpServletRequest request);

    //用户登录
    String login(HttpServletRequest request,String phoneNumber, String password);

    //用户退出
    void cancellation(HttpServletRequest request);

    //管理员退出
    void adminCancellation(HttpServletRequest request);

    //验证手机号是否存在
    boolean validationPhone(String phoneNumber);
}
