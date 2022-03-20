package com.mrlu.mybatisplus;

import com.mrlu.mybatisplus.dao.EmployeeMapper;
import com.mrlu.mybatisplus.dao.OrderDao;
import com.mrlu.mybatisplus.domain.Order;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-04 15:59
 */
public class OrderDaoTest {
    private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    private OrderDao orderDao = context.getBean("orderDao",OrderDao.class);

    @Test
    public void insert() {
        Order order = new Order();
        order.setName("aabb");
        order.setCount(1000);
        int insert = orderDao.insert(order);
        System.out.println(insert);
    }
}