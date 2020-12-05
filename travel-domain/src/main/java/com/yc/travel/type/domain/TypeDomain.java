package com.yc.travel.type.domain;

import com.yc.travel.pics.domain.PageDomain;
import lombok.Data;

@Data
public class TypeDomain extends PageDomain {
    private Integer tno;
    private String tname;
    private String tpic;
    private Integer status;

    public TypeDomain(Integer tno, String tname, String tpic,Integer status){
        this.tno=tno;
        this.tname=tname;
        this.tpic=tpic;
        this.status=status;
    }

}
