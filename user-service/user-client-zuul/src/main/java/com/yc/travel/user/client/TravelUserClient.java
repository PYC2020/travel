package com.yc.travel.user.client;


import com.yc.admin.domain.AdminDomain;
import com.yc.travel.user.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "BASE-ZUUL-GATEWAY",
        configuration = FeignClientConfig.class
)
public interface TravelUserClient {

    //访问的路径value要修改成zuul指定的服务路由路径
    @RequestMapping(method = RequestMethod.GET, value = "/travel-api/travel-proxy/travel/user/{uname}/{pwd}")
    String findByName(@PathVariable String uname,@PathVariable String pwd);

    @RequestMapping(method = RequestMethod.GET, value = "/travel-api/travel-proxy/travel/user/save/{uname}/{pwd}/{tel}")
    String save(@PathVariable String uname,@PathVariable String pwd,@PathVariable String tel);

    @RequestMapping(method = RequestMethod.GET, value = "/travel-api/travel-user-proxy/travel/user/findAll",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findAll(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize);

   @RequestMapping(method = RequestMethod.POST, value = "/travel-api/travel-user-proxy/travel",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String create(@RequestBody AdminDomain adminDomain);

    @RequestMapping(method = RequestMethod.DELETE, value = "/travel-api/travel-user-proxy/travel/user/{id}")
    String delete(@RequestParam("id") Integer id);
}
