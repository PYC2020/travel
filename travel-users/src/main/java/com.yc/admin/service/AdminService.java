package com.yc.admin.service;


import com.yc.admin.domain.AdminDomain;

import java.util.List;

public interface AdminService {
    /**
     * 查询所有admin
     * @return
     */
    public List<AdminDomain> list();
}
