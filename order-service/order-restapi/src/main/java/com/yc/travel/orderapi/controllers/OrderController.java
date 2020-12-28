package com.yc.travel.orderapi.controllers;


import com.google.gson.Gson;
import com.yc.order.domain.OrderDomain;
import com.yc.order.service.OrderService;
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
    @RequestMapping(value = "/findByPid/{pid}")
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
    public CompletableFuture<String> findAll(Integer page, Integer pageSize) {
        return CompletableFuture.supplyAsync(() -> {
            OrderDomain orderDomain = new OrderDomain();
            List<OrderDomain> list= orderService.list();

            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("data", list);
            return new Gson().toJson(map);
        });
    }
}
