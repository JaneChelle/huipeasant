package com.wlgzs.huipeasant.service;

import com.wlgzs.huipeasant.entity.User;

public interface CommentService {
    public boolean addcomment(User user, String content, long dataId);
}
