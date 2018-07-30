package com.wlgzs.huipeasant.dao;

import com.wlgzs.huipeasant.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author:胡亚星
 * @createTime 2018-07-13 9:12
 * @description:
 **/
public interface VideoRepository extends JpaRepository<Video, Long>,JpaSpecificationExecutor<Video> {
    Video findById(long videoId);

    //按类型查询视频
    @Query("FROM Video v WHERE v.moduleId=?1")
    List<Video> findOneVideo(long moduleId);

}
