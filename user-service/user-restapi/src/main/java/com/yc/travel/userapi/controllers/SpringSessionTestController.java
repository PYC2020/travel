package com.yc.travel.userapi.controllers;

import com.google.gson.Gson;
import com.yc.admin.domain.AdminDomain;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/session")
public class SpringSessionTestController {
    //在别的微服务中调用session
    @RequestMapping("/getsession")
    public String getSesseion(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            HttpSession session = request.getSession();
            AdminDomain value = (AdminDomain) session.getAttribute("user");
            map.put("user", value);
            return new Gson().toJson(map);
        }catch (Exception e){
            map.put("user", "没有UserSession");
            e.printStackTrace();
            return new Gson().toJson(map);
        }
    }
    //设置session失效
    @RequestMapping("/logout")
    public void logout(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            request.getSession().invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
