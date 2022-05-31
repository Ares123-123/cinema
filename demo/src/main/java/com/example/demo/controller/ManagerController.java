package com.example.demo.controller;

import com.example.demo.bean.Cinema;
import com.example.demo.bean.Manager;
import com.example.demo.dao.ManagerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ManagerController {
    @Autowired
    ManagerDao managerDao;

    //查看所有管理员
    @RequestMapping("/selectAllManager")
    public List<Manager> SelectAllManager(HttpServletRequest request){
        List<Manager> allManager = managerDao.selectAllManager();
        System.out.println("allManager:"+allManager);
        return allManager;
    }

    //注册管理员账号
    @RequestMapping( "/signUpManager")
    public List<Manager> signUpUser(HttpServletRequest request,@RequestBody Manager manager){
        List<Manager> signUpManager= managerDao.SignUpManager(manager.getManager_id(),manager.getManager_name(),manager.getManager_password());
        return signUpManager;
    }
}
