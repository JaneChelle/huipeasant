package com.wlgzs.huipeasant.service.impl;

import com.wlgzs.huipeasant.dao.CommentDao;
import com.wlgzs.huipeasant.entity.Comment;
import com.wlgzs.huipeasant.entity.Data;
import com.wlgzs.huipeasant.entity.User;
import com.wlgzs.huipeasant.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDao;
    @Override
    public boolean addcomment(User user,String content,long dataId){
        Date date =new Date();
        Comment comment = new Comment();
        comment.setCommentContent(content);
        comment.setDataId(dataId);
        comment.setUserId(user.getUserId());
        comment.setCommentDate(date);
        comment.setUserName(user.getNickName());
        comment.setUserIcon(user.getHeadPortrait());
        commentDao.save(comment);
        return true;
    }
    @Override
    public List<Comment> getanswer(long userId){      //点击每个问题进入每个问题详情获得所有答案
        List<Comment> commentList = commentDao.findByDataId(userId);
        return commentList;
    }
    }
