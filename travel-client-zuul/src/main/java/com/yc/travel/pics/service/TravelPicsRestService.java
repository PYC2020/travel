package com.yc.travel.pics.service;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yc.pics.domain.PicsDomain;
import com.yc.travel.pics.client.TravelPicsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

//Hystrix服务层:  调用 PiclibClient,实现断路器功能
@Service
public class TravelPicsRestService {
    @Autowired
    private TravelPicsClient travelClient;


    @HystrixCommand(fallbackMethod = "findByIdFallback")
    public String findById(Integer id) {
        return travelClient.findById(id);
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
        return travelClient.findAll(page, pageSize, description);
    }

    private String findAllFallback(Integer page, Integer pageSize,
                                   String description) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "createFallback")
    public String create(PicsDomain picDomain) {
        return travelClient.create(picDomain);
    }

    private String createFallback(PicsDomain picDomain) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常，无法添加" + picDomain.getPicpath());
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "deleteFallback")
    public String delete(Integer id) {
        return travelClient.delete(id);
    }

    private String deleteFallback(Integer id) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常，无法删除" + id);
        return new Gson().toJson(map);
    }


}