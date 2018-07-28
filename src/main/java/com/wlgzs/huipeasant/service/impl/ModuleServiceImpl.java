package com.wlgzs.huipeasant.service.impl;

import com.wlgzs.huipeasant.dao.DataDao;
import com.wlgzs.huipeasant.dao.MouduleDao;
import com.wlgzs.huipeasant.entity.Data;
import com.wlgzs.huipeasant.entity.Module;
import com.wlgzs.huipeasant.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    private MouduleDao mouduleDao;
    @Autowired
    private DataDao dataDao;

    @Override
    public void addModule(Module module) {
        module.setModuleLevel(1);
        mouduleDao.save(module);
    }
    public List<Module> getModules(){
        return mouduleDao.findByModuleLevel(1);
    }
    public void deleteMoudel(long moudelId){
         mouduleDao.deleteById(moudelId);
    }
    public void deleteMoudels(long[] moudelIds){
        for (int i=0;i<moudelIds.length;i++){
            mouduleDao.deleteById(moudelIds[i]);
        }
    }
    public void saveMoudel(Module module){
        Module oldModule = mouduleDao.findById(module.getMouldeId()).get();
        module.setModuleLevel(1);
        String currentName = module.getModuleName();
        if (!currentName.equals(oldModule.getModuleName())) {
            List<Data> dataList = new ArrayList<Data>();
            dataList =  dataDao.findByModuleId(module.getMouldeId());
            for (int i=0;i<dataList.size();i++){
                 Data data = dataList.get(i);
                 data.setModuleName(currentName);
                 dataDao.save(data);
            }
        }
        mouduleDao.save(module);
    }

}

