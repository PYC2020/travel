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

    //访问的路径value要修改成zuul指定的服务路由路径
    //根据pid查询产品
    @RequestMapping(method = RequestMethod.GET, value = "/travel-api/travel-proxy/travel/product/pid/{pid}")
    String findByPid( @PathVariable(value="pid") Integer pid);


    //根据pname查询相关产品
    @RequestMapping(method = RequestMethod.GET, value = "/travel-api/travel-proxy/travel/product/{pname}")
    String findBypname( @PathVariable(value="pname") String pname);

    @RequestMapping(method = RequestMethod.GET, value = "/travel-api/travel-proxy/travel/product/findAll",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findAll(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize
                  );

    @RequestMapping(method = RequestMethod.POST, value = "/travel-api/travel-proxy/travel",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String create(@RequestBody ProductDomain productDomain);

    @RequestMapping(method = RequestMethod.DELETE, value = "/travel-api/travel-proxy/travel/product/{id}")
    String delete(@RequestParam("id") Integer id);

}
