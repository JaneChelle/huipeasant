package com.wlgzs.huipeasant.service;

import com.wlgzs.huipeasant.entity.Module;

import java.util.List;

public interface ModuleService {
     void addModule(Module module);
     List<Module> getModules();
     void deleteMoudel(long moudelId); //删除模块
     void deleteMoudels(long[] moudelIds); //批量删除模块
     void saveMoudel(Module module);   //保存更改的模块
}
