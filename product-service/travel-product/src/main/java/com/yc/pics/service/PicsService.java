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


    public PageDomain<PicsDomain> listByPage(PicsDomain PicsDomain);

    public void save(PicsDomain picsDomain);


    public void delete(Integer id);

    public List findOne(Integer pid);
}
