package com.example.demo.bean;

public class Film {
    private String film_id;
    private String film_name;
    private float film_score;
    private int film_rank;
    private String film_style;
    private String director;
    private String actor;
    private String film_introduction;
    private String film_picture_url;

    public String getFilm_id() {
        return film_id;
    }

    public String getFilm_name() {
        return film_name;
    }

    public float getFilm_score() {
        return film_score;
    }

    public int getFilm_rank() {
        return film_rank;
    }

    public String getFilm_style() {
        return film_style;
    }

    public String getDirector() {
        return director;
    }

    public String getActor() {
        return actor;
    }

    public String getFilm_introduction() {
        return film_introduction;
    }

    public String getFilm_picture_url() {
        return film_picture_url;
    }

    public void setFilm_id(String film_id) {
        this.film_id = film_id;
    }

    public void setFilm_name(String film_name) {
        this.film_name = film_name;
    }

    public void setFilm_score(float film_score) {
        this.film_score = film_score;
    }

    public void setFilm_rank(int film_rank) {
        this.film_rank = film_rank;
    }

    public void setFilm_style(String film_style) {
        this.film_style = film_style;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public void setFilm_introduction(String film_introduction) {
        this.film_introduction = film_introduction;
    }

    public void setFilm_picture_url(String film_picture_url) {
        this.film_picture_url = film_picture_url;
    }

    @Override
    public String toString() {
        return "Film{" +
                "film_id=" + film_id +
                ", film_name='" + film_name + '\'' +
                ", film_score=" + film_score +
                ", film_rank=" + film_rank +
                ", film_style='" + film_style + '\'' +
                ", director='" + director + '\'' +
                ", actor='" + actor + '\'' +
                ", film_introduction='" + film_introduction + '\'' +
                ", film_picture_url='" + film_picture_url + '\'' +
                '}';
    }
}
