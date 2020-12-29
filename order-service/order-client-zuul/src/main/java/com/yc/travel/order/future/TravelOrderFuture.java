package com.yc.travel.order.future;

import com.yc.order.domain.OrderDomain;
import com.yc.travel.order.service.TravelOrderRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

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
            System.out.println("pid"+pid);
            return travelOrderRestService.findByPid(pid);
        });
    }


    @Async
    public CompletableFuture<String> findPage(Integer page, Integer limit) {
        return CompletableFuture.supplyAsync(() -> {
            return travelOrderRestService.findAll(page, limit);
        });
    }

    @Async
    public CompletableFuture<String> create(OrderDomain orderDomain) {

        return CompletableFuture.supplyAsync(() -> {
            return travelOrderRestService.create(orderDomain);
        });
    }


    @Async
    public CompletableFuture<String> delete(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            return travelOrderRestService.delete(id);
        });
    }
}
