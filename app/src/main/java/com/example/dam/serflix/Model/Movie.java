package com.example.dam.serflix.Model;

public class Movie {
    private String name;
    private String cast;
    private String tags;
    private String poster;
    private String description;
    private String year;

    public Movie(String title, String cast, String tags, String poster, String description, String year) {
        this.name = title;
        this.cast = cast;
        this.tags = tags;
        this.poster = poster;
        this.description = description;
        this.year = year;
    }

    public Movie() {
    }

    public String getTitle() {
        return name;
    }

    public void setTitle(String title) {
        this.name = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
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

    public String getCast() {
        return cast;
    }

    public void
    setCast(String cast) {
        this.cast = cast;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
