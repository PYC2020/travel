package com.yc.product.entity;


import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data      //  lombok 注解，节省getter, setter
@Table(name = "product")
public class product {

    @Id  // JPA注解,指定此属性为表中的主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键由数据库自动生成
    private Integer pid;
    private String pname;
    private Integer tno;
    private Integer price;
    private String intro;
    private Integer balance;
    private String company;
    private String pic;


}
