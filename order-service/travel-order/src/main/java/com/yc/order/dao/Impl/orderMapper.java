package com.yc.order.dao.Impl;

import com.yc.order.dao.MisBaseMapper;
import com.yc.order.entity.orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface orderMapper extends MisBaseMapper<orders> {

    @Select("select oid,uid,o.pid,odate,status,sdate,edate,num,pname,tno,price,intro,balance,company,pic from orders o LEFT join product p on p.pid=o.pid where 1=1")
    List findByTwo();
}
