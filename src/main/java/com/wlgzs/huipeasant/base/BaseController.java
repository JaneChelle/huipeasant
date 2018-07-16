package com.wlgzs.huipeasant.base;


import com.wlgzs.huipeasant.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.wlgzs.huipeasant.service.UserService;
import com.wlgzs.huipeasant.service.VideoService;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @author:胡亚星
 * @createTime 2018-07-11 17:07
 * @description:
 **/
public abstract class BaseController  {
    @Resource
    protected LogUserService logUserService;

    @Resource
    protected UserService userService;


    @Autowired
    protected ModuleService moduleService;

    @Autowired
    protected DataService dataService;

    @Autowired
    protected CommentService commentService;

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpSession session;

    @Autowired
    protected VideoService videoService;

}
