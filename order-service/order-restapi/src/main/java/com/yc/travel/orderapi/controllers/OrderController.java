package com.yc.travel.orderapi.controllers;


import com.google.gson.Gson;
import com.yc.order.domain.OrderDomain;
import com.yc.order.domain.PageDomain;
import com.yc.order.service.OrderService;
import com.yc.order.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    @RequestMapping(value = "/findByPid/{pid}",method ={RequestMethod.GET, RequestMethod.POST})
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
    @RequestMapping(value = "/findByUid/{uid}",method ={RequestMethod.GET, RequestMethod.POST})
    public CompletableFuture<String> findByUid(@PathVariable Integer uid) {
        return CompletableFuture.supplyAsync(() -> {
            List order = orderService.findByUid(uid);
            return new Gson().toJson(order);
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

    @RequestMapping(value = "/save",method = {RequestMethod.GET,RequestMethod.POST})
    public CompletableFuture<String> save(@RequestParam("uid")  Integer uid,
                                          @RequestParam("pid")  Integer pid,
                                          @RequestParam("status")  Integer status,
                                          @RequestParam("sdate")  String sdate,
                                          @RequestParam("edate")  String edate,
                                          @RequestParam("num")  Integer num) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            OrderDomain orderDomain = new OrderDomain();
            orderDomain.setUid(uid);
            orderDomain.setPid(pid);
            orderDomain.setStatus(status);
            orderDomain.setSdate(sdate);
            orderDomain.setEdate(edate);
            orderDomain.setNum(num);
            orderService.save(orderDomain);
            logger.info("新增->ID=" + orderDomain.getOid());
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("data", orderDomain);
            return new Gson().toJson(map);

        });
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CompletableFuture<String> delete(@PathVariable Integer id) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            orderService.delete(id);
            logger.info("删除->ID=" + id);
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            return new Gson().toJson(map);
        });
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public CompletableFuture<String> update(@PathVariable Integer id) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            orderService.update(id);
            logger.info("修改->ID=" + id);
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            return new Gson().toJson(map);
        });
    }
}
