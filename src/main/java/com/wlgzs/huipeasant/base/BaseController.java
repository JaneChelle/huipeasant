package com.wlgzs.huipeasant.base;

import com.wlgzs.huipeasant.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * @author:胡亚星
 * @createTime 2018-07-11 17:07
 * @description:
 **/
public abstract class BaseController implements Serializable {
    @Resource
    protected LogUserService logUserService;

    @Resource
    protected UserService userService;

    @Resource
    protected ModuleService moduleService;

    @Resource
    protected DataService dataService;

    @Resource
    protected CommentService commentService;

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpSession session;
}
