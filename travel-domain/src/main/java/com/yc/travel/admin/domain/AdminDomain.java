package com.yc.travel.admin.domain;

import com.yc.travel.pics.domain.PageDomain;
import lombok.Data;

@Data
public class AdminDomain extends PageDomain {
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
