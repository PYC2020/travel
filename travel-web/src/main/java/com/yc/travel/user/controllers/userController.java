package com.yc.travel.user.controllers;

import com.yc.admin.domain.AdminDomain;
import com.yc.travel.user.future.TravelUserFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/travel/user")
public class userController {

    private static Logger logger = LoggerFactory.getLogger(userController.class.getName());

    @Autowired
    private TravelUserFuture travelUserFuture;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public CompletableFuture<String> findByName(String uname,String pwd) {

        return travelUserFuture.findByName(uname, pwd);
    }


    @RequestMapping(value = "/create", method = {RequestMethod.POST,RequestMethod.GET})
    public CompletableFuture<String> create(@PathVariable AdminDomain adminDomain) throws Exception  {

        System.out.println("userController1成功");

        return travelUserFuture.create(adminDomain);
    }
}

