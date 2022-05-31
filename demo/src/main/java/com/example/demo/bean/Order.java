package com.example.demo.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;
import java.sql.Time;

public class Order {
    private String order_id;
    private Date order_date;
    private Time order_time;
    private String food_order_id;
    private String user_id;
    private String user_name;
    private String film_id;
    private String film_name;
    private String cinema_id;
    private String cinema_name;
    private String cinema_address;
    private String hall_id;
    private String hall_number;
    private String session_id;
    private String session_price;
    private int seat_number;
    private int total_price;
    private String state;

    public String getOrder_id() {
        return order_id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public Time getOrder_time() {
        return order_time;
    }

    public String getFood_order_id() {
        return food_order_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getFilm_id() {
        return film_id;
    }

    public String getFilm_name() {
        return film_name;
    }

    public String getCinema_id() {
        return cinema_id;
    }

    public String getCinema_name() {
        return cinema_name;
    }

    public String getCinema_address() {
        return cinema_address;
    }

    public String getHall_id() {
        return hall_id;
    }

    public String getHall_number() {
        return hall_number;
    }

    public String getSession_id() {
        return session_id;
    }

    public String getSession_price() {
        return session_price;
    }

    public int getSeat_number() {
        return seat_number;
    }

    public int getTotal_price() {
        return total_price;
    }

    public String getState() { return state; }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd") //出参
    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    public void setOrder_time(Time order_time) {
        this.order_time = order_time;
    }

    public void setFood_order_id(String food_order_id) {
        this.food_order_id = food_order_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setFilm_id(String film_id) {
        this.film_id = film_id;
    }

    public void setFilm_name(String film_name) {
        this.film_name = film_name;
    }

    public void setCinema_id(String cinema_id) {
        this.cinema_id = cinema_id;
    }

    public void setCinema_name(String cinema_name) {
        this.cinema_name = cinema_name;
    }

    public void setCinema_address(String cinema_address) {
        this.cinema_address = cinema_address;
    }

    public void setHall_id(String hall_id) {
        this.hall_id = hall_id;
    }

    public void setHall_number(String hall_number) {
        this.hall_number = hall_number;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public void setSession_price(String session_price) {
        this.session_price = session_price;
    }

    public void setSeat_number(int seat_number) {
        this.seat_number = seat_number;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public void setState(String state) { this.state = state; }

    @Override
    public String toString() {
        return "Order{" +
                "order_id='" + order_id + '\'' +
                ", order_date=" + order_date +
                ", order_time=" + order_time +
                ", food_order_id='" + food_order_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", film_id='" + film_id + '\'' +
                ", film_name='" + film_name + '\'' +
                ", cinema_id='" + cinema_id + '\'' +
                ", cinema_name='" + cinema_name + '\'' +
                ", cinema_address='" + cinema_address + '\'' +
                ", hall_id='" + hall_id + '\'' +
                ", hall_number='" + hall_number + '\'' +
                ", session_id='" + session_id + '\'' +
                ", session_price='" + session_price + '\'' +
                ", seat_number=" + seat_number +
                ", total_price=" + total_price +
                ", state='" + state + '\'' +
                '}';
    }
}
