package com.androidpprog2.baseprojectsallemovies.model;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {
    private String title;
    private int year;
    private List<String> cast;
    private List<String> genres;
    private int length;
    private double review;
    private String language;
    private String extract;
    private String thumbnail;

    public Movie(String title, List<String> genres, double review, int length, String thumbnail) {
        this.title = title;
        this.genres = genres;
        this.review = review;
        this.length = length;
        this.thumbnail = thumbnail;
    }

    public Movie(String title, int year, List<String> cast,
                 List<String> genres, int length,
                 double review, String language,
                 String extract, String thumbnail) {
        this.title = title;
        this.year = year;
        this.cast = cast;
        this.genres = genres;
        this.length = length;
        this.review = review;
        this.language = language;
        this.extract = extract;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public List<String> getCast() {
        return cast;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getLengthAsString() {
        int hours = length / 60;
        int minutes = length % 60;
        return hours + "h " + minutes + "min";
    }

    public String getReview() {
        return review + "/10";
    }

    public String getLanguage() {
        return language;
    }

    public String getExtract() {
        return extract;
    }

    public String getThumbnail() {
        return thumbnail;
    }

}
