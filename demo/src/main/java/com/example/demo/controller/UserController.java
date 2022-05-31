package com.example.demo.controller;

import com.example.demo.bean.User;
import com.example.demo.dao.UserDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserDao userDao;
    //查询所有用户信息
    @RequestMapping( "/QueryUser")
    public List<User> Query(HttpServletRequest request){
        List<User> user_list= userDao.queryUserInformation();
        System.out.println("list:"+user_list);
        return user_list;
    }
}
