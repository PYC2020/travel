package com.yc.order.entity;


import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data      //  lombok 注解，节省getter, setter
@Table(name = "orders")
public class orders {

    @Id  // JPA注解,指定此属性为表中的主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键由数据库自动生成
    private Integer oid;
    private Integer uid;
    private Integer pid;
    private Date odate;
    private Integer status;
    private  Date sdate;
    private  Date edate;
    private Integer num;
}
