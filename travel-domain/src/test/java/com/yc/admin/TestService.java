package com.yc.admin;

import com.yc.travel.admin.domain.AdminDomain;
import com.yc.travel.admin.service.AdminService;
import com.yc.travel.pics.service.PicsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DaoConfiguration.class})
public class TestService {

    private static final Logger logger = Logger.getLogger(TestService.class.getName());

    @Autowired
    private AdminService adminService;


    @Test
    public void testList() {
        logger.info("调用adminService.list");
        List<AdminDomain> list = adminService.list();
        System.out.println(list);
    }


}
