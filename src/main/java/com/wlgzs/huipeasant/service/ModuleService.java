package com.wlgzs.huipeasant.service;

import com.wlgzs.huipeasant.entity.Module;

import java.util.List;

public interface ModuleService {
     void addModule(Module module);
     List<Module> getModules();
}
