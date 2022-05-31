package com.example.demo.wx_api;

import com.example.demo.bean.Cinema;
import com.example.demo.dao.CinemaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class wx_cinema {
    @Autowired
    CinemaDao cinemaDao;

    //电影院收益
    @RequestMapping("/wx_cinemaIncome")
    public List<Cinema> wx_cinemaIncome(HttpServletRequest request) {
        //实例化
        Cinema cinemaIncome = new Cinema();

        String cinema_id = request.getParameter("cinema_id");
        int cinema_income = Integer.valueOf(request.getParameter("cinema_income"));

        //添加实例
        cinemaIncome.setCinema_id(cinema_id);
        cinemaIncome.setCinema_income(cinema_income);

        List<Cinema> income = cinemaDao.wx_cinemaIncome(cinemaIncome);
        return income;
    }

}
