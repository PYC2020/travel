package com.yc.pics.service;


import com.yc.pics.domain.PicsDomain;
import com.yc.pics.domain.PageDomain;

import java.util.List;

public interface PicsService {
    /**
     * 查询所有admin
     * @return
     */
    public List<PicsDomain> list();


    public PageDomain<PicsDomain> listByPage(PicsDomain PicDomain);

    public void save(PicsDomain picDomain);


    public void delete(Integer id);

    public PicsDomain findOne(Integer id);
}
