package com.example.demo.dao;

import com.example.demo.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    //查询所有用户信息
    List<User>  queryUserInformation();

    //查询指定id用户信息
    List<User>  selectOne_User(@Param("user_id")String user_id);

    //修改用户密码
    List<User> wx_change_password(User user);

    //用户支付电影票
    List<User> payUser_money(User user);

    //用户退票退款
    List<User> wx_refundUser(User user);

    //用户注册
    List<User> userSignUp(User user);

    //用户登录
    List<User> userLogin(User user);
}

