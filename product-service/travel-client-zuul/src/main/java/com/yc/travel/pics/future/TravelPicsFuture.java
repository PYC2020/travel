package com.yc.travel.pics.future;

import com.yc.pics.domain.PicsDomain;
import com.yc.travel.pics.service.TravelPicsRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.CompletableFuture;

// 对外公开的业务层
@Component
public class TravelPicsFuture {
    @Autowired
    private TravelPicsRestService travelRestService;

    @Async
    public CompletableFuture<String> findById(@PathVariable Integer pid) {
        return CompletableFuture.supplyAsync(() -> {
            return travelRestService.findById(pid);
        });
    }


    @Async
    public CompletableFuture<String> findPage(Integer page, Integer pageSize
                                              ) {
        return CompletableFuture.supplyAsync(() -> {
            return travelRestService.findAll(page, pageSize);
        });
    }

    @Async
    public CompletableFuture<String> create(PicsDomain picsDomain) {

        return CompletableFuture.supplyAsync(() -> {
            return travelRestService.create(picsDomain);
        });
    }


    @Async
    public CompletableFuture<String> delete(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            return travelRestService.delete(id);
        });
    }
}