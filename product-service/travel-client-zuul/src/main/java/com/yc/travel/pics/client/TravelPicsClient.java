package com.yc.travel.pics.client;

import com.yc.travel.config.FeignClientConfig;
import com.yc.pics.domain.PicsDomain;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

// feign客户端要访问的是  zuul服务 BASE-ZUUL-GATEWAY
@FeignClient(name = "BASE-ZUUL-GATEWAY",
        configuration = FeignClientConfig.class
)  // 配置要按自定义的类FeignClientConfig
public interface TravelPicsClient {

    //访问的路径value要修改成zuul指定的服务路由路径
    @RequestMapping(method = RequestMethod.GET, value = "/travel-api/travel-proxy/travel/pics/{id}")
    String findById(@RequestParam("id") Integer id);

    @RequestMapping(method = RequestMethod.GET, value = "/travel-api/travel-proxy/travel/pics/findAll",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findAll(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize,
                   @RequestParam("description") String description);

    @RequestMapping(method = RequestMethod.POST, value = "/travel-api/travel-proxy/travel",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String create(@RequestBody PicsDomain picsDomain);

    @RequestMapping(method = RequestMethod.DELETE, value = "/travel-api/travel-proxy/travel/pics/{id}")
    String delete(@RequestParam("id") Integer id);

}
