package com.yc.travel.admin.service;

import com.yc.travel.admin.domain.AdminDomain;

import java.util.List;

public interface AdminService {
    /**
     * 查询所有admin
     * @return
     */
    public List<AdminDomain> list();
}
