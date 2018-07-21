package com.wlgzs.huipeasant.service.impl;

import com.wlgzs.huipeasant.dao.MouduleDao;
import com.wlgzs.huipeasant.entity.Module;
import com.wlgzs.huipeasant.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    private MouduleDao mouduleDao;

    @Override
    public void addModule(Module module) {
        module.setModuleLevel(1);
        mouduleDao.save(module);
    }
    public List<Module> getModules(){
        return mouduleDao.findByModuleLevel(1);
    }

}
