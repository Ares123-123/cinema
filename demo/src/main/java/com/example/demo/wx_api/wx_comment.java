package com.example.demo.wx_api;

import com.example.demo.bean.Comment;
import com.example.demo.dao.CommentDao;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class wx_comment {
    @Autowired
    CommentDao commentDao;

    //查找指定id的电影
    @RequestMapping("/addOneComment")
    public String addOneComment(HttpServletRequest request) throws ParseException {

        //实例化
        Comment comment1 = new Comment();
        //类型转换

        //日期转换
        SimpleDateFormat showDate = new SimpleDateFormat("yyyy-MM-dd");
        String comment_date = request.getParameter("comment_date");
        Date date = showDate.parse(comment_date);
        java.sql.Date date1 = new java.sql.Date(date.getTime());

        //时间转换
        SimpleDateFormat showTime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String comment_time = request.getParameter("comment_time");
        System.out.println("comment_time:"+comment_time);
        Date time = showTime.parse(comment_time);
        System.out.println("time:"+time);
        java.sql.Time time1 = new java.sql.Time(time.getTime());

        //获取小程序的数据
        String user_id = request.getParameter("user_id");
        String comment_id = request.getParameter("comment_id");
        String comment_content = request.getParameter("comment_content");
        String film_id = request.getParameter("film_id");
        String approval_state = request.getParameter("approval_state");

        comment1.setComment_id(comment_id);
        comment1.setComment_content(comment_content);
        comment1.setComment_date(date1);
        comment1.setComment_time(time1);
        comment1.setFilm_id(film_id);
        comment1.setUser_id(user_id);
        comment1.setApproval_state(approval_state);
        System.out.println(comment1);

        //数据库操作
        List<Comment> addComment = commentDao.addOneComment(comment1);

        //字符串转换为List类型
        String str="asadssaf";
        List<String> list = new ArrayList<>();
        list = Arrays.asList(str);
        System.out.println(list);
        boolean b = list instanceof List;
        System.out.println("b:"+b);

      return "OK";
    }

    //查找指定电影的评论
    @RequestMapping("/wx_showOneFilm_Comment")
    public List<Comment> showOneCinema_Comment(HttpServletRequest request){
        //实例化对象
        Comment comment2 = new Comment();

        //获取小程序film_id
        String wx_film_id = request.getParameter("film_id");


        //数据库操作
        List<Comment> showComment = commentDao.wx_showOneFilm_Comment(wx_film_id);

        return showComment;
    }

    //查找指定用户的评论
    @RequestMapping("/wx_showOneUser_Comment")
    public List<Comment> wx_showOneUser_Comment(HttpServletRequest request){

        String user_id = request.getParameter("user_id");

        List<Comment> showOneComment = commentDao.wx_showOneUser_Comment(user_id);

        return showOneComment;
    }
}
