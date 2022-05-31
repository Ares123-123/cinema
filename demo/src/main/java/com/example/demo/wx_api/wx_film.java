package com.example.demo.wx_api;

import com.example.demo.bean.Film;
import com.example.demo.dao.FilmDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class wx_film{
    @Autowired
    FilmDao filmDao;

    //查找指定id的电影
    @RequestMapping("/wx_selectOneFilm")
    public List<Film> SelectOneFilm(HttpServletRequest request){
        String wx_film_id = request.getParameter("film_id");
        List<Film> oneFilm = filmDao.selectOneFilm(wx_film_id);
        return oneFilm;
    }

    //查询所有电影
    @RequestMapping("/wx_selectAllFilm")
    public List<Film> SelectAllFilm(HttpServletRequest request){
        List<Film> allFilm = filmDao.selectAllFilm();
        System.out.println("allFilm:"+allFilm);
        return allFilm;
    }

}
