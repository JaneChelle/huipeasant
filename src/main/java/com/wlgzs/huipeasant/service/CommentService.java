package com.wlgzs.huipeasant.service;

import com.wlgzs.huipeasant.entity.Comment;
import com.wlgzs.huipeasant.entity.User;

import java.util.List;

public interface CommentService {
    boolean addcomment(User user, String content, long dataId);
    List<Comment> getanswer(long userId);

}
