package com.yc.travel.admin.service;

import com.yc.travel.admin.domain.ProductDomain;

import java.util.List;

public interface ProductService {
    /**
     * 查询所有admin
     * @return
     */
    public List<ProductDomain> list();
}
