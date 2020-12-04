package com.yc.travel.admin.domain;

import lombok.Data;

import java.util.List;

@Data
public class AdminDomain extends PageDomain{
    private Integer aid;
    private String aname;
    private String pwd;

    public AdminDomain(Integer aid,String aname,String pwd){
        this.aid=aid;
        this.aname=aname;
        this.pwd=pwd;
    }
    public AdminDomain(){

    }
}
