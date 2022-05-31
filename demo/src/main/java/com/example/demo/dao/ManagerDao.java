package com.example.demo.dao;

import com.example.demo.bean.Manager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface ManagerDao {

    //登录验证
    public Manager LoginManager(@Param("manager_name")String manager_name,@Param("manager_password")String manager_password);

    //注册管理员
    public List<Manager> SignUpManager(@Param("manager_id")String manager_id,@Param("manager_name")String manager_name,@Param("manager_password")String manager_password);

    //查询所有管理员
    public List<Manager> selectAllManager();
}
