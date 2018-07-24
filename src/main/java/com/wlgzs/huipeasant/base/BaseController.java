package com.wlgzs.huipeasant.base;

import com.wlgzs.huipeasant.service.*;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @author:胡亚星
 * @createTime 2018-07-11 17:07
 * @description:
 **/
public abstract class BaseController implements Serializable {
    @Resource
    protected UserService userService;
    @Resource
    protected VideoService videoService;
    @Resource
    protected CollectionService collectionService;
    @Resource
    protected LogUserService logUserService;
}
