package com.example.demo.bean;

import java.sql.Date;
import java.sql.Time;

public class Hall {
    private String hall_id;
    private int hall_number;
    private int hall_seat_count;
    private String cinema_id;
    private String cinema_name;
    private String film_id;
    private String film_name;
    private String session_id;
    private Date session_date;
    private Time session_time;
    private float session_price;
    private int sold_number;

    public Hall() {
    }

    public Hall(String hall_id, int hall_number, int hall_seat_count, String cinema_id, String cinema_name, String film_id, String film_name, String session_id, Date session_date, Time session_time, float session_price, int sold_number) {
        this.hall_id = hall_id;
        this.hall_number = hall_number;
        this.hall_seat_count = hall_seat_count;
        this.cinema_id = cinema_id;
        this.cinema_name = cinema_name;
        this.film_id = film_id;
        this.film_name = film_name;
        this.session_id = session_id;
        this.session_date = session_date;
        this.session_time = session_time;
        this.session_price = session_price;
        this.sold_number = sold_number;
    }

    public String getHall_id() {
        return hall_id;
    }

    public int getHall_number() {
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

    public String getFilm_id() {
        return film_id;
    }

    public String getFilm_name() { return film_name; }

    public String getSession_id() { return session_id; }

    public Date getSession_date() { return session_date; }

    public Time getSession_time() { return session_time; }

    public float getSession_price() { return session_price; }

    public int getSold_number() { return sold_number; }

    public void setHall_id(String hall_id) { this.hall_id = hall_id; }

    public void setHall_number(int hall_number) {
        this.hall_number = hall_number;
    }

    public void setHall_seat_count(int hall_seat_count) {
        this.hall_seat_count = hall_seat_count;
    }

    public void setCinema_id(String cinema_id) {
        this.cinema_id = cinema_id;
    }

    public void setCinema_name(String cinema_name) {
        this.cinema_name = cinema_name;
    }

    public void setFilm_id(String film_id) { this.film_id = film_id; }

    public void setFilm_name(String film_name) { this.film_name = film_name; }

    public void setSession_id(String session_id) { this.session_id = session_id; }

    public void setSession_date(Date session_date) { this.session_date = session_date; }

    public void setSession_time(Time session_time) { this.session_time = session_time; }

    public void setSession_price(float session_price) { this.session_price = session_price; }

    public void setSold_number(int sold_number) { this.sold_number = sold_number; }

    @Override
    public String toString() {
        return "Hall{" +
                "hall_id='" + hall_id + '\'' +
                ", hall_number=" + hall_number +
                ", hall_seat_count=" + hall_seat_count +
                ", cinema_id='" + cinema_id + '\'' +
                ", cinema_name='" + cinema_name + '\'' +
                ", film_id='" + film_id + '\'' +
                ", film_name='" + film_name + '\'' +
                ", session_id='" + session_id + '\'' +
                ", session_date=" + session_date +
                ", session_time=" + session_time +
                ", session_price=" + session_price +
                ", sold_number=" + sold_number +
                '}';
    }
}
