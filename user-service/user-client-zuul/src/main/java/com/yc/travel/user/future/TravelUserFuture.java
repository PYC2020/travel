package com.yc.travel.user.future;


import com.yc.admin.domain.AdminDomain;
import com.yc.travel.user.service.TravelUserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;

@Component
public class TravelUserFuture {

    @Autowired
    private TravelUserRestService travelUserRestService;

    @Async
    public CompletableFuture<String> findByName( String uname, String pwd) {
        return CompletableFuture.supplyAsync(() -> {
            return travelUserRestService.findByName(uname, pwd);
        });
    }

    @Async
    public CompletableFuture<String> getsession(HttpServletRequest request) {
        return CompletableFuture.supplyAsync(() -> {
            return travelUserRestService.getsession(request);
        });
    }


    @Async
    public CompletableFuture<String> findPage(Integer page, Integer pageSize) {
        return CompletableFuture.supplyAsync(() -> {
            return travelUserRestService.findAll(page, pageSize);
        });
    }


    @Async
    public CompletableFuture<String> create(String uname, String pwd,String tel) {
        return CompletableFuture.supplyAsync(() -> {
            return travelUserRestService.create(uname, pwd, tel);
        });
    }


    @Async
    public CompletableFuture<String> delete(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            return travelUserRestService.delete(id);
        });
    }
}
