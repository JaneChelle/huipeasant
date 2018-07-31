package com.wlgzs.huipeasant.service.impl;

import com.wlgzs.huipeasant.dao.LogUserRepository;
import com.wlgzs.huipeasant.dao.UserRepository;
import com.wlgzs.huipeasant.entity.User;
import com.wlgzs.huipeasant.service.UserService;
import com.wlgzs.huipeasant.util.IoUtil;
import com.wlgzs.huipeasant.util.PageUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author:胡亚星
 * @createTime 2018-07-11 9:47
 * @description:
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LogUserRepository logUserRepository;

    //查找用户
    @Override
    public User findUserById(long userId) {
        return userRepository.findById(userId);
    }

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
                File file = new File(path+""+img);
                System.out.println(path+""+img);
                if (!img.equals("") && file.exists() && file.isFile()) {
                    file.delete();
                }
            }
            userRepository.deleteById(userId);
            return "成功";
        }
        return "失败";
    }

    //修改用户名
    @Override
    public void ModifyName(HttpServletRequest request, User user,String NickName) {
        System.out.println(user);
        System.out.println(NickName);
        //判断用户名是否存在
        if(NickName != null && !NickName.equals("")){
            if(null == logUserRepository.checkNickName(NickName)){
                System.out.println("=======");
                user.setNickName(NickName);
                userRepository.saveAndFlush(user);
                //从新存入session
                HttpSession session = request.getSession(true);
                session.setMaxInactiveInterval(60 * 20);
                session.setAttribute("user",user);
            }
        }
    }

    //修改手机
    @Override
    public User changePhone(HttpServletRequest request,String phoneNumber) {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("user");
        long userId = user.getUserId();
        System.out.println("phoneNumber"+phoneNumber);
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(phoneNumber);
        if(m.matches() && !phoneNumber.equals("")){
            System.out.println("qwertyyui");
            userRepository.changePhone(phoneNumber,userId);
        }
        User user1 = userRepository.findById(userId);
        session.setAttribute("user",user1);
        return user1;
    }

    //修改用户头像
    @Override
    public User ModifyAvatar(HttpSession session, HttpServletRequest request, MultipartFile myFileName) throws IOException {
        String userId = request.getParameter("userId");
        IoUtil ioUtil = new IoUtil();
        long id = Long.parseLong(userId);
        String realName = "";
        String headPortrait = "";
        if (!myFileName.getOriginalFilename().equals("")) {
            String fileName = myFileName.getOriginalFilename();
            String fileNameExtension = fileName.substring(fileName.indexOf("."), fileName.length());
            // 生成实际存储的真实文件名
            realName = UUID.randomUUID().toString() + fileNameExtension;
            // "/upload"是你自己定义的上传目录
            String realPath = "/upload/headPortrait/" + realName;
            ioUtil.saveFile(myFileName,realPath);
            headPortrait = realPath;
        } else {
            headPortrait = "/upload/headPortrait/" + "morende.jpg";
        }
        System.out.println(headPortrait);
        Map<String, String[]> properties = request.getParameterMap();
        User user = userRepository.findById(id);
        try {
            BeanUtils.populate(user, properties);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        user.setHeadPortrait(headPortrait);
        session.setAttribute("user",user);
        userRepository.ModifyAvatar(headPortrait,id);
        return user;
    }

    //判断修改密码密码是否正确
    @Override
    public boolean checkPassWord(String password, long userId) {
        User user = userRepository.checkPassWord(password,userId);
        if(user != null){//正确
            return true;
        }else{//错误
            return false;
        }
    }

    //修改用户密码
    @Override
    public void changePassword(String rePassword, long userId) {
        userRepository.changePassword(rePassword,userId);
    }

    //重置用户密码
    @Override
    public void changePassword(String rePassword, String phoneNumber) {
        userRepository.changePassword(rePassword,phoneNumber);
    }

    //判断用户预留信息是否正确
    @Override
    public boolean validationIfo(String phoneNumber, String reservedInf) {
        User user = userRepository.validationIfo(phoneNumber,reservedInf);
            return (user != null);
    }

    @Override
    public void ModifySex(User user, String sex,HttpSession session) {
        user.setSex(sex);
        userRepository.saveAndFlush(user);
        //从新存入session
        session.setAttribute("user",user);
    }

    @Override
    public void changeAddress(User user, String address, HttpSession session) {
        user.setAddress(address);
        userRepository.saveAndFlush(user);
        //从新存入session
        session.setMaxInactiveInterval(60 * 20);
        session.setAttribute("user",user);
    }

}
