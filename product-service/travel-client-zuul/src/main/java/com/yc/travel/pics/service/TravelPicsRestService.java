package com.yc.travel.pics.service;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yc.pics.domain.PicsDomain;
import com.yc.travel.pics.client.TravelPicsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

//Hystrix服务层:  调用 PiclibClient,实现断路器功能
@Service
public class TravelPicsRestService {
    @Autowired
    private TravelPicsClient travelClient;


    @HystrixCommand(fallbackMethod = "findByIdFallback")
    public String findById(@PathVariable Integer pid) {
        return travelClient.findById(pid);
    }

    private String findByIdFallback(Integer pid) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "查询图片服务异常");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "findAllFallback")
    public String findAll(Integer page, Integer pageSize
                         ) {
        return travelClient.findAll(page, pageSize);
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
        System.out.println("TravelPicsRestService   create失败");
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