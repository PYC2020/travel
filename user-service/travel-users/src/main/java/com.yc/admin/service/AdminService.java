package com.yc.admin.service;


import com.yc.admin.domain.AdminDomain;
import com.yc.admin.domain.PageDomain;

import java.util.List;

public interface AdminService {
    /**
     * 查询所有admin
     * @return
     */
    public List<AdminDomain> list();

    public AdminDomain findOne(Integer id);

   // public PageDomain<AdminDomain> listByPage(AdminDomain adminDomain);
}
