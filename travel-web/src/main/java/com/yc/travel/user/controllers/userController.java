package com.yc.travel.user.controllers;

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

    @RequestMapping(value = "/login/{uname}/{pwd}", method = RequestMethod.GET)
    public CompletableFuture<String> findByName(@PathVariable(value = "uname") String uname,
                                           @PathVariable(value = "pwd") String pwd) {

        return travelUserFuture.findByName(uname, pwd);
    }


    @RequestMapping(value = "/create/{uname}/{pwd}/{tel}", method = RequestMethod.POST)
    public CompletableFuture<String> create(@PathVariable(value = "uname") String uname,
                                                @PathVariable(value = "pwd") String pwd,
                                            @PathVariable(value = "tel") String tel) {

        System.out.println("userController1成功");

        return travelUserFuture.create(uname, pwd, tel);
    }
}

