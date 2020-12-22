package com.yc.travel.user.future;


import com.yc.admin.domain.AdminDomain;
import com.yc.travel.user.service.TravelUserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class TravelUserFuture {

    @Autowired
    private TravelUserRestService travelUserRestService;

    @Async
    public CompletableFuture<String> findById(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            return travelUserRestService.findById(id);
        });
    }


    @Async
    public CompletableFuture<String> findPage(Integer page, Integer pageSize
    ) {
        return CompletableFuture.supplyAsync(() -> {
            return travelUserRestService.findAll(page, pageSize);
        });
    }

    @Async
    public CompletableFuture<String> create(AdminDomain adminDomain) {

        return CompletableFuture.supplyAsync(() -> {
            return travelUserRestService.create(adminDomain);
        });
    }


    @Async
    public CompletableFuture<String> delete(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            return travelUserRestService.delete(id);
        });
    }
}
