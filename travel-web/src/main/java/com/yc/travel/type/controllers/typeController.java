package com.yc.travel.type.controllers;

import com.yc.travel.pics.controllers.PicController;
import com.yc.travel.type.future.TravelTypeFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/travel/type")
public class typeController {
    private static Logger logger = LoggerFactory.getLogger(PicController.class.getName());
    @Autowired
    private  TravelTypeFuture travelTypeFuture;
    @RequestMapping(value = "/findAll", method = {RequestMethod.POST,RequestMethod.GET})
    public CompletableFuture<String> list() {
        return travelTypeFuture.findAll();
    }
}
