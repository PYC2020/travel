package com.yc.travel.admin.service;

import com.yc.travel.admin.domain.PicsDomain;

import java.util.List;

public interface PicsService {
    /**
     * 查询所有admin
     * @return
     */
    public List<PicsDomain> list();
}
