package com.wlgzs.huipeasant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_module")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Module implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mouldeId;                         //模块ID
    private String moduleName;                        //模块名字
    private Integer moduleLevel;                      //模块等级

}
