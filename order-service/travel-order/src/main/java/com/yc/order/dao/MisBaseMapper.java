package com.yc.order.dao;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ExampleMapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface MisBaseMapper<A> extends BaseMapper<A>, ExampleMapper<A>, MySqlMapper {
}
