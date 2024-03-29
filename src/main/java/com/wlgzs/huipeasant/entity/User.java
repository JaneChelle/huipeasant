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
        private long userId;                          //用户ID
        private String  phoneNumber;                     //用户手机号
        private String password;                        //密码
        private String headPortrait;                    //头像地址
        private String nickName;                       //昵称
        private String sex;                             //性别
        private String address;                         //地区
        private String reservedInf;                     //预留信息
        private long roleId;                         //角色id
    }
