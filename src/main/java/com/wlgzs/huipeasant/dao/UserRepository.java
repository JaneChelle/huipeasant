package com.wlgzs.huipeasant.dao;

import com.wlgzs.huipeasant.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author:胡亚星
 * @createTime 2018-07-11 9:58
 * @description:
 **/
public interface UserRepository extends JpaRepository<User, Long>,JpaSpecificationExecutor<User> {
}
