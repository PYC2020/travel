package com.yc;


import com.yc.order.domain.OrderDomain;
import com.yc.order.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DaoConfiguration.class})
public class TestService {

    private static final Logger logger = Logger.getLogger(TestService.class.getName());

    @Autowired
    private OrderService orderService;




    @Test
    public void testList() {
        logger.info("调用orderService.list");
        List<OrderDomain> list = orderService.list();
        System.out.println(list);
    }
    @Test
    public void testList1() {
        logger.info("调用orderService.list");

        System.out.println(orderService.count());
    }

}
