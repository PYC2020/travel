package com.yc.travel.userapi.controllers;


import com.google.gson.Gson;

import com.yc.admin.domain.AdminDomain;
import com.yc.admin.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("travel/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    //@Resource
    @Autowired
    private AdminService adminService;


    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public CompletableFuture<String> findAll(Integer page, Integer pageSize) {
        return CompletableFuture.supplyAsync(() -> {

                AdminDomain  adminDomain = new AdminDomain();
                List<AdminDomain> list= adminService.list();

                Map<String, Object> map = new HashMap<>();
                map.put("code", 1);
                map.put("data", list);
                return new Gson().toJson(map);
        });
    }

    @RequestMapping(value = "/{uname}/{pwd}")
    public CompletableFuture<String> findByName(@PathVariable String uname,@PathVariable String pwd) {
        //非阻塞式异步编程方法。因为在web ui的微服务对rest api的调用中将使用这种高并发的编程方法，所以为了保证与调用端保持同步，这里也使用这种方法.
        return CompletableFuture.supplyAsync(() -> {
            AdminDomain adminDomain = new AdminDomain();
            adminDomain.setUname(uname);
            adminDomain.setPwd(pwd);
            List a = adminService.findOne(adminDomain);
            //协议
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("data", a);
            return new Gson().toJson(map);
        });
    }

    @RequestMapping(value = "/save/{uname}/{pwd}/{tel}",method = RequestMethod.GET)
    public CompletableFuture<String> save(@PathVariable String uname,@PathVariable String pwd,
                                                    @PathVariable String tel) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            AdminDomain adminDomain = new AdminDomain();
            adminDomain.setUname(uname);
            adminDomain.setPwd(pwd);
            adminDomain.setTel(tel);
            adminService.save(adminDomain);
            logger.info("新增->ID=" + adminDomain.getUid());
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("data", adminDomain);
            return new Gson().toJson(map);

        });
    }
}
