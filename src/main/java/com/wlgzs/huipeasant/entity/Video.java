package com.wlgzs.huipeasant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_video")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Video implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long videoId;                         //视频ID
    private String authorSuggests;                   //作者提示
    private String videoIntroduction;                //视频简介
    private int videoHits;                       //视频点击量
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date uploadTime;                      //上传时间
    private String videoCover;                    //视频封面地址
    private String videoAddress;                   //视频地址
    private long moduleId;                    //模块id
    private String modelName;                    //模块名字
    private String videoTitle;                     //视频标题
    private long code=0;                             //状态码

}
