package com.wlgzs.huipeasant.service.impl;

import com.wlgzs.huipeasant.dao.CommentDao;
import com.wlgzs.huipeasant.entity.Comment;
import com.wlgzs.huipeasant.entity.User;
import com.wlgzs.huipeasant.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
        commentDao.save(comment);
        return true;
    }
}
