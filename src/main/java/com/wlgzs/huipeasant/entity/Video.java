package com.wlgzs.huipeasant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_video")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer videoId;                         //用户ID
    private String authorSuggests;                   //作者提示
    private String videoIntroduction;                //视频简介
    private Integer videoHits;                       //视频点击量
    private Date uploadTime;                      //上传时间
    private String videoCover;                    //视频封面
    private String videoAddress;                   //视频地址
    private Integer  moduleId;                    //模块id
    private String   modelName;                    //模块名字

}
