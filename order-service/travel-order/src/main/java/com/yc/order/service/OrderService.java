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
}
