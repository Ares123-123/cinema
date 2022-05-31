package com.example.demo.wx_api;

import com.example.demo.bean.Cinema;
import com.example.demo.bean.Session;
import com.example.demo.dao.CinemaDao;
import com.example.demo.dao.SessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class wx_session {
    @Autowired
    SessionDao sessionDao;

    //查找指定id的电影
    @RequestMapping("/wx_selectOneCinema_Session")
    public List<Session> wx_selectOneCinema_Session(HttpServletRequest request){
        String wx_cinema_id = request.getParameter("cinema_id");
        List<Session> oneCinema_Session = sessionDao.selectOneCinema_session(wx_cinema_id);
        return oneCinema_Session;
    }

    //根据电影id查找排片，按照排片查找电影院
    @RequestMapping("/wx_selectOneFilm_Cinema")
    public List<Session> wx_selectOneFilm_Cinema(HttpServletRequest request){
        String wx_film_id = request.getParameter("film_id");
        List<Session> oneFilm_Cinema = sessionDao.wx_selectOneFilm_Cinema(wx_film_id);
        return oneFilm_Cinema;
    }
    //查找指定id场次
    @RequestMapping("/wx_selectOneSession_Hall")
    public List<Session> wx_selectOneSession_Hall(HttpServletRequest request){
        String wx_session_id = request.getParameter("session_id");
        List<Session> oneSession_Hall = sessionDao.selectOneSession_Hall(wx_session_id);
        return oneSession_Hall;
    }

    //按照场次id查找对应场次所有信息
    @RequestMapping("/wx_selectOneSession")
    public List<Session> wx_selectOneSession(HttpServletRequest request){
        String wx_session_id = request.getParameter("session_id");
        List<Session> oneSession = sessionDao.wx_selectOneSession(wx_session_id);
        return oneSession;
    }
}
