package com.yc.order.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.order.dao.Impl.orderMapper;
import com.yc.order.domain.OrderDomain;
import com.yc.order.domain.PageDomain;
import  com.yc.order.entity.orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

    @Autowired(required = false)//手动设置false，bean不存在时注入不会失败
    private orderMapper om;


    @Transactional(readOnly = true)//在将事务设置成只读后，相当于将数据库设置成只读数据库，此时若要进行写的操作，会出现错误
    @Override
    public List<OrderDomain> list(){
        List<orders>list=om.selectAll();
        List<OrderDomain> r = new ArrayList<>();
        for (orders o : list) {   //  BeanUtils.copyBean(source, destination);
            OrderDomain od = new OrderDomain(o.getOid(),o.getUid(),o.getPid(),o.getOdate(),o.getStatus(),o.getSdate(),
                                o.getEdate(),o.getNum());
            r.add(od);
        }
        return r;
    }

    @Transactional(readOnly = true)
    @Override
    public PageDomain<OrderDomain> listByPage(OrderDomain orderDomain) {
        Example example = new Example(orders.class);   //条件
        //分页条件设置
        PageHelper.startPage(orderDomain.getPage(), orderDomain.getLimit());
        //排序条件
        example.setOrderByClause("oid asc");
        //  Criteria: 查询的规则
        Example.Criteria c = example.createCriteria();
        // PageInfo: 分页的结果   总记录数，第几页，每页多少条条，上一页，下一页， 总共多少页.
        PageInfo<orders> pageInfo = new PageInfo<orders>(om.selectByExample(example));


        PageDomain<OrderDomain> pageDomain = new PageDomain<OrderDomain>();
        pageDomain.setTotal(pageInfo.getTotal());
        pageDomain.setPage(pageInfo.getPageNum());
        pageDomain.setLimit(orderDomain.getLimit());
        //List<Pic> list = picMapper.selectByExample(example);
        List<OrderDomain> r = new ArrayList<OrderDomain>();
        //从pageInfo中取记录数
        if (pageInfo.getList() != null) {
            for (orders o : pageInfo.getList()) {
                OrderDomain od = new OrderDomain(o.getOid(),o.getUid(),o.getPid(),o.getOdate(),o.getStatus(),o.getSdate(),
                        o.getEdate(),o.getNum());
                r.add(od);
            }
        }
        pageDomain.setData(r);
        return pageDomain;
    }

    @Override
    public void save(OrderDomain orderDomain) {
        orders o = new orders();
        o.setOid(orderDomain.getOid());
        o.setOdate(orderDomain.getOdate());
        this.om.insert(o);
        // 在这里  mybatis完成了两步操作: 1. insert   2. select 到最新的id后，存到pic中
        //pic中的id已经获取到
        //关键:
        orderDomain.setOid(o.getOid());
    }

    @Override
    public void delete(Integer id) {
        this.om.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public OrderDomain findOne(Integer id) {
        orders o = this.om.selectByPrimaryKey(id);
        OrderDomain orderDomain= new OrderDomain(o.getOid(),o.getUid(),o.getPid(),o.getOdate(),o.getStatus(),o.getSdate(),
                o.getEdate(),o.getNum());
        return orderDomain;
    }
    //public OrderDomain findNum(Integer id);
    @Transactional(readOnly = true)
    @Override
    public List findBypid(Integer pid) {
        orders o = new orders();
        Example example=new Example(orders.class);
        Example.Criteria criteria=example.createCriteria();
        Example.Criteria criteria2=example.createCriteria();
        criteria.andEqualTo("pid",pid);
        criteria.andEqualTo("status",1);
        criteria2.andEqualTo("pid",pid);
        criteria2.andEqualTo("status",2);
        example.or(criteria2);

        List ordersList=om.selectByExample(example);

        return ordersList;
    }
}
