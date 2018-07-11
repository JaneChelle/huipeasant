package com.wlgzs.huipeasant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

    @Table(name = "t_user")
    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class User implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer userId;                          //用户ID
        private String  phoneNumber;                     //用户手机号
        private String password;                        //密码
        private String headPortrait;                    //头像地址
        private String  nickName;                       // 昵称
        private Integer roleId;                         //角色id

    }
