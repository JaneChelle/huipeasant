package com.wlgzs.huipeasant.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_data")
@Entity
@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class Data implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dataId;                         //文章/问题ID
    private long userId;                         //用户id
    private String contents;                        //文章内容
    private String pictureAddress;                  //图片地址
    private long  moduleId;                      // 模块id
    private String moduleName;                      //模块名字
    private String contentsTitle;                   //文章、问题标题
}
