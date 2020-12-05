package com.yc.travel.pics.service;

import com.yc.travel.pics.domain.PicsDomain;
import com.yc.travel.pics.dao.Impl.picsMapper;
import com.yc.travel.pics.entity.pics;
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
}
