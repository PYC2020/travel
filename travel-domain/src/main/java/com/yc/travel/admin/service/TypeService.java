package com.yc.travel.admin.service;

import com.yc.travel.admin.domain.TypeDomain;

import java.util.List;

public interface TypeService {
    /**
     * 查询所有admin
     * @return
     */
    public List<TypeDomain> list();
}
