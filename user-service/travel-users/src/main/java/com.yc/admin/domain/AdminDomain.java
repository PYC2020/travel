package com.yc.admin.domain;

import lombok.Data;

@Data
public class AdminDomain extends PageDomain {
    private Integer uid;
    private String uname;
    private String pwd;
    private String tel;

    public AdminDomain(Integer uid,String uname,String pwd,String tel){
        this.uid=uid;
        this.uname=uname;
        this.pwd=pwd;
        this.tel=tel;
    }

}
