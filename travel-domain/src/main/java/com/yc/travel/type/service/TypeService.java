package com.yc.travel.type.service;

import com.yc.travel.type.domain.TypeDomain;

import java.util.List;

public interface TypeService {
    /**
     * 查询所有admin
     * @return
     */
    public List<TypeDomain> list();
}
