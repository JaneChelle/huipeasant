package com.wlgzs.huipeasant.service;

import com.wlgzs.huipeasant.entity.User;
import org.springframework.data.domain.Page;

/**
 * @author:胡亚星
 * @createTime 2018-07-11 9:46
 * @description: 用户增删改查,注册
 **/
public interface UserService {
    Page<User> findUserPage(String user_name, int pa, int limit);

}
