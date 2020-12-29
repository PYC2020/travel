package com.yc.travel.order.client;


import com.yc.order.domain.OrderDomain;
import com.yc.travel.order.config.OrderFeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "BASE-ZUUL-GATEWAY",
        configuration = OrderFeignClientConfig.class
)
public interface TravelOrderClient {
    @RequestMapping(method = RequestMethod.GET, value = "/travel-api/travel-order-proxy/travel/order/findByPid/{pid}")
    String findByPid(@RequestParam("pid") Integer pid);
    //访问的路径value要修改成zuul指定的服务路由路径
    @RequestMapping(method = RequestMethod.GET, value = "/travel-api/travel-order-proxy/travel/order/{id}")
    String findById(@RequestParam("id") Integer id);

    @RequestMapping(method = RequestMethod.GET, value = "/travel-api/travel-order-proxy/travel/order/findAll",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findAll(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    @RequestMapping(method = RequestMethod.POST, value = "/travel-api/travel-order-proxy/travel",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String create(@RequestBody OrderDomain orderDomain);

    @RequestMapping(method = RequestMethod.DELETE, value = "/travel-api/travel-order-proxy/travel/order/{id}")
    String delete(@RequestParam("id") Integer id);
}
