package com.example.demo.controller;

import com.example.demo.bean.Hall;
import com.example.demo.dao.HallDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class HallController {
    @Autowired
    HallDao hallDao;


    //查看所有电影院影厅
    @RequestMapping("/selectAllHall")
    public List<Hall> SelectAllHall(HttpServletRequest request){
        List<Hall> allHall = hallDao.selectAllHall();
        System.out.println("allHall:"+allHall);
        return allHall;
    }

    //按电影院名查找对应影院的影厅
    @RequestMapping("/selectOneCinema_Hall")
    public List<Hall> SelectOneCinema_Hall(HttpServletRequest request,@RequestBody Hall hall){
        List<Hall> OneCinema_hall = hallDao.selectOneCinema_hall(hall.getCinema_id());
        System.out.println("OneCinema_Hall:"+OneCinema_hall);
        return OneCinema_hall;
    }

    //添加对应电影院的影厅
    @RequestMapping("/addOneHall")
    public List<Hall> AddOneCinema_hall(HttpServletRequest request,@RequestBody Hall hall){
        List<Hall> addHall = hallDao.addCinema_hall(hall.getHall_id(),
                hall.getHall_number(),hall.getHall_seat_count(),hall.getCinema_id());
        System.out.println("addHall:"+addHall);
        return addHall;
    }

    //按对于id更新影厅信息
    @RequestMapping("/updateOneHall")
    public List<Hall> UpdateOneHall(HttpServletRequest request,@RequestBody Hall hall){
        List<Hall> updateHall = hallDao.updateOneHall(hall.getHall_id(),hall.getHall_number(),hall.getHall_seat_count());
        System.out.println("updateHall:"+updateHall);
        return updateHall;
    }

    //查找指定影厅id的排片详情
    @RequestMapping("/selectOneHall")
    List<Hall> SelectOneHall(HttpServletRequest request,@RequestBody Hall hall){
        List<Hall> oneHall = hallDao.selectOneHall(hall.getHall_id());
        System.out.println("oneHall:"+oneHall);
        return oneHall;
    }
}
