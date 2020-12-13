package com.yc.type.service;


import com.yc.type.domain.PageDomain;
import com.yc.type.domain.TypeDomain;

import java.util.List;

public interface TypeService {

    public List<TypeDomain> list();

    public PageDomain<TypeDomain> listByPage(TypeDomain typeDomain);

    public void save(TypeDomain typeDomain);


    public void delete(Integer id);

    public TypeDomain findOne(Integer id);
}
