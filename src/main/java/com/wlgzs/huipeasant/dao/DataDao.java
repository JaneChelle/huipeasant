package com.wlgzs.huipeasant.dao;

import com.wlgzs.huipeasant.entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataDao extends JpaRepository<Data,Long> {
    List<Data> findByModuleId(long moudelId);
    List<Data> findBymoduleLevelOrderByUploadTimeDesc(int moduleLevel);
    List<Data> findBymoduleLevelOrderByHitsDesc(int moduleLevel);

}
