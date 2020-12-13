package com.yc.admin.service;

import com.yc.admin.dao.Impl.adminMapper;
import com.yc.admin.domain.AdminDomain;
import com.yc.admin.entity.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired(required = false)//手动设置false，bean不存在时注入不会失败
    private adminMapper am;
    @Transactional(readOnly = true)//在将事务设置成只读后，相当于将数据库设置成只读数据库，此时若要进行写的操作，会出现错误
    @Override
    public List<AdminDomain> list(){
        List<admin>list=am.selectAll();
        List<AdminDomain> r = new ArrayList<>();
        for (admin a : list) {   //  BeanUtils.copyBean(source, destination);
            AdminDomain ad = new AdminDomain(a.getAid(), a.getAname(), a.getPwd());
            r.add(ad);
        }
        return r;
    }
}
