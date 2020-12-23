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

    //根据pname查询相关产品
    @HystrixCommand(fallbackMethod = "findByIdFallback")
    public String findBypid(@PathVariable Integer pid) {
        return travelproductClient.findByPid(pid);
    }

    private String findByIdFallback(Integer pid) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "查询产品信息异常");
        return new Gson().toJson(map);
    }





    //根据pname查询相关产品
    @HystrixCommand(fallbackMethod = "findBypnameFallback")
    public String findBypname(@PathVariable String pname) {
        return travelproductClient.findBypname(pname);
    }

    private String findBypnameFallback(String pname) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "查询产品信息异常");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "findAllFallback")
    public String findAll(Integer page, Integer pageSize  ) {
        return travelproductClient.findAll(page, pageSize);
    }

    private String findAllFallback(Integer page, Integer pageSize) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "查询产品信息异常");
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