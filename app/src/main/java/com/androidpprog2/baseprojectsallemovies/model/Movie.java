package com.androidpprog2.baseprojectsallemovies.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String title;
    private int year;
    private List<String> cast;
    private List<String> genres;
    private int length;
    private double review;
    private String language;
    private String extract;
    private String thumbnail;

    public Movie(){
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

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<String> getCast() {
        return cast;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public double getReview() {
        return review;
    }

    public void setReview(double review) {
        this.review = review;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getExtract() {
        return extract;
    }

    public void setExtract(String extract) {
        this.extract = extract;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}
