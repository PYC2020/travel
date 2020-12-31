package com.yc.travel.order.future;

import com.yc.order.domain.OrderDomain;
import com.yc.travel.order.service.TravelOrderRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

@Component
public class TravelOrderFuture {

    @Autowired
    private TravelOrderRestService travelOrderRestService;

    @Async
    public CompletableFuture<String> findById(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            return travelOrderRestService.findById(id);
        });
    }
    @Async
    public CompletableFuture<String> findByPid(Integer pid) {
        return CompletableFuture.supplyAsync(() -> {
            return travelOrderRestService.findByPid(pid);
        });
    }
    @Async
    public CompletableFuture<String> find() {
        return CompletableFuture.supplyAsync(() -> {
            return travelOrderRestService.find();
        });
    }
    @Async
    public CompletableFuture<String> findByUid(Integer uid) {
        return CompletableFuture.supplyAsync(() -> {
            return travelOrderRestService.findByUid(uid);
        });
    }


    @Async
    public CompletableFuture<String> findPage(Integer page, Integer limit) {
        return CompletableFuture.supplyAsync(() -> {
            return travelOrderRestService.findAll(page, limit);
        });
    }

    @Async
    public CompletableFuture<String> create(Integer uid,Integer pid,Integer status, String sdate, String edate, Integer num) {

        return CompletableFuture.supplyAsync(() -> {
            return travelOrderRestService.create(uid, pid, status, sdate, edate, num);
        });
    }


    @Async
    public CompletableFuture<String> delete(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            return travelOrderRestService.delete(id);
        });
    }

    @Async
    public CompletableFuture<String> update(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            return travelOrderRestService.update(id);
        });
    }
}
