package com.wlgzs.huipeasant.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date uploadTime;                         //上传时间
    private String pictureAddress;                  //图片地址
    private long  moduleId;                         // 模块id
    private String moduleName;                      //模块名字
    private String contentsTitle;                   //文章、问题标题
    private int identity;                           //数据标识
    private int moduleLevel;                        //数据分类
    private long hits;                               //点击量
}
