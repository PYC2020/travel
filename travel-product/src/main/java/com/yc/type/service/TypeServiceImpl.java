package com.yc.type.service;

import com.yc.product.domain.ProductDomain;
import com.yc.product.entity.product;
import com.yc.type.dao.Impl.typeMapper;
import com.yc.type.domain.TypeDomain;
import com.yc.type.entity.type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TypeServiceImpl implements TypeService {
    @Autowired(required = false)//手动设置false，bean不存在时注入不会失败
    private typeMapper tm;
    @Transactional(readOnly = true)//在将事务设置成只读后，相当于将数据库设置成只读数据库，此时若要进行写的操作，会出现错误
    @Override
    public List<TypeDomain> list(){
        List<type>list=tm.selectAll();
        List<TypeDomain> r = new ArrayList<>();
        for (type t : list) {   //  BeanUtils.copyBean(source, destination);
            TypeDomain td = new TypeDomain(t.getTno(),t.getTname(),t.getTpic(),t.getStatus());
            r.add(td);
        }
        return r;
    }


    @Transactional(readOnly = true)
    @Override
    public TypeDomain findOne(Integer id) {
        type t = this.tm.selectByPrimaryKey(id);
        TypeDomain typeDomain = new TypeDomain(t.getTno(),t.getTname(),t.getTpic(),t.getStatus());
        return typeDomain;
    }
}
