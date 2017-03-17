package com.example.ahmed.movie_app;

import java.io.Serializable;

/**
 * Created by ahmed on 10/10/2016.
 */

public class Movie implements Serializable {
    private String title;
    private String imagepath;
    private int duration;
    private double rate;
    int id;

    public Movie(String title, String imagepath, double rate, int id, String description, String year) {
        this.title = title;
        this.imagepath = imagepath;

        this.rate = rate;
        this.id = id;
        this.description = description;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String description;

    public Movie() {
    }

    public Movie(String title, String imagepath, double rate, String description, String year) {
        this.title = title;
        this.imagepath = imagepath;
        this.rate = rate;
        this.description = description;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageId() {
        return imagepath;
    }

    public void setImageId(String imagepath) {
        this.imagepath = imagepath;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    private String year;

}
