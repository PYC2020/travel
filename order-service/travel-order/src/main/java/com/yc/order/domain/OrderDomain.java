package com.yc.order.domain;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDomain  extends PageDomain{

    private Integer oid;
    private Integer uid;
    private Integer pid;
    private String odate;
    private Integer status;
    private  String sdate;
    private  String edate;
    private Integer num;

    public OrderDomain(Integer oid,Integer uid,Integer pid,String odate,
                            Integer status,String sdate,String edate,Integer num){
        this.oid=oid;
        this.uid=uid;
        this.pid=pid;
        this.odate=odate;
        this.edate=edate;
        this.status=status;
        this.sdate=sdate;
        this.num=num;
    }
    
    public OrderDomain(){}
}
