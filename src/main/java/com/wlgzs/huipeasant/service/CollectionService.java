package com.wlgzs.huipeasant.service;

import com.wlgzs.huipeasant.entity.Collection;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author:胡亚星
 * @createTime 2018-07-18 17:21
 * @description:
 **/
public interface CollectionService {

    //添加收藏
    void collectionVideo(long userId,long videoId);

    //删除收藏
    void deleteCollection(long videoId,HttpSession session);

    //查看收藏
    List<Collection> toCollection(long userId);

    //查找视频是否被收藏
    boolean isCollection(long videoId, HttpSession session);

}
