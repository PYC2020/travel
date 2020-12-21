package com.yc.travel.productapi.controllers;


import com.google.gson.Gson;
import com.yc.pics.domain.PicsDomain;
import com.yc.product.domain.PageDomain;
import com.yc.product.domain.ProductDomain;
import com.yc.product.service.ProductService;
import com.yc.pics.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/travel/product")
public class ProductController {

    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/{pname}")
    public CompletableFuture<String> findById(@PathVariable String pname) {
        //非阻塞式异步编程方法。因为在web ui的微服务对rest api的调用中将使用这种高并发的编程方法，所以为了保证与调用端保持同步，这里也使用这种方法.
        return CompletableFuture.supplyAsync(() -> {
            ProductDomain productDomain = new ProductDomain();
            productDomain.setPname(pname);
            List p = productService.findOne(productDomain);
            //协议
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("data", p);
            return new Gson().toJson(map);
        });
    }
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public CompletableFuture<String> findAll(Integer page, Integer pageSize) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                ProductDomain productDomain = new ProductDomain();

                if (CommonUtils.isNotNull(page)) {
                    productDomain.setPage(page);
                }
                if (CommonUtils.isNotNull(pageSize)) {
                    productDomain.setPageSize(pageSize);
                }
                PageDomain<ProductDomain> pageDomain = productService.listByPage(productDomain);

                Map<String, Object> map = new HashMap<>();
                map.put("code", 1);
                map.put("data", pageDomain);


                return new Gson().toJson(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }
    @RequestMapping(method = RequestMethod.POST)
    public CompletableFuture<String> save(@RequestBody ProductDomain productDomain) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            productService.save(productDomain);
            logger.info("新增->ID=" + productDomain.getPid());
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("data", productDomain);
            return new Gson().toJson(map);

        });
    }


}
