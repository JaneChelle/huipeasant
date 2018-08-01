package com.wlgzs.huipeasant.controller.admin;

import com.wlgzs.huipeasant.base.BaseController;
import com.wlgzs.huipeasant.entity.Data;
import com.wlgzs.huipeasant.entity.Module;
import com.wlgzs.huipeasant.util.Result;
import com.wlgzs.huipeasant.util.ResultCode;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 武凯焱
 * @date 2018/7/25 16:45
 */
@RestController
@RequestMapping("AdminDataController")

public class AdminDataController extends BaseController {


    @GetMapping("toViewdata/{level}/{page}")  //查看文章
    public ModelAndView toViewData(Model model,@PathVariable("level")  int level ,@PathVariable("page") int page) {
        dataService.ipgetDatas(0,level, page, model);
        model.addAttribute("level", level);
        if(level==1){
            return new ModelAndView("admin/adminArticle");
        }if (level==2){
            return new ModelAndView("admin/adminQuestion");
        }
        else {
            return new ModelAndView("admin/adminInfom");
        }

    }

    @PostMapping("savedata")
    public Result saveData(Data data, int level, MultipartFile multipartFile) throws IOException {
        if (dataService.addData(data, multipartFile, level)) {
            return new Result(ResultCode.SUCCESS, "保存成功");
        } else {
            return new Result(ResultCode.FAIL, "保存失败");
        }
    }

    @PostMapping("deletedata")
    public Result deletedata(long dataId) {
        dataService.delete(dataId);
        return new Result(ResultCode.SUCCESS, "删除成功");
    }

    @PostMapping("deletedatas")
    public Result deletedatas(long[] dataIds) {
        if (dataIds != null) {
            dataService.deletedatas(dataIds);
            return new Result(ResultCode.SUCCESS, "删除成功");
        } else {
            return new Result(ResultCode.FAIL, "请至少选择一项");
        }
    }

    @PostMapping("deletemoudel")
    public Result deletemoudel(long moudelId) {
        moduleService.deleteMoudel(moudelId);
        return new Result(ResultCode.SUCCESS, "删除成功");
    }

    @RequestMapping("modulesView")//查看模块
    public ModelAndView modulesView(Model model) {
        List<Module> list = new ArrayList<Module>();
        list = moduleService.getModules();
        model.addAttribute("modules", list);
        return new ModelAndView("admin/adminModel");
    }

    @PostMapping("savemodules") //保存模块
    public void savemodules(Module module) {
        moduleService.saveMoudel(module);
    }
    @PostMapping("adminSearchData")
    public ModelAndView adminSearchData(Model model){

       return new ModelAndView();
    }
}
