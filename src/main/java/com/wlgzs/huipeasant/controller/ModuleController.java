package com.wlgzs.huipeasant.controller;

import com.wlgzs.huipeasant.base.BaseController;
import com.wlgzs.huipeasant.entity.Module;
import com.wlgzs.huipeasant.util.Result;
import com.wlgzs.huipeasant.util.ResultCode;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/user")
@RestController
public class ModuleController extends BaseController {

    @RequestMapping("addaddModule")//添加模块
    public Result addModule(Module module){
        moduleService.addModule(module);
        return new Result(ResultCode.SUCCESS,"添加成功");
    }

    @GetMapping("moduleview/{moudelId}/{page}")//查看模块所有数据
    public ModelAndView moduleView(Model model, @PathVariable("moudelId") long moudelId, @PathVariable("page") int page){
        model.addAttribute("information",dataService.information());
        model.addAttribute("rank",dataService.indexRank());
        dataService.moudelDatas(model,moudelId,page);
        return new ModelAndView("material") ;
    }
}