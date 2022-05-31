package com.example.demo.controller;

import com.example.demo.bean.Manager;
import com.example.demo.bean.User;
import com.example.demo.dao.ManagerDao;
import com.example.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class LoginController {

    @Autowired
    ManagerDao managerDao;

    //登录验证
    @RequestMapping( "/Login")
    public String Login(HttpServletRequest request, @RequestBody Manager manager){
        Manager info =  managerDao.LoginManager(manager.getManager_name(),manager.getManager_password());
        System.out.println("manager:"+info);
        if(info!=null){
            return "OK";
        }
        return "ERROR";
    }


}
