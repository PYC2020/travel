package com.yc.travel.user.service;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yc.admin.domain.AdminDomain;
import com.yc.travel.user.client.TravelUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

@Service
public class TravelUserRestService {
    @Autowired
    private TravelUserClient travelUserClient;

   @HystrixCommand(fallbackMethod = "findByNameFallback")
    public String findByName(@PathVariable(value = "uname") String uname,
                             @PathVariable(value = "pwd") String pwd ){
        return travelUserClient.findByName(uname,pwd);
    }

    private String findByNameFallback(String uname, String pwd) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "findAllFallback")
    public String findAll(Integer page, Integer pageSize
    ) {
        return travelUserClient.findAll(page, pageSize);
    }

    private String findAllFallback(Integer page, Integer pageSize) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常");
        return new Gson().toJson(map);
    }


    @HystrixCommand(fallbackMethod = "createFallback")
    public String create(@PathVariable(value = "uname") String uname,
                             @PathVariable(value = "pwd") String pwd ,
                             @PathVariable(value = "tel") String tel){
        System.out.println("userController3成功");
        return travelUserClient.create(uname, pwd, tel);
    }

    private String createFallback( String uname,String pwd,String tel) {
        System.out.println("travelUserClient   create失败");
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常，无法添加" + uname);
        return new Gson().toJson(map);
    }

    @HystrixCommand(fallbackMethod = "deleteFallback")
    public String delete(Integer id) {
        return travelUserClient.delete(id);
    }

    private String deleteFallback(Integer id) {
        Map map = new HashMap();
        map.put("code", "-1");
        map.put("msg", "服务异常，无法删除" + id);
        return new Gson().toJson(map);
    }
}
