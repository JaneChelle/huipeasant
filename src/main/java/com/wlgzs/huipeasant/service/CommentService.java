package com.wlgzs.huipeasant.service;

import com.wlgzs.huipeasant.entity.Comment;
import com.wlgzs.huipeasant.entity.User;

import java.util.List;
import java.util.Map;

public interface CommentService {
    boolean addcomment(User user, String content, long dataId);
    Map<User,Comment> getanswer(long userId);

}
