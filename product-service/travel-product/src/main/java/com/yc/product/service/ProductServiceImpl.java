package com.yc.product.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.product.domain.PageDomain;
import com.yc.pics.util.CommonUtils;
import com.yc.product.dao.Impl.productMapper;
import com.yc.product.domain.ProductDomain;
import com.yc.product.entity.product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {


    @Autowired(required = false)//手动设置false，bean不存在时注入不会失败
    private productMapper pm;


    @Transactional(readOnly = true)//在将事务设置成只读后，相当于将数据库设置成只读数据库，此时若要进行写的操作，会出现错误
    @Override
    public List<ProductDomain> list(){
        List<product>list=pm.selectAll();
        List<ProductDomain> r = new ArrayList<>();
        for (product a : list) {   //  BeanUtils.copyBean(source, destination);
            ProductDomain pd = new ProductDomain(a.getPid(), a.getPname(),
                    a.getTno(),a.getPrice(),a.getIntro(),a.getBalance(),a.getCompany(),a.getPic());
            r.add(pd);
        }
        return r;
    }


    @Transactional(readOnly = true)
    @Override
    public PageDomain<ProductDomain> listByPage(ProductDomain productDomain) {
        //mybatis的逆向工程中会生成实例及实例对应的example，example用于添加条件，相当where后面的部分
        Example example = new Example(product.class);   //条件
        //分页条件设置
        PageHelper.startPage(productDomain.getPage(), productDomain.getPageSize());
        //排序条件


        example.setOrderByClause("tno asc,pid desc");
        //  Criteria: 查询的规则
        Example.Criteria c = example.createCriteria();
        // PageInfo: 分页的结果   总记录数，第几页，每页多少条条，上一页，下一页， 总共多少页.
        PageInfo<product> pageInfo = new PageInfo<product>(pm.selectByExample(example));


        PageDomain<ProductDomain> pageDomain = new PageDomain<ProductDomain>();
        pageDomain.setTotal(pageInfo.getTotal());
        pageDomain.setPage(pageInfo.getPageNum());
        pageDomain.setPageSize(productDomain.getPageSize());
        //List<Pic> list = picMapper.selectByExample(example);
        List<ProductDomain> r = new ArrayList<ProductDomain>();
        //从pageInfo中取记录数
        if (pageInfo.getList() != null) {
            for (product p : pageInfo.getList()) {
                ProductDomain pd = new ProductDomain(p.getPid(),p.getPname(),
                        p.getTno(),p.getPrice(),p.getIntro(),p.getBalance(),p.getCompany(),p.getPic());
                r.add(pd);
            }
        }
        pageDomain.setData(r);
        return pageDomain;
    }


    @Override
    public void save(ProductDomain productDomain) {
        product p = new product();
        p.setPid(productDomain.getPid());
        p.setPname(productDomain.getPname());
        this.pm.insert(p);
        // 在这里  mybatis完成了两步操作: 1. insert   2. select 到最新的id后，存到pic中
        //pic中的id已经获取到
        //关键:
        productDomain.setPid(p.getPid());
    }

    /**
     * 按地址模糊查询产品
     * @param productDomain
     * @return
     */
    //todo
    @Transactional(readOnly = true)
    @Override
    public List findOne(ProductDomain productDomain ) {
        product p = new product();
        //mybatis的逆向工程中会生成实例及实例对应的example，example用于添加条件，相当where后面的部分
        Example example = new Example(product.class);
        example.createCriteria().andLike("pname", "%"+productDomain.getPname()+"%");
        List<product>list=  pm.selectByExample(example);
        return list;
    }


    @Override
    public void delete(Integer id) {
        this.pm.deleteByPrimaryKey(id);
    }
}
