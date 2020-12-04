package com.yc.travel.admin.dao.Impl;

import com.yc.travel.admin.dao.MisBaseMapper;
import com.yc.travel.admin.entity.admin;
import org.apache.ibatis.annotations.Mapper;
@Mapper  //具体操作某张表的mapper
public interface adminMapper extends MisBaseMapper<admin> {
}
