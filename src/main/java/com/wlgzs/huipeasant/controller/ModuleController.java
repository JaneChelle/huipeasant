package com.wlgzs.huipeasant.controller;

import com.wlgzs.huipeasant.base.BaseController;
import com.wlgzs.huipeasant.entity.Module;
import com.wlgzs.huipeasant.util.Result;
import com.wlgzs.huipeasant.util.ResultCode;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ModuleController extends BaseController {

    @RequestMapping("addaddModule")//添加模块
    public Result addModule(Module module){
        moduleService.addModule(module);
        return new Result(ResultCode.SUCCESS,"添加成功");
    }
    @RequestMapping("modulesView")//查看模块
    public void modulesView(Model model){
        List<Module> list = new ArrayList<Module>();
        list = moduleService.getModules();
        model.addAttribute("modules",list);
    }

}
