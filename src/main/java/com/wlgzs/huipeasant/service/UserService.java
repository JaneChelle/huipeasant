package com.wlgzs.huipeasant.service;

import com.wlgzs.huipeasant.entity.User;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;

/**
 * @author:胡亚星
 * @createTime 2018-07-11 9:46
 * @description: 用户增删改查,注册
 **/
public interface UserService {
    //搜索用户
    Page<User> findUserPage(String nickName, int pa, int limit);

    //添加用户
    void save(User user);

    //修改用户
    String edit(User user);

    //删除用户
    String delete(long userId, HttpServletRequest request);
}
