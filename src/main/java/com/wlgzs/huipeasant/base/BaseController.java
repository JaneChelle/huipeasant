package com.wlgzs.huipeasant.base;

import com.wlgzs.huipeasant.service.LogUserService;
import com.wlgzs.huipeasant.service.UserService;
import com.wlgzs.huipeasant.service.VideoService;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @author:胡亚星
 * @createTime 2018-07-11 17:07
 * @description:
 **/
public abstract class BaseController implements Serializable {
    @Resource
    protected LogUserService logUserService;
    protected UserService userService;
    protected VideoService videoService;
}
