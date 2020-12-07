package com.yc.pics.service;


import com.yc.pics.dao.Impl.picsMapper;
import com.yc.pics.domain.PicsDomain;
import com.yc.pics.entity.pics;
import com.yc.product.domain.ProductDomain;
import com.yc.product.entity.product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PicsServiceImpl implements PicsService {
    @Autowired(required = false)//手动设置false，bean不存在时注入不会失败
    private picsMapper pm;
    @Transactional(readOnly = true)//在将事务设置成只读后，相当于将数据库设置成只读数据库，此时若要进行写的操作，会出现错误
    @Override
    public List<PicsDomain> list(){
        List<pics>list=pm.selectAll();
        List<PicsDomain> r = new ArrayList<>();
        for (pics p : list) {   //  BeanUtils.copyBean(source, destination);
            PicsDomain pd = new PicsDomain(p.getPicid(),p.getPid(), p.getPicpath());
            r.add(pd);
        }
        return r;
    }


    @Transactional(readOnly = true)
    @Override
    public PicsDomain findOne(Integer id) {
        pics p = this.pm.selectByPrimaryKey(id);
        PicsDomain picsDomain = new PicsDomain(p.getPid(),p.getPicid(),p.getPicpath());
        return picsDomain;
    }
}
