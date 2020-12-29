package com.yc.order.domain;

import lombok.Data;

import java.util.List;

@Data
public class PageDomain<A> {
    private Integer page = 1;   //当前第几页
    private Integer limit = 2;  //每页多少条
    private Long total = 0L;  //总记录数

    private List<A> data;   //当前查询出来的数据集
    private Integer TotalPages;

}
