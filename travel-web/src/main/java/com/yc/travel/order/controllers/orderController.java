package com.yc.travel.order.controllers;

import com.yc.travel.order.future.TravelOrderFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/travel/order")
public class orderController {

    private static Logger logger = LoggerFactory.getLogger(orderController.class.getName());

    @Autowired
    private TravelOrderFuture travelOrderFuture;
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public CompletableFuture<String> find() {
        return travelOrderFuture.find();
    }
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public CompletableFuture<String> findAll(Integer page, Integer limit) {
        return travelOrderFuture.findPage(page,limit);
    }

    /**
     * 当status为1或者2时根据pid,查商品，
     * @param pid
     * @return
     */
    @RequestMapping(value = "/findByPid/{pid}", method = RequestMethod.POST)
    public CompletableFuture<String> findByPid(@PathVariable Integer pid) {
        return travelOrderFuture.findByPid(pid);
    }
    @RequestMapping(value = "/findByUid/{uid}", method = RequestMethod.GET)
    public CompletableFuture<String> findByUid(@PathVariable Integer uid) {
        return travelOrderFuture.findByUid(uid);
    }

    @RequestMapping(value = "/create", method = {RequestMethod.GET,RequestMethod.POST})
    public CompletableFuture<String> create(@RequestParam("uid")  Integer uid, @RequestParam("pid")  Integer pid, @RequestParam("status")  Integer status,
                                            @RequestParam("sdate")  String sdate, @RequestParam("edate")  String edate, @RequestParam("num")  Integer num) throws Exception  {
        return travelOrderFuture.create(uid, pid, status, sdate, edate, num);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CompletableFuture<String> delete(@PathVariable Integer id) throws Exception {
        return travelOrderFuture.delete(id);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public CompletableFuture<String> update(@PathVariable Integer id) throws Exception {
        return travelOrderFuture.update(id);
    }
}
