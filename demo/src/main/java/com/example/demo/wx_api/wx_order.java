package com.example.demo.wx_api;

import com.example.demo.bean.Comment;
import com.example.demo.bean.Order;
import com.example.demo.bean.Transfer_Order;
import com.example.demo.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class wx_order {
    @Autowired
    OrderDao orderDao;

    //添加订单
    @RequestMapping("/addNewOrder")
    public List<Order> SelectOneFilm(HttpServletRequest request) throws ParseException {

        //实例化
        Order neworder = new Order();
        //类型转换

        //日期转换
        SimpleDateFormat showDate = new SimpleDateFormat("yyyy-MM-dd");
        String order_date = request.getParameter("order_date");
        Date date = showDate.parse(order_date);
        java.sql.Date date1 = new java.sql.Date(date.getTime());

        //时间转换
        SimpleDateFormat showTime = new SimpleDateFormat("hh:mm:ss");
        String order_time = request.getParameter("order_time");
        Date time = showTime.parse(order_time);
        java.sql.Time time1 = new java.sql.Time(time.getTime());

        //获取小程序的数据
        String user_id = request.getParameter("user_id");
        String order_id = request.getParameter("order_id");
        String food_order_id = request.getParameter("food_order_id");
        String film_id = request.getParameter("film_id");
        String session_id = request.getParameter("session_id");
        int seat_number = Integer.valueOf(request.getParameter("seat_number")); //获取int类型数据方法用Integer.valueOf
        int total_price = Integer.valueOf(request.getParameter("total_price"));

        neworder.setOrder_id(order_id);
        neworder.setOrder_date(date1);
        neworder.setOrder_time(time1);
        neworder.setFood_order_id(food_order_id);
        neworder.setUser_id(user_id);
        neworder.setFilm_id(film_id);
        neworder.setSession_id(session_id);
        neworder.setSeat_number(seat_number);
        neworder.setTotal_price(total_price);

        System.out.println(neworder);

        //数据库操作
        List<Order>  addNewOrder = orderDao.addNewOrder(neworder);


        return addNewOrder;
    }

    //查找指定id用户订单
    @RequestMapping("/selectOneUser_order")
    public List<Order> SelectOneUser_order(HttpServletRequest request) {
        String user_id = request.getParameter("user_id");

        List<Order> oneUser_order = orderDao.selectOneUser_order(user_id);

        return  oneUser_order;
    }


    //查找指定id用户订单,并取删除订单
    @RequestMapping("/wx_quitUserOrder")
    public List<Order> wx_quitUserOrder(HttpServletRequest request) {
        String order_id = request.getParameter("order_id");

        List<Order> quitUserOrder = orderDao.wx_quitUserOrder(order_id);

        return  quitUserOrder;
    }

    //修改订单状态
    @RequestMapping("/wx_Update_oneOrderState")
    public List<Order> wx_Update_oneOrderState(HttpServletRequest request) {
        //实例化
        Order updateOrder = new Order();

        String order_id = request.getParameter("order_id");
        String state = request.getParameter("state");

        //添加
        updateOrder.setOrder_id(order_id);
        updateOrder.setState(state);

        List<Order> update_oneOrderState = orderDao.wx_Update_oneOrderState(updateOrder);
        return update_oneOrderState;
    }

    //查询和该用户相同场次的订单信息
    @RequestMapping("/wx_select_sameSession_order")
    public List<Order> wx_select_sameSession_order(HttpServletRequest request) {
        //实例化
        Order sameSession = new Order();

        String user_id = request.getParameter("user_id");

        List<Order> sameSession_order = orderDao.wx_select_sameSession_order(user_id);
        return sameSession_order;
    }
    //比对座位号查询是否售出
    @RequestMapping("/wx_determine_seat_isSell")
    public List<Order> wx_determine_seat_isSell(HttpServletRequest request) throws ParseException {
        //实例化
        Order isOrder = new Order();

        //获取小程序的数据
        String session_id = request.getParameter("session_id");
        int seat_number = Integer.valueOf(request.getParameter("seat_number"));

        isOrder.setSession_id(session_id);
        isOrder.setSeat_number(seat_number);

        List<Order> isSell = orderDao.wx_determine_seat_isSell(isOrder);

        return  isSell;
    }

    //比对座位号查询是否是你的座位
    @RequestMapping("/wx_determine_isYourSeat")
    public List<Order> wx_determine_isYourSeat(HttpServletRequest request) {
        //实例化
        Order isyourOrder = new Order();

        String user_id = request.getParameter("user_id");
        String session_id = request.getParameter("session_id");
        int seat_number = Integer.valueOf(request.getParameter("seat_number"));

        isyourOrder.setUser_id(user_id);
        isyourOrder.setSession_id(session_id);
        isyourOrder.setSeat_number(seat_number);

        List<Order> isyour = orderDao.wx_determine_isYourSeat(isyourOrder);
        System.out.println("isyour:"+isyour);

        return  isyour;
    }

    //添加转让电影票
    @RequestMapping("/wx_addTransfer_order")
        public List<Transfer_Order> wx_addTransfer_order(HttpServletRequest request) throws ParseException {
            //实例化
            Transfer_Order transfer_order = new Transfer_Order();

            //类型转换

            //日期转换
            SimpleDateFormat showDate = new SimpleDateFormat("yyyy-MM-dd");
            String order_date1 = request.getParameter("order_date");
            Date date2 = showDate.parse(order_date1);
            java.sql.Date date3 = new java.sql.Date(date2.getTime());

            //时间转换
            SimpleDateFormat showTime = new SimpleDateFormat("hh:mm:ss");
            String order_time1 = request.getParameter("order_time");
            Date time2 = showTime.parse(order_time1);
            java.sql.Time time3 = new java.sql.Time(time2.getTime());

            //获取小程序的数据
            String transfer_order_id =request.getParameter("transfer_order_id");
            String film_id = request.getParameter("film_id");
            String user_id = request.getParameter("user_id");
            String session_id = request.getParameter("session_id");
            int seat_number = Integer.valueOf(request.getParameter("seat_number"));
            int original_price = Integer.valueOf(request.getParameter("original_price"));
            int current_price = Integer.valueOf(request.getParameter("current_price"));

            transfer_order.setTransfer_order_id(transfer_order_id);
            transfer_order.setOrder_date(date3);
            transfer_order.setOrder_time(time3);
            transfer_order.setFilm_id(film_id);
            transfer_order.setUser_id(user_id);
            transfer_order.setSession_id(session_id);
            transfer_order.setSeat_number(seat_number);
            transfer_order.setOriginal_price(original_price);
            transfer_order.setCurrent_price(current_price);

            List<Transfer_Order> transferOrder = orderDao.wx_addTransfer_order(transfer_order);

            return  transferOrder;
        }

        //查询所有转让电影票
     @RequestMapping("/wx_selectAllTransfer_order")
     public List<Transfer_Order> wx_selectAllTransfer_order(HttpServletRequest request) {
        List<Transfer_Order> transfer_order = orderDao.wx_selectAllTransfer_order();
         System.out.println("transfer_order:"+transfer_order);
        return transfer_order;
     }

     //删除指定id电影票
     @RequestMapping("/wx_deleteOneTransfer_order")
     public List<Transfer_Order> wx_deleteOneTransfer_order(HttpServletRequest request) {
         String transfer_order_id = request.getParameter("transfer_order_id");
         List<Transfer_Order> delete_transfer_order = orderDao.wx_deleteOneTransfer_order(transfer_order_id);
         return delete_transfer_order;
     }
}
