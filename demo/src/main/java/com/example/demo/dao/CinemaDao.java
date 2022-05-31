package com.example.demo.dao;

import com.example.demo.bean.Cinema;
import com.example.demo.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaDao {

    //查询电影院信息
     List<Cinema> selectAllCinema();

    //查找指定id电影院信息
    List<Cinema>  selectOneCinema(@Param("cinema_id")String cinema_id);

     //添加电影院信息
     List<Cinema> addOneCinema(@Param("cinema_id")String cinema_id,@Param("cinema_name")String cinema_name,
                              @Param("cinema_address")String cinema_address,@Param("cinema_income")int cinema_income);
     //按指定id更新电影院信息
    List<Cinema> updateOneCinema(@Param("cinema_id")String cinema_id,@Param("cinema_name")String cinema_name,
                                 @Param("cinema_address")String cinema_address);

    //按指定id删除电影院信息
    List<Cinema> deleteOneCinema(@Param("cinema_id")String cinema_id);

    //电影院收益
    List<Cinema> wx_cinemaIncome(Cinema cinema);
}
