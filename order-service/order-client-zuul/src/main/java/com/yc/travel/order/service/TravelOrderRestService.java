package com.yc.travel.order.service;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yc.order.domain.OrderDomain;
import com.yc.travel.order.client.TravelOrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TravelOrderRestService {
    @Autowired
    private TravelOrderClient travelOrderClient;


    @HystrixCommand(fallbackMethod = "findFallback")
    public String find() {
        return travelOrderClient.find();
    }
    private String findFallback() {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }
    @HystrixCommand(fallbackMethod = "findByPidFallback")
    public String findByPid(Integer pid) {
        return travelOrderClient.findByPid(pid);
    }
    @HystrixCommand(fallbackMethod = "findByUidFallback")
    public String findByUid(Integer uid) {
        return travelOrderClient.findByUid(uid);
    }
    private String findByUidFallback(Integer uid) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
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

    //@HystrixCommand(fallbackMethod = "createFallback")
    public String create(Integer uid,Integer pid,Integer status,String sdate,String edate,Integer num) {

        return travelOrderClient.create(uid, pid, status, sdate, edate, num);
    }

    private String createFallback(Integer uid,Integer pid,Integer status,String sdate,String edate,Integer num) {

        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常，无法添加");
        return new Gson().toJson(map);
    }

    //@HystrixCommand(fallbackMethod = "deleteFallback")
    public String delete(Integer id) {
        return travelOrderClient.delete(id);
    }

    private String deleteFallback(Integer id) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常，无法删除" + id);
        return new Gson().toJson(map);
    }

    //@HystrixCommand(fallbackMethod = "updateFallback")
    public String update(Integer id) {
        return travelOrderClient.update(id);
    }

    private String updateFallback(Integer id) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常，无法修改" + id);
        return new Gson().toJson(map);
    }


}
