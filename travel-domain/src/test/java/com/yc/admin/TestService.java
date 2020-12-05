package com.yc.admin;


import com.yc.travel.admin.domain.AdminDomain;
import com.yc.travel.admin.domain.ProductDomain;
import com.yc.travel.admin.domain.TypeDomain;
import com.yc.travel.admin.domain.PicsDomain;
import com.yc.travel.admin.service.AdminService;
import com.yc.travel.admin.service.PicsService;
import com.yc.travel.admin.service.TypeService;
import com.yc.travel.admin.service.ProductService;
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

    @Autowired
    private ProductService productService;

    @Autowired
    private PicsService picsService;

    @Autowired
    private TypeService typeService;

    @Test
    public void testList() {
        logger.info("调用adminService.list");
        List<AdminDomain> list = adminService.list();
        System.out.println(list);
    }


    @Test
    public void testList1() {
        logger.info("调用productService.list");
        List<ProductDomain> list = productService.list();
        System.out.println(list);
    }


    @Test
    public void testList2() {
        logger.info("调用picsService.list");
        List<PicsDomain> list = picsService.list();
        System.out.println(list);
    }

    @Test
    public void testList3() {
        logger.info("调用typeService.list");
        List<TypeDomain> list = typeService.list();
        System.out.println(list);
    }



}
