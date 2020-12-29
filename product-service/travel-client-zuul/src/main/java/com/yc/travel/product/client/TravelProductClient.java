package com.yc.travel.product.client;

import com.yc.travel.config.FeignClientConfig;
import com.yc.product.domain.ProductDomain;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

// feign客户端要访问的是  zuul服务 BASE-ZUUL-GATEWAY
@FeignClient(name = "BASE-ZUUL-GATEWAY",configuration = FeignClientConfig.class)
  // 配置要按自定义的类FeignClientConfig
public interface TravelProductClient {

    //根据 pname,pid,tno查询
    @RequestMapping(method = RequestMethod.POST, value = "/travel-api/travel-proxy/travel/product/findBy")
    String findBy( @RequestParam("pname") String pname,@RequestParam("pid")Integer pid,@RequestParam("tno")Integer tno);

    @RequestMapping(method = RequestMethod.GET, value = "/travel-api/travel-proxy/travel/product/findAll",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findAll(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    @RequestMapping(method = RequestMethod.POST, value = "/travel-api/ttravel-proxy/travel",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String create(@RequestBody ProductDomain productDomain);

    @RequestMapping(method = RequestMethod.DELETE, value = "/travel-api/travel-proxy/travel/product/{id}")
    String delete(@RequestParam("id") Integer id);

}
