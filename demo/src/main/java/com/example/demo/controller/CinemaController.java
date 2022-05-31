package com.example.demo.controller;

import com.example.demo.bean.Cinema;
import com.example.demo.dao.CinemaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@RestController
public class CinemaController {

    @Autowired
    CinemaDao cinemaDao;

    //查询所有电影院
    @RequestMapping("/selectAllCinema")
    public List<Cinema> SelectAllCinema(HttpServletRequest request){
        List<Cinema> allCinema = cinemaDao.selectAllCinema();
        System.out.println("allCinema:"+allCinema);
        return allCinema;
    }

    //查找指定id电影院信息
    @RequestMapping("/selectOneCinema")
    public List<Cinema> selectOneCinema(HttpServletRequest request){
        String cinema_id = request.getParameter("cinema_id");

        List<Cinema> oneCinema = cinemaDao.selectOneCinema(cinema_id);
        return oneCinema;
    }

    //添加电影院
    @RequestMapping("/addOneCinema")
    public List<Cinema> AddOneCinema(HttpServletRequest request, @RequestBody Cinema cinema){
        List<Cinema> NewCinema = cinemaDao.addOneCinema(cinema.getCinema_id(),cinema.getCinema_name(),
                cinema.getCinema_address(),cinema.getCinema_income());
        System.out.println("NewCinema:"+NewCinema);
        return NewCinema;
    }

    //按指定id更新电影院信息
    @RequestMapping("/updateOneCinema")
    public  List<Cinema> UpdateOneCinema(HttpServletRequest request, @RequestBody Cinema cinema){
        List<Cinema> updateCinema = cinemaDao.updateOneCinema(cinema.getCinema_id(),cinema.getCinema_name(),cinema.getCinema_address());
        System.out.println("updateCinema:"+updateCinema);
        return updateCinema;
    }

    //按指定id删除电影院信息
    @RequestMapping("/deleteOneCinema")
    public List<Cinema> DeleteOneCinema(HttpServletRequest request, @RequestBody Cinema cinema) throws ParseException {
        List<Cinema> deleteCinema = cinemaDao.deleteOneCinema(cinema.getCinema_id());
        System.out.println("updateCinema:"+deleteCinema);
        return deleteCinema;
    }
}

