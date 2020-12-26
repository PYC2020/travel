package com.yc.product.service;


import com.yc.product.domain.PageDomain;
import com.yc.product.domain.ProductDomain;

import java.util.List;

public interface ProductService {
    public List<ProductDomain> list();

    public PageDomain<ProductDomain> listByPage(ProductDomain productDomain);

    public List findBy(ProductDomain productDomain);

    public void save(ProductDomain productDomain);

    public void delete(Integer id);



}
