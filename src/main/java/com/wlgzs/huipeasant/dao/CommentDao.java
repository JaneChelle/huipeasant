package com.wlgzs.huipeasant.dao;

import com.wlgzs.huipeasant.entity.Comment;
import com.wlgzs.huipeasant.entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentDao extends JpaRepository<Comment,Long> {
    List<Comment> findByDataId(long dataId); //获取每个问题答案的集合
}
