package com.yc.type.service;


import com.yc.product.domain.ProductDomain;
import com.yc.type.domain.TypeDomain;

import java.util.List;

public interface TypeService {

    public List<TypeDomain> list();

    public TypeDomain findOne(Integer id);
}
