package com.example.demo.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;
import java.sql.Time;

public class Comment {
    private String comment_id;
    private String comment_content;
    private Date comment_date;
    private Time comment_time;
    private String film_id;
    private String film_name;
    private String user_id;
    private String user_name;
    private String approval_state;

    public String getComment_id() {
        return comment_id;
    }

    public String getComment_content() {
        return comment_content;
    }

    //@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd") //出参
    public Date getComment_date() {
        return comment_date;
    }

    //@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    public Time getComment_time() {
        return comment_time;
    }

    public String getFilm_id() {
        return film_id;
    }

    public String getFilm_name() { return film_name; }

    public String getUser_name() { return user_name; }

    public String getUser_id() {
        return user_id;
    }

    public String getApproval_state() { return approval_state; }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public void setComment_date(Date comment_date) {
        this.comment_date = comment_date;
    }

    public void setComment_time(Time comment_time) {
        this.comment_time = comment_time;
    }

    public void setFilm_id(String film_id) {
        this.film_id = film_id;
    }

    public void setFilm_name(String film_name) { this.film_name = film_name; }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setUser_name(String user_name) { this.user_name = user_name; }

    public void setApproval_state(String approval_state) { this.approval_state = approval_state; }

    @Override
    public String toString() {
        return "Comment{" +
                "comment_id='" + comment_id + '\'' +
                ", comment_content='" + comment_content + '\'' +
                ", comment_date=" + comment_date +
                ", comment_time=" + comment_time +
                ", film_id='" + film_id + '\'' +
                ", film_name='" + film_name + '\'' +
                ", user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", approval_state='" + approval_state + '\'' +
                '}';
    }
}
