package com.example.demo.dao;
import com.example.demo.bean.Cinema;
import com.example.demo.bean.Session;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Repository
public interface SessionDao {

    //查询场次信息
    List<Session> selectAllSession();

    //按id值更新场次
    List<Session> updateOneSession(@Param("session_id")String session_id, @Param("session_date") Date session_date,
                                   @Param("session_time") Time session_time, @Param("session_price")float session_price,
                                   @Param("sold_number")int sold_number);

    //按电影院id查找场次
    List<Session> selectOneCinema_session(@Param("cinema_id")String cinema_id);

    //添加场次
    List<Session> addOneSession(@Param("session_id")String session_id, @Param("session_date") Date session_date,
                                @Param("session_time") Time session_time, @Param("session_price")float session_price,
                                @Param("sold_number")int sold_number,@Param("hall_id")String hall_id,@Param("film_id")String film_id);

    //根据电影id查找排片，按照排片查找电影院
    List<Session> wx_selectOneFilm_Cinema(@Param("film_id")String film_id);

    //根据场次id查影院
    List<Session> selectOneSession_Hall(@Param("session_id")String session_id);

    //按照场次id查找该场次所有信息
    List<Session> wx_selectOneSession(@Param("session_id")String session_id);
}
