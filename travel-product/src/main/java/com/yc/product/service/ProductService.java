package com.yc.product.service;


import com.yc.product.domain.ProductDomain;

import java.util.List;

public interface ProductService {
    public List<ProductDomain> list();

    public ProductDomain findOne(Integer id);
}
