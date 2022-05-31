package com.example.demo.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;
import java.sql.Time;

public class Transfer_Order {
    private String transfer_order_id;
    private Date order_date;
    private Time order_time;
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
    private int original_price;
    private int current_price;

    public String getTransfer_order_id() {
        return transfer_order_id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public Time getOrder_time() {
        return order_time;
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

    public int getOriginal_price() {
        return original_price;
    }

    public int getCurrent_price() {
        return current_price;
    }

    public void setTransfer_order_id(String transfer_order_id) {
        this.transfer_order_id = transfer_order_id;
    }
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd") //出参
    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    public void setOrder_time(Time order_time) {
        this.order_time = order_time;
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

    public void setOriginal_price(int original_price) {
        this.original_price = original_price;
    }

    public void setCurrent_price(int current_price) {
        this.current_price = current_price;
    }

    @Override
    public String toString() {
        return "Transfer_Order{" +
                "transfer_order_id='" + transfer_order_id + '\'' +
                ", order_date=" + order_date +
                ", order_time=" + order_time +
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
                ", original_price=" + original_price +
                ", current_price=" + current_price +
                '}';
    }
}
