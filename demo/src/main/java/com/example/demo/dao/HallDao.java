package com.example.demo.dao;

import com.example.demo.bean.Hall;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HallDao {

    //查询所有电影院影厅信息
    List<Hall> selectAllHall();

    //查询一家电影院的影厅
    List<Hall> selectOneCinema_hall(@Param("cinema_id")String cinema_id);

    //添加对应电影院的影厅
    List<Hall> addCinema_hall(@Param("hall_id")String hall_id,@Param("hall_number")int hall_number,
                                 @Param("hall_seat_count")int hall_seat_count,
                                 @Param("cinema_id")String cinema_id);

    //按对于id更新影厅信息
    List<Hall> updateOneHall(@Param("hall_id")String hall_id,@Param("hall_number")int hall_number,
                              @Param("hall_seat_count")int hall_seat_count);

    //查找指定影厅id的排片详情
    List<Hall> selectOneHall(@Param("hall_id")String hall_id);
}
