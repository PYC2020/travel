package com.yc.travel.product.service;

import com.yc.travel.product.dao.Impl.productMapper;
import com.yc.travel.product.domain.ProductDomain;
import com.yc.travel.product.entity.product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired(required = false)//手动设置false，bean不存在时注入不会失败
    private productMapper pm;
    @Transactional(readOnly = true)//在将事务设置成只读后，相当于将数据库设置成只读数据库，此时若要进行写的操作，会出现错误
    @Override
    public List<ProductDomain> list(){
        List<product>list=pm.selectAll();
        List<ProductDomain> r = new ArrayList<>();
        for (product a : list) {   //  BeanUtils.copyBean(source, destination);
            ProductDomain pd = new ProductDomain(a.getPid(), a.getPname(),
                    a.getTno(),a.getPrice(),a.getIntro(),a.getBalance(),a.getCompany(),a.getPic());
            r.add(pd);
        }
        return r;
    }
}
