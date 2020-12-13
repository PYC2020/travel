package com.yc.type.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.type.domain.PageDomain;
import com.yc.pics.util.CommonUtils;
import com.yc.type.dao.Impl.typeMapper;
import com.yc.type.domain.TypeDomain;
import com.yc.type.entity.type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TypeServiceImpl implements TypeService {


    @Autowired(required = false)//手动设置false，bean不存在时注入不会失败
    private typeMapper tm;


    @Transactional(readOnly = true)//在将事务设置成只读后，相当于将数据库设置成只读数据库，此时若要进行写的操作，会出现错误
    @Override
    public List<TypeDomain> list(){
        List<type>list=tm.selectAll();
        List<TypeDomain> r = new ArrayList<>();
        for (type t : list) {   //  BeanUtils.copyBean(source, destination);
            TypeDomain td = new TypeDomain(t.getTno(),t.getTname(),t.getTpic(),t.getStatus());
            r.add(td);
        }
        return r;
    }



    @Transactional(readOnly = true)
    @Override
    public PageDomain<TypeDomain> listByPage(TypeDomain typeDomain) {
        Example example = new Example(type.class);   //条件
        //分页条件设置
        PageHelper.startPage(typeDomain.getPage(), typeDomain.getPageSize());
        //排序条件
        example.setOrderByClause("id desc");
        //  Criteria: 查询的规则
        Example.Criteria c = example.createCriteria();
        if (CommonUtils.isNotNull(typeDomain.getTname())) {
            //条件创建    where 1=1 and description like '%xx%';
            c.andLike("description", "%" + typeDomain.getTname() + "%");
        }
        // PageInfo: 分页的结果   总记录数，第几页，每页多少条条，上一页，下一页， 总共多少页.
        PageInfo<type> pageInfo = new PageInfo<type>(tm.selectByExample(example));


        PageDomain<TypeDomain> pageDomain = new PageDomain<TypeDomain>();
        pageDomain.setTotal(pageInfo.getTotal());
        pageDomain.setPage(pageInfo.getPageNum());
        pageDomain.setPageSize(typeDomain.getPageSize());
        //List<Pic> list = picMapper.selectByExample(example);
        List<TypeDomain> r = new ArrayList<TypeDomain>();
        //从pageInfo中取记录数
        if (pageInfo.getList() != null) {
            for (type t : pageInfo.getList()) {
                TypeDomain pd = new TypeDomain(t.getTno(),t.getTname(),t.getTpic(),t.getStatus());
                r.add(pd);
            }
        }
        pageDomain.setData(r);
        return pageDomain;
    }


    @Override
    public void save(TypeDomain typeDomain) {
        type t = new type();
        t.setTno(typeDomain.getTno());
        t.setTname(typeDomain.getTname());
        this.tm.insert(t);
        // 在这里  mybatis完成了两步操作: 1. insert   2. select 到最新的id后，存到pic中
        //pic中的id已经获取到
        //关键:
        typeDomain.setTno(t.getTno());
    }


    @Transactional(readOnly = true)
    @Override
    public TypeDomain findOne(Integer id) {
        type t = this.tm.selectByPrimaryKey(id);
        TypeDomain typeDomain = new TypeDomain(t.getTno(),t.getTname(),t.getTpic(),t.getStatus());
        return typeDomain;
    }

    @Override
    public void delete(Integer id) {
        this.tm.deleteByPrimaryKey(id);
    }
}
