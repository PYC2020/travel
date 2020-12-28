package com.yc.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data      //  lombok 注解，节省getter, setter
@Table(name = "user")
//会生成一个包含所有变量，同时如果变量使用了NotNull annotation ， 会进行是否为空的校验，
//全部参数的构造函数的自动生成，该注解的作用域也是只有在实体类上，参数的顺序与属性定义的顺序一致。
@AllArgsConstructor
//无参构造函数
@NoArgsConstructor
public class admin {
    @Id  // JPA注解,指定此属性为表中的主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键由数据库自动生成
    private Integer uid;
    private String uname;
    private String pwd;
    private String tel;
}
