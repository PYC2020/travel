package com.yc.travel.order.client;


import com.yc.order.domain.OrderDomain;
import com.yc.travel.order.config.OrderFeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@FeignClient(name = "BASE-ZUUL-GATEWAY",
        configuration = OrderFeignClientConfig.class
)
public interface TravelOrderClient {
    @RequestMapping(method = RequestMethod.POST, value = "/travel-api/travel-order-proxy/travel/order/findByUid/{uid}")
    String findByUid(@RequestParam("uid") Integer uid);
    @RequestMapping(method = RequestMethod.POST, value = "/travel-api/travel-order-proxy/travel/order/findByPid/{pid}")
    String findByPid(@RequestParam("pid") Integer pid);
    //访问的路径value要修改成zuul指定的服务路由路径
    @RequestMapping(method = RequestMethod.GET, value = "/travel-api/travel-order-proxy/travel/order/{id}")
    String findById(@RequestParam("id") Integer id);
    @RequestMapping(method = RequestMethod.GET, value = "/travel-api/travel-order-proxy/travel/order/find")
    String find();
    @RequestMapping(method = RequestMethod.GET, value = "/travel-api/travel-order-proxy/travel/order/findAll",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findAll(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    @RequestMapping(method = RequestMethod.POST, value = "/travel-api/travel-order-proxy/travel/order/save")
    String create(@RequestParam("uid")  Integer uid,@RequestParam("pid")  Integer pid,@RequestParam("status")  Integer status,
                  @RequestParam("sdate")  String sdate,@RequestParam("edate")  String edate,@RequestParam("num")  Integer num);

    @RequestMapping(method = RequestMethod.GET, value = "/travel-api/travel-order-proxy/travel/order/delete/{id}")
    String delete(@RequestParam("id") Integer id);

    @RequestMapping(method = RequestMethod.GET, value = "/travel-api/travel-order-proxy/travel/order/update/{id}")
    String update(@RequestParam("id") Integer id);
}
