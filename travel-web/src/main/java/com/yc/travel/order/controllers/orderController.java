package com.yc.travel.order.controllers;

import com.yc.travel.order.future.TravelOrderFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/travel/order")
public class orderController {

    private static Logger logger = LoggerFactory.getLogger(orderController.class.getName());

    @Autowired
    private TravelOrderFuture travelOrderFuture;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public CompletableFuture<String> findAll(Integer page, Integer limit) {
        return travelOrderFuture.findPage(page,limit);
    }
}
