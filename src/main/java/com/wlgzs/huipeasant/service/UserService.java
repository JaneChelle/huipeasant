package com.wlgzs.huipeasant.service;

import com.wlgzs.huipeasant.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author:胡亚星
 * @createTime 2018-07-11 9:46
 * @description: 用户增删改查,注册
 **/
public interface UserService {
    //查找用户
    User findUserById(long userId);

    //搜索用户
    Page<User> findUserPage(String nickName, int pa, int limit);

    //后台添加用户
    void save(User user);

    //后台修改用户
    String edit(User user);

    //后台删除用户
    String delete(long userId, HttpServletRequest request);

    //修改用户名
    void ModifyName(HttpServletRequest request,User user);

    //修改手机号
    User changePhone(HttpServletRequest request);

    //修改用户头像
    User ModifyAvatar(HttpSession session, HttpServletRequest request, MultipartFile myFileName) throws IOException;

    //判断修改密码密码是否正确
    boolean checkPassWord(String password,long userId);

    //修改用户密码
    void changePassword(String rePassword,long userId);

    //重置用户密码
    void changePassword(String rePassword,String phoneNumber);

    //判断用户预留信息是否正确
    boolean validationIfo(String phoneNumber,String reservedInf);

    //修改性别
    void ModifySex(User user,String sex,HttpSession session);

}
