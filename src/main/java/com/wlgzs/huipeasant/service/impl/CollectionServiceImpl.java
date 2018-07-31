package com.wlgzs.huipeasant.service.impl;

import com.wlgzs.huipeasant.dao.CollectionRepository;
import com.wlgzs.huipeasant.dao.VideoRepository;
import com.wlgzs.huipeasant.entity.Collection;
import com.wlgzs.huipeasant.entity.User;
import com.wlgzs.huipeasant.entity.Video;
import com.wlgzs.huipeasant.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
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
    public void deleteCollection(long videoId,HttpSession session) {
        User user = (User)session.getAttribute("user");
            long userId = user.getUserId();
            Collection collection = collectionRepository.isCollection(userId,videoId);
        collectionRepository.deleteById(collection.getCollectionId());
    }

    //查找收藏
    @Override
    public List<Collection> toCollection(long userId) {
        List<Collection> collections = collectionRepository.findCollectionByUserId(userId);
        return collections;
    }

    //查找视频是否被收藏
    @Override
    public boolean isCollection(long videoId, HttpSession session) {
        User user = (User)session.getAttribute("user");
        if(user != null){
            long userId = user.getUserId();
            Collection collection = collectionRepository.isCollection(userId,videoId);
            if(collection != null){
                return true;
            }
        }
        return false;
    }

}
