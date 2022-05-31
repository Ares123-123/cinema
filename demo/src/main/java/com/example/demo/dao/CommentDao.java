package com.example.demo.dao;

import com.example.demo.bean.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Repository
public interface CommentDao {

    //添加一条评论
    List<Comment> addOneComment(Comment comment);

    //展示一部电影的评论
    List<Comment> wx_showOneFilm_Comment(@Param("film_id")String film_id);

    //展示一个用户的评论
    List<Comment> wx_showOneUser_Comment(@Param("user_id")String user_id);

    //展示所有评论
    List<Comment> selectAllComment();

    //按指定id删除评论信息
    List<Comment> deleteOneComment(@Param("comment_id")String comment_id);

    //按指定id改变评论审核状态为审核通过
    List<Comment> updateOneComment_stateSuccess(@Param("comment_id")String comment_id);

    //按指定id改变评论审核状态为审核未通过
    List<Comment> updateOneComment_stateFail(@Param("comment_id")String comment_id);
}
