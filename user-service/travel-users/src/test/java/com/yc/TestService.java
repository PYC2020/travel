package com.yc;

import com.yc.admin.domain.AdminDomain;
import com.yc.admin.service.AdminService;
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
        AdminDomain adminDomain=new AdminDomain(0,"a","a","1232132");
        List<AdminDomain> list = adminService.findOne(adminDomain);
        System.out.println(list);
    }
    @Test
    public void testList1() {
        logger.info("调用adminService.list");
        AdminDomain adminDomain=new AdminDomain(0,"a","a","1232132");
        AdminDomain list= adminService.login(adminDomain);
        System.out.println(list);
    }


}
