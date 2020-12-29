package com.yc.travel.user.client;

import com.yc.admin.domain.AdminDomain;
import com.yc.travel.user.config.FeignUserClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@FeignClient(name = "BASE-ZUUL-GATEWAY",
        configuration = FeignUserClientConfig.class
)
public interface TravelUserClient {

    //访问的路径value要修改成zuul指定的服务路由路径
    @RequestMapping(method = RequestMethod.GET, value = "/travel-api/travel-user-proxy/travel/user/login")
    String findByName(@RequestParam("uname") String uname,@RequestParam("pwd") String pwd);

    @RequestMapping(method = RequestMethod.GET,value = "/travel-api/travel-user-proxy/session/getsession")
    String getSesseion(HttpServletRequest request);


    @RequestMapping(method = RequestMethod.POST, value = "/travel-api/travel-user-proxy/travel/user/save")
    String create(@RequestParam("uname") String uname,@RequestParam("pwd") String pwd,@RequestParam("tel") String tel);

    @RequestMapping(method = RequestMethod.GET, value = "/travel-api/travel-user-proxy/travel/user/findAll",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findAll(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize);


    @RequestMapping(method = RequestMethod.DELETE, value = "/travel-api/travel-user-proxy/travel/user/{id}")
    String delete(@RequestParam("id") Integer id);
}
