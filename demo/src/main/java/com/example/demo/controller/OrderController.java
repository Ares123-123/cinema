package com.example.demo.controller;

import com.example.demo.bean.Order;
import com.example.demo.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderDao orderDao;
    //查找指定id用户订单
    @RequestMapping("/selectAllFilm_Order")
    public List<Order> SelectAllFilm_Order(HttpServletRequest request) {

        List<Order> allOrder = orderDao.selectAllFilm_Order();

        return  allOrder;
    }
}
