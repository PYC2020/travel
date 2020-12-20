package com.yc.travel.picsapi.controllers;


import com.google.gson.Gson;
import com.yc.pics.domain.PicsDomain;
import com.yc.pics.service.PicsService;
import com.yc.product.domain.ProductDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/travel/pics")
public class PicsController {

    private static Logger logger = LoggerFactory.getLogger(PicsController.class);

    @Autowired
    private PicsService picsService;

    @RequestMapping(value = "/{id}")
    public CompletableFuture<String> findById(@PathVariable Integer id) {
        //非阻塞式异步编程方法。因为在web ui的微服务对rest api的调用中将使用这种高并发的编程方法，所以为了保证与调用端保持同步，这里也使用这种方法.
        return CompletableFuture.supplyAsync(() -> {
            PicsDomain pic = picsService.findOne(id);
            //协议
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("data", pic);
            return new Gson().toJson(map);
        });
    }


    @RequestMapping(method = RequestMethod.GET)
    public CompletableFuture<String> save(@RequestBody PicsDomain picDomain) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            picsService.save(picDomain);
            logger.info("新增->ID=" + picDomain.getPid());
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("data", picDomain);
            return new Gson().toJson(map);
        });
    }
}
