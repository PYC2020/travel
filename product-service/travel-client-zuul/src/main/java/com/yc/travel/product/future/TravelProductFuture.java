package com.yc.travel.product.future;

import com.yc.travel.product.service.TravelProductRestService;
import com.yc.product.domain.ProductDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.CompletableFuture;

// 对外公开的业务层
@Component
public class TravelProductFuture {
    @Autowired
    private TravelProductRestService travelRestService;
    //根据pid查询相关产品
    @Async
    public CompletableFuture<String> findBypid(@PathVariable Integer pid) {
        return CompletableFuture.supplyAsync(() -> {
            return travelRestService.findBypid(pid);
        });
    }



    //根据pname查询相关产品
    @Async
    public CompletableFuture<String> findBypname(@PathVariable String pname) {
        return CompletableFuture.supplyAsync(() -> {
            return travelRestService.findBypname(pname);
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