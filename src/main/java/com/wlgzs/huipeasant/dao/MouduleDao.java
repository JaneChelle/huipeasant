package com.wlgzs.huipeasant.dao;

import com.wlgzs.huipeasant.entity.Data;
import com.wlgzs.huipeasant.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MouduleDao extends JpaRepository<Module,Long> {
     List<Module> findByModuleLevel(int moduleLevel);
}
