package com.yc.travel.admin.domain;

import lombok.Data;

@Data
public class PicsDomain extends PageDomain{
    private Integer picid;
    private Integer pid;
    private String picpath;

    public PicsDomain(Integer picid, Integer pid, String picpath){
        this.picid=picid;
        this.pid=pid;
        this.picpath=picpath;
    }

}
