package com.yc.travel.type.service;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yc.type.domain.TypeDomain;
import com.yc.travel.type.client.TravelTypeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

//Hystrix服务层:  调用 PiclibClient,实现断路器功能
@Service
public class TravelTypeRestService {
    @Autowired
    private TravelTypeClient travelTypeClient;


    @HystrixCommand(fallbackMethod = "findByIdFallback")
    public String findById(Integer id) {
        return travelTypeClient.findById(id);
    }

    private String findByIdFallback(Integer id) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "findAllFallback")
    public String findAll(Integer page, Integer pageSize,
                          String description) {
        return travelTypeClient.findAll(page, pageSize, description);
    }

    private String findAllFallback(Integer page, Integer pageSize,
                                   String description) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "createFallback")
    public String create(TypeDomain typeDomain) {
        return travelTypeClient.create(typeDomain);
    }

    private String createFallback(TypeDomain typeDomain) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常，无法添加" + typeDomain.getTname());
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "deleteFallback")
    public String delete(Integer id) {
        return travelTypeClient.delete(id);
    }

    private String deleteFallback(Integer id) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常，无法删除" + id);
        return new Gson().toJson(map);
    }


}