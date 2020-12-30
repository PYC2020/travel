package com.yc.travel.product.controllers;

import com.yc.travel.pics.controllers.PicController;

import com.yc.travel.product.future.TravelProductFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/travel/product")
public class productController {
    private static Logger logger = LoggerFactory.getLogger(PicController.class.getName());
  //  @Value("${file.path.head:http://101.37.202.175/}")
    private String pathHead;
    @Autowired
    private TravelProductFuture travelProductFuture;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public CompletableFuture<String> findAll(Integer page, Integer limit) {
        return travelProductFuture.findPage(page, limit);
    }

    /**
     * 前端中pid,tno不使用需要为0
     * @param pname
     * @param pid
     * @param tno
     * @return
     */
    @RequestMapping(value = "/findBy", method = RequestMethod.POST)
    public CompletableFuture<String> findBy( String pname,Integer pid,Integer tno) {
        return travelProductFuture.findBy(pname,pid,tno);
    }

    @RequestMapping(value = "/{id}")
    public CompletableFuture<String> findById(@PathVariable Integer id) {
        return travelProductFuture.findById(id);
    }

    @RequestMapping(value = "/create", method = {RequestMethod.POST,RequestMethod.GET})
    public CompletableFuture<String> create(String pname,Integer tno,Integer price,String intro,Integer balance,
                                            String company, String pic) throws Exception  {
        return travelProductFuture.create(pname, tno, price, intro, balance, company, pic);
    }

}
