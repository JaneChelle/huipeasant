package com.wlgzs.huipeasant.service;

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
}
