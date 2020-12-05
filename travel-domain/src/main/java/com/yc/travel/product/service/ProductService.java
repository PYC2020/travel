package com.yc.travel.product.service;

import com.yc.travel.product.domain.ProductDomain;

import java.util.List;

public interface ProductService {
    /**
     * 查询所有admin
     * @return
     */
    public List<ProductDomain> list();
}
