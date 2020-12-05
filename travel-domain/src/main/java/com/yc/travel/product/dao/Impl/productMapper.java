package com.yc.travel.product.dao.Impl;

import com.yc.travel.admin.dao.MisBaseMapper;
import com.yc.travel.product.entity.product;
import org.apache.ibatis.annotations.Mapper;

@Mapper  //具体操作某张表的mapper
public interface productMapper extends MisBaseMapper<product> {
}
