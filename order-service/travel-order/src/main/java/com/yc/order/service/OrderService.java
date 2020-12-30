package com.yc.order.service;

import com.yc.order.domain.OrderDomain;
import com.yc.order.domain.PageDomain;

import java.util.List;

public interface OrderService {

    public List<OrderDomain> list();

    public PageDomain<OrderDomain> listByPage(OrderDomain orderDomain);

    public void save(OrderDomain orderDomain);

    public void delete(Integer id);

    public OrderDomain findOne(Integer id);

    //根据pid查订单
    public List findBypid(Integer pid);
    //根据uid查订单
    public List findByUid(Integer id);
    //查后台下单人数
    public int findByren(Integer pid);
    public List count();


}
