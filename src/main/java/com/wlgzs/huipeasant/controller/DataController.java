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


        //农技视频
        List<Video> videoOneList = videoService.videoList(1);
        model.addAttribute("videoList", videoOneList);

        //电商视频
        List<Video> videoTowList = videoService.videoList(2);
        model.addAttribute("videoTowList", videoTowList);

        //视频排行
        List<Video> videoRanking = videoService.videoRanking();
        if(videoRanking.size() > 4){
            videoRanking = videoRanking.subList(videoRanking.size() - 4, videoRanking.size());
        }
        model.addAttribute("videoRanking",videoRanking);
        model.addAttribute("moudel",moduleService.getModules());

        return new ModelAndView("index");
    }

    @GetMapping("toindex1")
    public ModelAndView toIndex1() {
        return new ModelAndView("index1");
    }

    @RequestMapping("toipindex")              //进入手机端主页
    public ModelAndView toIpindex(Model model) {
        model.addAttribute("infor", dataService.information());
        model.addAttribute("question", dataService.ipQuestion(2, 1, model));
        return new ModelAndView("/phone/ipindex");
    }

    @GetMapping("morequestion/{page}")
    public ModelAndView ipMoreQuestion(Model model, @PathVariable("page") int page) {
        model.addAttribute("question", dataService.ipQuestion(2, 1, model));
        return new ModelAndView("");
    }

    @RequestMapping("toipindex/{level}/{page}")  //手机端主页 关于文章排行/资讯的接口
    public ModelAndView toIpindex(Model model, @PathVariable("level") int level, @PathVariable("page") int page) {
        int status = 1;
        dataService.ipgetDatas(status, level, 1, model);
        return new ModelAndView("ipindex");
    }
    @RequestMapping("indexmore/{level}/{page}")//pc主页 排行 资讯更多
    public ModelAndView indexMore(Model model, @PathVariable("level") int level, @PathVariable("page") int page){
        int status = 1;
        dataService.ipgetDatas(status, level, 1, model);
        if (level==1){
            return new ModelAndView("ipindex");
        }else {
            return new ModelAndView("");
        }
    }
    @RequestMapping("updateaticel/{page}")  //文章更新排行
    public ModelAndView articleUpdate(Model model, @PathVariable("page") int page) {
        int status = 1;
        dataService.ipgetDatas(1, 1, page, model);
        return new ModelAndView("phone/List-of-articles");
    }

    @RequestMapping("toaddData")

    public ModelAndView toaddData(Model model) {
        model.addAttribute("moudels", moduleService.getModules());
        return new ModelAndView("qusetion");

    }


    @PostMapping("addData")         //  添加数据
    public ModelAndView addData(Data data, MultipartFile multipartFile, int dataLevle) throws IOException {
        System.out.println("tgdfygdryr" + dataLevle);

        boolean isTrue = dataService.addData(data, multipartFile, dataLevle);
        if (isTrue) {
            if (dataLevle == 2)
                return new ModelAndView("myqusetion");
            if (dataLevle == 1)
                return new ModelAndView("");
            if (dataLevle == 3)
                return new ModelAndView("");
        } else {
            return new ModelAndView("");
        }
        return new ModelAndView("myqusetion");
    }


    @GetMapping("textview/{dataId}")   //进入文章页面
    public ModelAndView textView(Model model, @PathVariable("dataId") long dataId) {

        model.addAttribute("data", dataService.dataView(dataId));

        if (dataService.jundegeView(dataId)) {
            model.addAttribute("paragraphs", dataService.paragraphList(dataService.textView(dataId).getContents()));
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
        if ( dataService.searchData(dataName)==null){
            model.addAttribute("message","对不起，没有查询到相应的数据");
        }
        return new ModelAndView("material");
    }

    @RequestMapping("question")  //进入提问问题界面
    public ModelAndView question(Model model) {
        model.addAttribute("moudels", moduleService.getModules());
        return new ModelAndView("qusetion");
    }

    @RequestMapping("viewquestion")
    public ModelAndView viewQuestion(Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("question", dataService.userGetquestion(user.getUserId()));
        System.out.println("dfcsfsfsf" + dataService.userGetquestion(user.getUserId()));
        return new ModelAndView("myqusetion");
    }
    //进入搜索页面
    @RequestMapping("/toSearch")
    public ModelAndView toSearch() {
        return new ModelAndView("phone/search") ;
    }

    @PostMapping("searchDataIP")   //搜索
    public ModelAndView searchDataIP(Model model, String dataName) {
        model.addAttribute("datas", dataService.searchData(dataName));
        return new ModelAndView("phone/search-a");
    }

    @RequestMapping("morequestion")  //手机端更多问题
    public ModelAndView moreQuestion(Model model) {
        model.addAttribute("question", dataService.getAllipQuestion());
        return new ModelAndView("");
    }
    @GetMapping("ipviewartical/{dataId}")  //手机端观看文章
    public ModelAndView ipViewArtical(Model model,@PathVariable("dataId") long dataId){
        model.addAttribute("data", dataService.dataView(dataId));
        System.out.println(dataService.dataView(dataId));
        model.addAttribute("paragraphs", dataService.paragraphList(dataService.textView(dataId).getContents()));
        return new ModelAndView("phone/Article");
    }


}
