package com.wlgzs.huipeasant.dao;

import com.wlgzs.huipeasant.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author:胡亚星
 * @createTime 2018-07-11 9:58
 * @description:
 **/
public interface UserRepository extends JpaRepository<User, Long>,JpaSpecificationExecutor<User> {
    User findById(long userId);

    //修改手机号
    @Query("UPDATE User u SET u.phoneNumber=?1 WHERE u.userId=?2")
    @Modifying
    @Transactional
    void changePhone(String phoneNumber,long userId);

    //修改用户头像路径
    @Query("UPDATE User u SET u.headPortrait=headPortrait WHERE u.userId=id")
    @Modifying
    @Transactional
    void ModifyAvatar(String headPortrait,long id);

    //修改密码：判断用户输入密码是否正确
    @Query("FROM User u WHERE u.password=?1 and u.userId=?2")
    User checkPassWord(String password,long id);

    //修改密码
    @Query("UPDATE User u SET u.password=?1 WHERE u.userId=?2")
    @Modifying
    @Transactional
    void changePassword(String Password,long userId);

    //重置密码
    @Query("UPDATE User u SET u.password=?1 WHERE u.phoneNumber=?2")
    @Modifying
    @Transactional
    void changePassword(String rePassword,String phoneNumber);

    //判断用户预留信息是否正确
    @Query("FROM User u WHERE u.phoneNumber=?1 and u.reservedInf=?2")
    User validationIfo(String phoneNumber, String reservedInf);

}
