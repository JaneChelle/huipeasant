package com.wlgzs.huipeasant.controller;

import com.wlgzs.huipeasant.base.BaseController;
import com.wlgzs.huipeasant.dao.CommentDao;
import com.wlgzs.huipeasant.entity.User;
import com.wlgzs.huipeasant.util.Result;
import com.wlgzs.huipeasant.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("user")
@RestController
public class CommentController extends BaseController {
    @Autowired
    CommentDao commentDao;

    @PostMapping("tocomment")
    public Result tocomment(@RequestParam("content") String content, @RequestParam("dataId") long dataId) {
        User user = (User) session.getAttribute("user");
        commentService.addcomment(user, content, dataId);
        return new Result(ResultCode.SUCCESS, "评论成功");
    }

    @GetMapping("morecomment/{dataId}")
    public ModelAndView moreComment(Model model, @PathVariable("dataId") long dataId) {
        model.addAttribute("lists", commentDao.findByDataId(dataId));
        return new ModelAndView("comment");
    }
}
