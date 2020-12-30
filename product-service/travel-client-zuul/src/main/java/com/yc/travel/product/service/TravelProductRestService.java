package com.yc.travel.product.service;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yc.travel.product.client.TravelProductClient;
import com.yc.product.domain.ProductDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

//Hystrix服务层:  调用 PiclibClient,实现断路器功能
@Service
public class TravelProductRestService {
    @Autowired
    private TravelProductClient travelproductClient;

    //findBy(),String pname,Integer pid,Integer tno pname,pid,tno
    @HystrixCommand(fallbackMethod = "findByFallback")
    public String findBy(String pname,Integer pid,Integer tno) {
        return travelproductClient.findBy(pname,pid,tno);
    }

    private String findByFallback(String pname,Integer pid,Integer tno) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "查询产品信息异常test1");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "findAllFallback")
    public String findAll(Integer page, Integer limit  ) {
        return travelproductClient.findAll(page, limit);
    }

    private String findAllFallback(Integer page, Integer limit) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "查询产品信息异常test5");
        return new Gson().toJson(map);
    }

    //@HystrixCommand(fallbackMethod = "createFallback")
    public String create(String pname,Integer tno,Integer price,String intro,Integer balance,String company, String pic){
        return travelproductClient.create(pname, tno, price, intro, balance, company, pic);
    }

    private String createFallback( String pname,Integer tno,Integer price,String intro,Integer balance,String company, String pic) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常，无法添加" + pname);
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "deleteFallback")
    public String delete(Integer id) {
        return travelproductClient.delete(id);
    }

    private String deleteFallback(Integer id) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常，无法删除" + id);
        return new Gson().toJson(map);
    }

    //@HystrixCommand(fallbackMethod = "findByIdFallback")
    public String findById(Integer id) {
        return travelproductClient.findById(id);
    }

    private String findByIdFallback(Integer id) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }


}