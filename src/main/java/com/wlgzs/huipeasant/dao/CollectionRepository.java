package com.wlgzs.huipeasant.dao;

import com.wlgzs.huipeasant.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author:胡亚星
 * @createTime 2018-07-18 17:20
 * @description:
 **/
public interface CollectionRepository extends JpaRepository<Collection, Long>,JpaSpecificationExecutor<Collection> {


}
