package com.wlgzs.huipeasant.dao;

import com.wlgzs.huipeasant.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment,Long> {

}
