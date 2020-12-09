package com.yc.travel.type.future;

import com.yc.travel.type.service.TravelTypeRestService;
import com.yc.type.domain.TypeDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

// 对外公开的业务层
@Component
public class TravelTypeFuture {
    @Autowired
    private TravelTypeRestService travelRestService;

    @Async
    public CompletableFuture<String> findById(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            return travelRestService.findById(id);
        });
    }


    @Async
    public CompletableFuture<String> findPage(Integer page, Integer pageSize,
                                              String description) {
        return CompletableFuture.supplyAsync(() -> {
            return travelRestService.findAll(page, pageSize, description);
        });
    }

    @Async
    public CompletableFuture<String> create(TypeDomain typeDomain) {
        return CompletableFuture.supplyAsync(() -> {
            return travelRestService.create(typeDomain);
        });
    }


    @Async
    public CompletableFuture<String> delete(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            return travelRestService.delete(id);
        });
    }
}