package com.wlgzs.huipeasant.controller;

import com.wlgzs.huipeasant.base.BaseController;
import com.wlgzs.huipeasant.entity.Data;
import com.wlgzs.huipeasant.entity.Module;
import com.wlgzs.huipeasant.util.Result;
import com.wlgzs.huipeasant.util.ResultCode;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DataController extends BaseController {
    @RequestMapping("toindex")
    public ModelAndView toindex(Model model){
         model.addAttribute("moudels",dataService.index());
         model.addAttribute("question",dataService.question());
         model.addAttribute("information",dataService.information());
         model.addAttribute("rank",dataService.indexRank());
         model.addAttribute("videoList",videoService.videoList());
        return new ModelAndView("index");
    }
    @RequestMapping("toaddData")
    public ModelAndView toaddData(Model model){
        List<Module> list = new ArrayList<Module>();
        list = moduleService.getModules();
        model.addAttribute("types",list);
        return new ModelAndView("toaddData");
    }
    @PostMapping("addData")
    public Result addData(Data data, MultipartFile multipartFile,int dataLevle){
       boolean isTrue = dataService.addData(data,multipartFile,dataLevle);
       if (isTrue){
           return new Result(ResultCode.SUCCESS,"上传成功");
       }else {
           return new Result(ResultCode.FAIL,"上传失败，请检查信息是否填写完整");
       }
    }
}