package com.yc.travel.orderapi.controllers;


import com.google.gson.Gson;
import com.yc.order.domain.OrderDomain;
import com.yc.order.domain.PageDomain;
import com.yc.order.service.OrderService;
import com.yc.order.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/travel/order")
public class OrderController {
    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    //@Resource
    @Autowired
    private OrderService orderService;
    @RequestMapping(value = "/findByPid/{pid}",method = RequestMethod.POST)
    public CompletableFuture<String> findByPid(@PathVariable Integer pid) {
        //非阻塞式异步编程方法。因为在web ui的微服务对rest api的调用中将使用这种高并发的编程方法，所以为了保证与调用端保持同步，这里也使用这种方法.
        return CompletableFuture.supplyAsync(() -> {
            List order = orderService.findBypid(pid);
            //协议
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("data", order);
            return new Gson().toJson(map);
        });
    }
    @RequestMapping(value = "/findByUid/{uid}",method = RequestMethod.POST)
    public CompletableFuture<String> findByUid(@PathVariable Integer uid) {
        return CompletableFuture.supplyAsync(() -> {
            List order = orderService.findByUid(uid);
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("data", order);
            return new Gson().toJson(map);
        });
    }

    @RequestMapping(value = "/{id}")
    public CompletableFuture<String> findById(@PathVariable Integer id) {
        //非阻塞式异步编程方法。因为在web ui的微服务对rest api的调用中将使用这种高并发的编程方法，所以为了保证与调用端保持同步，这里也使用这种方法.
        return CompletableFuture.supplyAsync(() -> {
            OrderDomain order = orderService.findOne(id);
            //协议
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("data", order);
            return new Gson().toJson(map);
        });
    }
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public CompletableFuture<String> findAll(Integer page, Integer limit) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                OrderDomain orderDomain=new OrderDomain();
                if (CommonUtils.isNotNull(page)) {
                    orderDomain.setPage(page);
                }
                if (CommonUtils.isNotNull(limit)) {
                    orderDomain.setLimit(limit);
                }
                PageDomain<OrderDomain> pageDomain = orderService.listByPage(orderDomain);
                return new Gson().toJson(pageDomain);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public CompletableFuture<String> find(Integer page, Integer limit) {
        return CompletableFuture.supplyAsync(() -> {

            List list = orderService.count();
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("data", list);
            return new Gson().toJson(map);

        });
    }
}
