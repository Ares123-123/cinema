package com.example.demo.dao;

import com.example.demo.bean.Film;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmDao {
    //查询电影信息
    List<Film> selectAllFilm();

    //添加电影
    List<Film> addFilm(@Param("film_id")String film_id, @Param("film_name")String film_name,
                              @Param("film_score")float film_score, @Param("film_rank")int film_rank,
                              @Param("film_style")String film_style, @Param("director")String director,
                              @Param("actor")String actor,@Param("film_introduction")String film_introduction,
                              @Param("film_picture_url")String film_picture_url);
    //删除一部电影
    List<Film> deleteOneFilm(@Param("film_id")String film_id);

    //查找指定id的电影
    List<Film> selectOneFilm(@Param("film_id")String film_id);

    //编辑指定id的电影
    List<Film> updateOneFilm(@Param("film_id")String film_id,@Param("film_name")String film_name,@Param("film_score")float film_score,
                             @Param("film_rank")int film_rank,@Param("film_style")String film_style,
                             @Param("director")String director,@Param("actor")String actor);
}
