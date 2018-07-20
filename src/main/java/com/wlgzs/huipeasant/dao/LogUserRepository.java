package com.wlgzs.huipeasant.dao;

import com.wlgzs.huipeasant.base.BaseController;
import com.wlgzs.huipeasant.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author:胡亚星
 * @createTime 2018-07-11 9:57
 * @description:
 **/
public interface LogUserRepository extends JpaRepository<User, Long>,JpaSpecificationExecutor<User> {
    //查询手机号是否已经存在
    @Query("FROM User a WHERE a.phoneNumber = ?1")
    User checkPhoneNumber(String phoneNumber);

    //查询用户名是否已经存在
    @Query("FROM User a WHERE a.nickName = ?1")
    User checkNickName(String nickName);
}
