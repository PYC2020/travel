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

    public PageDomain<AdminDomain> listByPage(AdminDomain adminDomain);

    public void save(AdminDomain adminDomain);

    public void delete(Integer id);

    public List findOne(AdminDomain adminDomain);
    //session登陆
    public AdminDomain login(AdminDomain adminDomain);

}
