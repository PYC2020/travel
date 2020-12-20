package com.yc.order.service;

import com.yc.order.domain.OrderDomain;

import java.util.List;

public interface OrderService {

    public List<OrderDomain> list();

    public OrderDomain findOne(Integer id);
}
