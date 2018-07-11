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

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * @author:胡亚星
 * @createTime 2018-07-11 9:47
 * @description:
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    //后台遍历和搜索用户
    @Override
    public Page<User> findUserPage(String nickName, int page, int limit) {
        Sort sort = new Sort(Sort.Direction.DESC, "userId");
        Pageable pageable = new PageRequest(page,limit, sort);
        Specification<User> specification = new PageUtil<User>(nickName).getPage("nickName","phoneNumber");
        Page pages = userRepository.findAll(specification,pageable);
        return pages;
    }

    //添加用户
    @Override
    public void save(User user) {
        user.setHeadPortrait("");
        userRepository.save(user);
    }

    //修改用户
    @Override
    public String edit(User user) {
        User userTwo = userRepository.findById(user.getUserId());
        if(userTwo != null){
            if(userTwo.getRoleId() == 1){
                user.setRoleId(1);
            }
            user.setHeadPortrait("");
            userRepository.saveAndFlush(user);
            return "成功";
        }
        return "失败";
    }

    //删除用户
    @Override
    public String delete(long userId, HttpServletRequest request) {
        User user = userRepository.findById(userId);
        if(user != null){
            String img = user.getHeadPortrait();
            String path = request.getSession().getServletContext().getRealPath("/");
            if(img!=null){
                File file = new File(path+""+img.substring(1,img.length()));
                System.out.println(path+""+img.substring(1,img.length()));
                if (!img.equals("") && file.exists() && file.isFile()) {
                    file.delete();
                }
            }
            userRepository.deleteById(userId);
            return "成功";
        }
        return "失败";
    }


}
