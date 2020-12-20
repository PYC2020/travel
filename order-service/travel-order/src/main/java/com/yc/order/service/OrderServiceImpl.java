package com.yc.order.service;

import com.yc.order.dao.Impl.orderMapper;
import com.yc.order.domain.OrderDomain;
import  com.yc.order.entity.orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public OrderDomain findOne(Integer id) {
        orders o = this.om.selectByPrimaryKey(id);
        OrderDomain orderDomain= new OrderDomain(o.getOid(),o.getUid(),o.getPid(),o.getOdate(),o.getStatus(),o.getSdate(),
                o.getEdate(),o.getNum());
        return orderDomain;
    }
}
