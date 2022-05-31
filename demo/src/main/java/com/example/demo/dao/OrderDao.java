package com.example.demo.dao;

import com.example.demo.bean.Order;
import com.example.demo.bean.Transfer_Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {
    //查找所有订单
    List<Order> selectAllFilm_Order();

    //添加订单
    List<Order> addNewOrder(Order order);

    //查找指定id用户订单
    List<Order> selectOneUser_order(@Param("user_id")String user_id);

    //删除指定id订单
    List<Order> wx_quitUserOrder(@Param("order_id")String order_id);

    //修改订单状态
    List<Order> wx_Update_oneOrderState(Order order);

    //查询和该用户相同场次的订单信息
    List<Order> wx_select_sameSession_order(@Param("user_id")String user_id);

    //比对座位号查询是否售出
    List<Order> wx_determine_seat_isSell(Order order);

    //判断场次是否有你的座位
    List<Order> wx_determine_isYourSeat(Order order);

    //添加转让电影票
    List<Transfer_Order> wx_addTransfer_order(Transfer_Order transfer_order);

    //查询所有转让电影票
    List<Transfer_Order> wx_selectAllTransfer_order();

    //删除指定id转让票
    List<Transfer_Order> wx_deleteOneTransfer_order(@Param("transfer_order_id") String transfer_order_id);


}
