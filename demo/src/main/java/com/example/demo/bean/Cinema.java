package com.example.demo.bean;

public class Cinema {
    private String cinema_id;
    private String cinema_name;
    private String cinema_address;
    private int cinema_income;
    private double longitude;
    private double latitude;

    public Cinema() {
    }

    public Cinema(String cinema_id, String cinema_name, String cinema_address, int cinema_income, double longitude, double latitude) {
        this.cinema_id = cinema_id;
        this.cinema_name = cinema_name;
        this.cinema_address = cinema_address;
        this.cinema_income = cinema_income;
        this.longitude = longitude;
        this.latitude = latitude;
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

    public int getCinema_income() {
        return cinema_income;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
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

    public void setCinema_income(int cinema_income) {
        this.cinema_income = cinema_income;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "cinema_id='" + cinema_id + '\'' +
                ", cinema_name='" + cinema_name + '\'' +
                ", cinema_address='" + cinema_address + '\'' +
                ", cinema_income=" + cinema_income +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
