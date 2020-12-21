package com.yc.order.domain;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDomain  extends PageDomain{

    private Integer oid;
    private Integer uid;
    private Integer pid;
    private Date odate;
    private Integer status;
    private  Date sdate;
    private  Date edate;
    private Integer num;

    public OrderDomain(Integer oid,Integer uid,Integer pid,Date odate,
                            Integer status,Date sdate,Date edate,Integer num){
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
