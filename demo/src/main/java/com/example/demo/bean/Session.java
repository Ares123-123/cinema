package com.example.demo.bean;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.sql.Date;
import java.sql.Time;


public class Session {
    private String session_id;
    private Date session_date;
    private Time session_time;
    private float session_price;
    private int sold_number;
    private String hall_id;
    private String hall_number;
    private int hall_seat_count;
    private String cinema_id;
    private String cinema_name;
    private String cinema_address;
    private String film_id;
    private String film_name;

    public String getSession_id() {
        return session_id;
    }

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd") //出参
    public Date getSession_date() {
        return session_date;
    }

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    public Time getSession_time() {
        return session_time;
    }

    public float getSession_price() {
        return session_price;
    }

    public int getSold_number() {
        return sold_number;
    }

    public String getHall_id() {
        return hall_id;
    }

    public String getHall_number() {
        return hall_number;
    }

    public int getHall_seat_count() {
        return hall_seat_count;
    }

    public String getCinema_id() {
        return cinema_id;
    }

    public String getCinema_name() {
        return cinema_name;
    }

    public String getCinema_address() { return cinema_address; }

    public String getFilm_id() {
        return film_id;
    }

    public String getFilm_name() {
        return film_name;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public void setSession_date(Date session_date) {
        this.session_date = session_date;
    }

    public void setSession_time(Time session_time) {
        this.session_time = session_time;
    }

    public void setSession_price(float session_price) {
        this.session_price = session_price;
    }

    public void setSold_number(int sold_number) {
        this.sold_number = sold_number;
    }

    public void setHall_seat_count(int hall_seat_count) {
        this.hall_seat_count = hall_seat_count;
    }

    public void setHall_id(String hall_id) {
        this.hall_id = hall_id;
    }

    public void setHall_number(String hall_number) {
        this.hall_number = hall_number;
    }

    public void setCinema_id(String cinema_id) {
        this.cinema_id = cinema_id;
    }

    public void setCinema_name(String cinema_name) {
        this.cinema_name = cinema_name;
    }

    public void setCinema_address(String cinema_address) { this.cinema_address = cinema_address; }

    public void setFilm_id(String film_id) {
        this.film_id = film_id;
    }

    public void setFilm_name(String film_name) {
        this.film_name = film_name;
    }

    @Override
    public String toString() {
        return "Session{" +
                "session_id='" + session_id + '\'' +
                ", session_date=" + session_date +
                ", session_time=" + session_time +
                ", session_price=" + session_price +
                ", sold_number=" + sold_number +
                ", hall_id='" + hall_id + '\'' +
                ", hall_number='" + hall_number + '\'' +
                ", hall_seat_count=" + hall_seat_count +
                ", cinema_id='" + cinema_id + '\'' +
                ", cinema_name='" + cinema_name + '\'' +
                ", cinema_address='" + cinema_address + '\'' +
                ", film_id='" + film_id + '\'' +
                ", film_name='" + film_name + '\'' +
                '}';
    }
}
