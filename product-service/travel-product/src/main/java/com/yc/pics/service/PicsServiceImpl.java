package com.yc.pics.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.pics.dao.Impl.picsMapper;
import com.yc.pics.domain.PicsDomain;
import com.yc.pics.domain.PageDomain;
import com.yc.pics.entity.pics;
import com.yc.pics.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

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
    public PageDomain<PicsDomain> listByPage(PicsDomain picDomain) {
        Example example = new Example(pics.class);   //条件
        //分页条件设置
        PageHelper.startPage(picDomain.getPage(), picDomain.getPageSize());
        //排序条件
        example.setOrderByClause("picid desc");
        //  Criteria: 查询的规则
        Example.Criteria c = example.createCriteria();
//        if (CommonUtils.isNotNull(picDomain.getPicpath())) {
//            //条件创建    where 1=1 and description like '%xx%';
//            c.andLike("picpath", "%" + picDomain.getPicpath() + "%");
//        }
        // PageInfo: 分页的结果   总记录数，第几页，每页多少条条，上一页，下一页， 总共多少页.
        PageInfo<pics> pageInfo = new PageInfo<pics>(pm.selectByExample(example));


        PageDomain<PicsDomain> pageDomain = new PageDomain<PicsDomain>();
        pageDomain.setTotal(pageInfo.getTotal());
        pageDomain.setPage(pageInfo.getPageNum());
        pageDomain.setPageSize(picDomain.getPageSize());
        //List<Pic> list = picMapper.selectByExample(example);
        List<PicsDomain> r = new ArrayList<PicsDomain>();
        //从pageInfo中取记录数
        if (pageInfo.getList() != null) {
            for (pics p : pageInfo.getList()) {
                PicsDomain pd = new PicsDomain(p.getPid(), p.getPicid(), p.getPicpath());
                r.add(pd);
            }
        }
        pageDomain.setData(r);
        return pageDomain;
    }


    @Override
    public void save(PicsDomain picDomain) {
        pics p = new pics();
        p.setPicpath(picDomain.getPicpath());
        p.setPid(picDomain.getPid());
        this.pm.insert(p);
        // 在这里  mybatis完成了两步操作: 1. insert   2. select 到最新的id后，存到pic中
        //pic中的id已经获取到
        //关键:
        picDomain.setPicid(p.getPicid());
    }


    @Transactional(readOnly = true)
    @Override
    public List findOne(Integer pid) {

        Example example = new Example(pics.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("pid",pid);
        List<pics> p=  this.pm.selectByExample(example);
        return  p;
    }

    /*pics p = new pics();
        p.setPid(pid);
        List<pics> select = this.pm.select(p);*/

    @Override
    public void delete(Integer id) {
        this.pm.deleteByPrimaryKey(id);
    }
}
