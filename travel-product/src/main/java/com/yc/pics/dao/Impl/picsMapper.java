package com.yc.pics.dao.Impl;

import com.yc.pics.dao.MisBaseMapper;
import com.yc.pics.entity.pics;
import org.apache.ibatis.annotations.Mapper;

@Mapper  //具体操作某张表的mapper
public interface picsMapper extends MisBaseMapper<pics> {
}
