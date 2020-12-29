package com.yc.travel.order.service;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yc.order.domain.OrderDomain;
import com.yc.travel.order.client.TravelOrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TravelOrderRestService {
    @Autowired
    private TravelOrderClient travelOrderClient;
    //@HystrixCommand(fallbackMethod = "findByPidFallback")
    public String findByPid(Integer pid) {
        System.out.println("pid"+pid);
        return travelOrderClient.findByPid(pid);
    }

    private String findByPidFallback(Integer pid) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }
    @HystrixCommand(fallbackMethod = "findByIdFallback")
    public String findById(Integer id) {
        return travelOrderClient.findById(id);
    }

    private String findByIdFallback(Integer id) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "findAllFallback")
    public String findAll(Integer page, Integer limit) {
        return travelOrderClient.findAll(page, limit);
    }

    private String findAllFallback(Integer page, Integer limit) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "createFallback")
    public String create(OrderDomain orderDomain) {

        return travelOrderClient.create(orderDomain);
    }

    private String createFallback(OrderDomain orderDomain) {

        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常，无法添加" + orderDomain.getOid());
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "deleteFallback")
    public String delete(Integer id) {
        return travelOrderClient.delete(id);
    }

    private String deleteFallback(Integer id) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常，无法删除" + id);
        return new Gson().toJson(map);
    }
}
