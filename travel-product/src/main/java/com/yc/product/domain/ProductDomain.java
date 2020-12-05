package com.yc.product.domain;


import lombok.Data;

@Data
public class ProductDomain extends PageDomain {
    private Integer pid;
    private String pname;
    private Integer tno;
    private Integer price;
    private String intro;
    private Integer balance;
    private String company;
    private String pic;

    public ProductDomain(Integer pid, String pname,Integer tno,Integer price,String intro,Integer balance,String company,String pic){
        this.pid=pid;
        this.pname=pname;
        this.tno=tno;
        this.price=price;
        this.intro=intro;
        this.balance=balance;
        this.company=company;
        this.pic=pic;




    }

}
