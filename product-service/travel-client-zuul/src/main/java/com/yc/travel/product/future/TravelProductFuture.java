package com.yc.travel.product.future;

import com.yc.travel.product.service.TravelProductRestService;
import com.yc.product.domain.ProductDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

// 对外公开的业务层
@Component
public class TravelProductFuture {
    @Autowired
    private TravelProductRestService travelRestService;

    @Async
    public CompletableFuture<String> findById(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            return travelRestService.findById(id);
        });
    }


    @Async
    public CompletableFuture<String> findPage(Integer page, Integer pageSize) {
        return CompletableFuture.supplyAsync(() -> {
            return travelRestService.findAll(page, pageSize);
        });
    }

    @Async
    public CompletableFuture<String> create(ProductDomain productDomain) {
        return CompletableFuture.supplyAsync(() -> {
            return travelRestService.create(productDomain);
        });
    }


    @Async
    public CompletableFuture<String> delete(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            return travelRestService.delete(id);
        });
    }
}