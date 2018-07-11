package com.wlgzs.huipeasant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_comment")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;                         //评论ID
    private Integer dataId;                        //资料ID
    private Integer userId;                        //用户ID
    private String userName;                        //用户名
    private String commentContent;                  //评论内容
    private Date   commentDate;                     //评论时间
}
