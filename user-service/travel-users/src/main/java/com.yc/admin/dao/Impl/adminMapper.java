package com.yc.admin.dao.Impl;


import com.yc.admin.dao.MisBaseMapper;
import com.yc.admin.entity.admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper  //具体操作某张表的mapper
public interface adminMapper extends MisBaseMapper<admin> {
}
