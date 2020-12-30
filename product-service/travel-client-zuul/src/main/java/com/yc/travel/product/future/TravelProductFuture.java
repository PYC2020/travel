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
    @Async
    public CompletableFuture<String> findBy(String pname,Integer pid,Integer tno) {
        return CompletableFuture.supplyAsync(() -> {
            return travelRestService.findBy(pname,pid,tno);
        });
    }
    @Async
    public CompletableFuture<String> findPage(Integer page, Integer limit) {
        return CompletableFuture.supplyAsync(() -> {
            return travelRestService.findAll(page, limit);
        });
    }

    @Async
    public CompletableFuture<String> findById(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            return travelRestService.findById(id);
        });
    }

    @Async
    public CompletableFuture<String> create(String pname,Integer tno,Integer price,String intro,Integer balance,String company, String pic) {
        return CompletableFuture.supplyAsync(() -> {
            return travelRestService.create(pname, tno, price, intro, balance, company, pic);
        });
    }


    @Async
    public CompletableFuture<String> delete(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            return travelRestService.delete(id);
        });
    }
}