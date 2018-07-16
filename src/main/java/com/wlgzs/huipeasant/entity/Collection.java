package com.wlgzs.huipeasant.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "t_collection")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long collectionId;//收藏id
    private long userId;//用户id
    private long videoId;//视频id
    private String modelTitle;//视频标题
    private String videoCover;//视频图片
}
