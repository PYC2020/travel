package com.yc.travel.pics.service;

import com.yc.travel.pics.domain.PicsDomain;

import java.util.List;

public interface PicsService {
    /**
     * 查询所有admin
     * @return
     */
    public List<PicsDomain> list();
}
