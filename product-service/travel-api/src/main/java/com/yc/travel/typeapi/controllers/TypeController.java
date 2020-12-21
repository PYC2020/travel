package com.yc.travel.typeapi.controllers;


import com.google.gson.Gson;
import com.yc.pics.util.CommonUtils;
import com.yc.type.domain.PageDomain;
import com.yc.type.domain.TypeDomain;
import com.yc.type.entity.type;
import com.yc.type.service.TypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/travel/type")
public class TypeController {

    private static Logger logger = LoggerFactory.getLogger(TypeController.class);

    @Autowired
    private TypeService typeService;

    @RequestMapping(value = "/{id}")
    public CompletableFuture<String> findById(@PathVariable Integer id) {
        //非阻塞式异步编程方法。因为在web ui的微服务对rest api的调用中将使用这种高并发的编程方法，所以为了保证与调用端保持同步，这里也使用这种方法.
        return CompletableFuture.supplyAsync(() -> {
            TypeDomain type = typeService.findOne(id);
            //协议
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("data", type);
            return new Gson().toJson(map);
        });
    }
    @RequestMapping(value = "/findAll")
    public CompletableFuture<String> findAll() {
        //非阻塞式异步编程方法。因为在web ui的微服务对rest api的调用中将使用这种高并发的编程方法，所以为了保证与调用端保持同步，这里也使用这种方法.
        return CompletableFuture.supplyAsync(() -> {
            List<TypeDomain> type = typeService.list();
            //协议
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("data", type);
            return new Gson().toJson(map);
        });
    }


    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public CompletableFuture<String> findAll(Integer page, Integer pageSize) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TypeDomain typeDomain  = new TypeDomain();

                if (CommonUtils.isNotNull(page)) {
                    typeDomain.setPage(page);
                }
                if (CommonUtils.isNotNull(pageSize)) {
                    typeDomain.setPageSize(pageSize);
                }
                PageDomain<TypeDomain> pageDomain = typeService.listByPage(typeDomain);

                Map<String, Object> map = new HashMap<>();
                map.put("code", 1);
                map.put("data", pageDomain);


                return new Gson().toJson(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }
}
