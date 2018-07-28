package com.wlgzs.huipeasant.service.impl;

import com.wlgzs.huipeasant.dao.CollectionRepository;
import com.wlgzs.huipeasant.dao.VideoRepository;
import com.wlgzs.huipeasant.entity.Collection;
import com.wlgzs.huipeasant.entity.Video;
import com.wlgzs.huipeasant.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:胡亚星
 * @createTime 2018-07-18 17:22
 * @description:
 **/
@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    VideoRepository videoRepository;
    @Autowired
    CollectionRepository collectionRepository;

    //添加收藏
    @Override
    public void collectionVideo(long userId, long videoId) {
        Video video = videoRepository.findById(videoId);
        Collection collection = new Collection();
        collection.setModelTitle(video.getVideoTitle());
        collection.setUserId(userId);
        collection.setVideoCover(video.getVideoCover());
        collection.setVideoId(videoId);
        collectionRepository.save(collection);
    }

    //删除收藏
    @Override
    public void deleteCollection(long collectionId) {
        collectionRepository.deleteById(collectionId);
    }

    //查找收藏
    @Override
    public List<Collection> toCollection(long userId) {
        List<Collection> collections = collectionRepository.findCollectionByUserId(userId);
        return collections;
    }

}
