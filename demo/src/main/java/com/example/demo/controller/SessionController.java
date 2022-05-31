package com.example.demo.controller;

import com.example.demo.bean.Session;
import com.example.demo.dao.SessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class SessionController {
    @Autowired
    SessionDao sessionDao;

    //查询所有场次信息
    @RequestMapping("/selectAllSession")
    public List<Session> SelectAllSession(HttpServletRequest request){
        List<Session> allSession = sessionDao.selectAllSession();
        return allSession;
    }

    //按id值更新场次
    @RequestMapping("/updateOneSession")
    public List<Session> UpdateOneSession(HttpServletRequest request, @RequestBody Session session){
        List<Session> updateSession = sessionDao.updateOneSession(session.getSession_id(),session.getSession_date(),
                                                                   session.getSession_time(),session.getSession_price(),
                                                                   session.getSold_number());
        System.out.println("updateSession:"+updateSession);
        return updateSession;
    }

    //按电影院id查找场次
    @RequestMapping("/selectOneCinema_session")
    public List<Session> SelectOneCinema_session(HttpServletRequest request, @RequestBody Session session){
        List<Session> OneCinema_session = sessionDao.selectOneCinema_session(session.getCinema_id());
        System.out.println("OneCinema_session:"+OneCinema_session);
        return OneCinema_session;
    }

    //添加场次
    @RequestMapping("/addOneSession")
    List<Session> AddOneSession(HttpServletRequest request, @RequestBody Session session){
        List<Session> addSession = sessionDao.addOneSession(session.getSession_id(),session.getSession_date(),session.getSession_time(),
                session.getSession_price(),session.getSold_number(),session.getHall_id(),session.getFilm_id());
        System.out.println("addSession:"+addSession);
        return addSession;
    }

    //根据场次id查影院
    @RequestMapping("/selectOneSession_Hall")
    List<Session> SelectOneSession_Hall(HttpServletRequest request, @RequestBody Session session){
        List<Session> oneSession_Hall = sessionDao.selectOneSession_Hall(session.getSession_id());
        System.out.println("oneSession_Hall:"+oneSession_Hall);
        return oneSession_Hall;
    }

}
