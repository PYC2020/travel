package com.yc.type.dao.Impl;

import com.yc.type.dao.MisBaseMapper;
import com.yc.type.entity.type;
import org.apache.ibatis.annotations.Mapper;

@Mapper  //具体操作某张表的mapper
public interface typeMapper extends MisBaseMapper<type> {
}
