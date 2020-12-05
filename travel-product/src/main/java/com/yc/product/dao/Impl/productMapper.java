package com.yc.product.dao.Impl;

import com.yc.product.dao.MisBaseMapper;
import com.yc.product.entity.product;
import org.apache.ibatis.annotations.Mapper;

@Mapper  //具体操作某张表的mapper
public interface productMapper extends MisBaseMapper<product> {
}
