package com.example.demo.controller;

import com.example.demo.bean.Cinema;
import com.example.demo.bean.Comment;
import com.example.demo.dao.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@RestController
public class CommentController {
    @Autowired
    CommentDao commentDao;

    //查询所有评论
    @RequestMapping("/selectAllComment")
    public List<Comment> SelectAllCinema(HttpServletRequest request){
        List<Comment> allComment = commentDao.selectAllComment();
        System.out.println("allComment:"+allComment);
        return allComment;
    }

    //按指定id删除评论信息
    @RequestMapping("/deleteOneComment")
    public List<Comment> DeleteOneComment(HttpServletRequest request,@RequestBody Comment comment){
        List<Comment> deleteComment = commentDao.deleteOneComment(comment.getComment_id());
        System.out.println("deleteComment:"+deleteComment);
        return deleteComment;
    }

    //按指定id改变评论审核状态为审核通过
    @RequestMapping("/updateOneComment_stateSuccess")
    public List<Comment> updateOneComment_stateSuccess(HttpServletRequest request,@RequestBody Comment comment){
        List<Comment> newComment_state1 = commentDao.updateOneComment_stateSuccess(comment.getComment_id());
        System.out.println("newComment_state1:"+newComment_state1);
        return newComment_state1;
    }

    //按指定id改变评论审核状态为审核未通过
    @RequestMapping("/updateOneComment_stateFail")
    public List<Comment> updateOneComment_stateFail(HttpServletRequest request,@RequestBody Comment comment){
        List<Comment> newComment_state2 = commentDao.updateOneComment_stateFail(comment.getComment_id());
        System.out.println("newComment_state2:"+newComment_state2);
        return newComment_state2;
    }
}
