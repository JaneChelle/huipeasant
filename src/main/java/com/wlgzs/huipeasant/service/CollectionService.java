package com.wlgzs.huipeasant.service;

import com.wlgzs.huipeasant.entity.Collection;

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
    void deleteCollection(long collectionId);

    //查看收藏
    List<Collection> toCollection(long userId);

}
