package com.wlgzs.huipeasant.controller;

import com.wlgzs.huipeasant.base.BaseController;
import com.wlgzs.huipeasant.entity.User;
import com.wlgzs.huipeasant.util.Result;
import com.wlgzs.huipeasant.util.ResultCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController extends BaseController {
    @PostMapping("tocomment")
    public Result tocomment(@RequestParam("content") String content,@RequestParam("dataId") long dataId){
        User user = (User)session.getAttribute("user");
        commentService.addcomment(user,content,dataId);
        return new Result(ResultCode.SUCCESS,"添加成功");

    }

}
