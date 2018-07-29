package com.wlgzs.huipeasant.controller;

import com.wlgzs.huipeasant.base.BaseController;
import com.wlgzs.huipeasant.entity.Data;
import com.wlgzs.huipeasant.entity.User;
import com.wlgzs.huipeasant.entity.Video;
import com.wlgzs.huipeasant.util.Result;
import com.wlgzs.huipeasant.util.ResultCode;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@RequestMapping("/user")
@RestController
public class DataController extends BaseController {

    @RequestMapping("toindex")       //   进入pc端主页
    public ModelAndView toindex(Model model) {
        model.addAttribute("moudels", dataService.index());
        model.addAttribute("question", dataService.question());
        model.addAttribute("information", dataService.information());
        model.addAttribute("rank", dataService.indexRank());
        List<Video> videoList = videoService.videoList();
        videoList = videoList.subList(videoList.size() - 8, videoList.size());
        model.addAttribute("videoList", videoList);
        model.addAttribute("moudel",moduleService.getModules());
        return new ModelAndView("index");
    }

    @GetMapping ("toindex1")
    public ModelAndView toIndex1() {
        return new ModelAndView("index1");
    }
    @RequestMapping("toipindex")              //进入手机端主页
    public ModelAndView toIpindex(Model model){
        model.addAttribute("infor",dataService.information());
        model.addAttribute("question",dataService.ipQuestion(1,1,model));
        return new ModelAndView("/phone/ipindex");
    }

    @RequestMapping("toipindex/{level}/{page}")  //手机端主页 关于文章的接口
    public ModelAndView toIpindex(Model model, @PathVariable("level") int level, @PathVariable("page") int page) {
        dataService.ipgetDatas(level, page, model);
        return new ModelAndView("ipindex ");
    }

    @RequestMapping("toaddData")

    public ModelAndView toaddData(Model model) {
        model.addAttribute("moudels",moduleService.getModules());
        return new ModelAndView("question");

    }


    @PostMapping("addData")         //  添加数据
    public Result addData(Data data, MultipartFile multipartFile, int dataLevle) throws IOException {
        System.out.println("tgdfygdryr"+dataLevle);

        boolean isTrue = dataService.addData(data, multipartFile, dataLevle);
        if (isTrue) {
            return new Result(ResultCode.SUCCESS, "上传成功");
        } else {
            return new Result(ResultCode.FAIL, "上传失败，请检查信息是否填写完整");
        }
    }
    @GetMapping("textview/{dataId}")   //进入文章页面
    public ModelAndView textView(Model model, @PathVariable("dataId") long dataId) {
        model.addAttribute("paragraphs", dataService.paragraphList(dataService.textView(dataId).getContents()));
        model.addAttribute("data", dataService.dataView(dataId));

        if (dataService.jundegeView(dataId)) {
            model.addAttribute("question", dataService.relevantIssues());
            model.addAttribute("recommed", dataService.recommend(dataId));
            model.addAttribute("information", dataService.information());
            model.addAttribute("rank", dataService.indexRank());
            return new ModelAndView("article");
        } else {
            model.addAttribute("answer", commentService.getanswer(dataId));
            return new ModelAndView("ask");
        }
    }

    @PostMapping("/keyword")//下拉框提示接口
    public Result keyword(Model model, @RequestParam("keyword") String keyWord) {
        return new Result(ResultCode.SUCCESS, dataService.getKeyWord(keyWord));
    }

    @PostMapping("searchData")   //搜索
    public ModelAndView searchData(Model model, String dataName) {
        model.addAttribute("datas", dataService.searchData(dataName));
        return new ModelAndView("/");
    }
    @RequestMapping("question")  //进入提问问题界面
    public ModelAndView question(Model model){
        model.addAttribute("moudels",moduleService.getModules());
        return new ModelAndView("question");
    }
    @PostMapping("viewquestion")
    public ModelAndView viewQuestion(Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("question",dataService.userGetquestion(user.getUserId()));
        return new ModelAndView("myqusetion");
    }
}