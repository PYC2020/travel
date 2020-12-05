package com.yc.travel.type.dao.Impl;

import com.yc.travel.type.dao.MisBaseMapper;
import com.yc.travel.type.entity.type;
import org.apache.ibatis.annotations.Mapper;

@Mapper  //具体操作某张表的mapper
public interface typeMapper extends MisBaseMapper<type> {
}
