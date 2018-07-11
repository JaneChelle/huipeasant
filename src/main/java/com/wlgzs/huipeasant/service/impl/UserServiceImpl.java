package com.wlgzs.huipeasant.service.impl;

import com.wlgzs.huipeasant.dao.UserRepository;
import com.wlgzs.huipeasant.entity.User;
import com.wlgzs.huipeasant.service.UserService;
import com.wlgzs.huipeasant.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @author:胡亚星
 * @createTime 2018-07-11 9:47
 * @description:
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> findUserPage(String user_name, int page, int limit) {
        Sort sort = new Sort(Sort.Direction.DESC, "userId");
        Pageable pageable = new PageRequest(page,limit, sort);
        Specification<User> specification = new PageUtil<User>(user_name).getPage("user_name","user_role","user_mail");
        Page pages = userRepository.findAll(specification,pageable);
        return pages;
    }


}
