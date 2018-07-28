package com.wlgzs.huipeasant.dao;

import com.wlgzs.huipeasant.entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DataDao extends JpaRepository<Data,Long> , JpaSpecificationExecutor<Data> {
    List<Data> findByModuleId(long moudelId);
    List<Data> findByModuleLevelOrderByUploadTimeDesc(int moduleLevel);
    List<Data> findByModuleLevelOrderByHitsDesc(int moduleLevel);
    List<Data> findByUserIdAndModuleLevel(long userId,int leLevel);
    List<Data> findByContentsTitleContaining(String contentdTitle);
    List<Data> findByContentsTitle(String contentsTitle);
}
