package com.yc.travel.product.service;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yc.travel.product.client.TravelProductClient;
import com.yc.product.domain.ProductDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String findAll(Integer page, Integer pageSize  ) {
        return travelproductClient.findAll(page, pageSize);
    }

    private String findAllFallback(Integer page, Integer pageSize) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "查询产品信息异常test5");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "createFallback")
    public String create(ProductDomain productDomain) {
        return travelproductClient.create(productDomain);
    }

    private String createFallback(ProductDomain productDomain) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常，无法添加" + productDomain.getPname());
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


}