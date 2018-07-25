package com.wlgzs.huipeasant.dao;

import com.wlgzs.huipeasant.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author:胡亚星
 * @createTime 2018-07-18 17:20
 * @description:
 **/
public interface CollectionRepository extends JpaRepository<Collection, Long>,JpaSpecificationExecutor<Collection> {

    //查看收藏
    @Query(value = "SELECT c FROM Collection c WHERE c.userId=?1")
    List<Collection> findCollectionByUserId(long userId);

}
