package com.yc.pics.service;


import com.yc.pics.domain.PicsDomain;

import java.util.List;

public interface PicsService {
    /**
     * 查询所有admin
     * @return
     */
    public List<PicsDomain> list();
}
