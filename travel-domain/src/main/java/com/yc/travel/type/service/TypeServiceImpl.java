package com.yc.travel.type.service;

import com.yc.travel.type.dao.Impl.typeMapper;
import com.yc.travel.type.domain.TypeDomain;
import com.yc.travel.type.entity.type;
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
}
