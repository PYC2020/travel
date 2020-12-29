package com.yc.travel.user.service;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yc.admin.domain.AdminDomain;
import com.yc.travel.user.client.TravelUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class TravelUserRestService {
    @Autowired
    private TravelUserClient travelUserClient;

   @HystrixCommand(fallbackMethod = "findByNameFallback")
    public String findByName( String uname,String pwd ){
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


    //@HystrixCommand(fallbackMethod = "createFallback")
    public String create(String uname, String pwd,String tel){
        System.out.println("3");
        return travelUserClient.create(uname, pwd, tel);
    }

    private String createFallback( String uname,String pwd,String tel) {
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

    public String getsession(HttpServletRequest request){
        return travelUserClient.getSesseion(request);
    }
}
