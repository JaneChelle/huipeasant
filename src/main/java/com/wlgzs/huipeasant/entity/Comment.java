package com.wlgzs.huipeasant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_comment")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;                         //评论ID
    private long dataId;                        //资料ID
    private long userId;                        //用户ID
    private String userName;                        //用户名
    private String commentContent;                  //评论内容
    private Date   commentDate;                     //评论时间
    private String userIcon;                        //用户头像地址
}
