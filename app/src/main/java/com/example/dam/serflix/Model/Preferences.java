package com.example.dam.serflix.Model;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Preferences implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String type;

    private String name;

    private Integer value;

    private Set<MovieRecommendation> movieRecomendations = new HashSet<>();

    private Set<Request> requests = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public Preferences type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Preferences name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public Preferences value(Integer value) {
        this.value = value;
        return this;
    }

    public void setValue(Integer value) {
        this.value = value;
    }



    public Set<MovieRecommendation> getMovieRecomendations() {
        return movieRecomendations;
    }

    public Preferences movieRecomendations(Set<MovieRecommendation> movieRecomendations) {
        this.movieRecomendations = movieRecomendations;
        return this;
    }

    public Preferences addMovieRecomendation(MovieRecommendation movieRecomendation) {
        movieRecomendations.add(movieRecomendation);
        movieRecomendation.getPreferences().add(this);
        return this;
    }

    public Preferences removeMovieRecomendation(MovieRecommendation movieRecomendation) {
        movieRecomendations.remove(movieRecomendation);
        movieRecomendation.getPreferences().remove(this);
        return this;
    }

    public void setMovieRecomendations(Set<MovieRecommendation> movieRecomendations) {
        this.movieRecomendations = movieRecomendations;
    }

    public Set<Request> getRequests() {
        return requests;
    }

    public Preferences requests(Set<Request> requests) {
        this.requests = requests;
        return this;
    }

    public void setRequests(Set<Request> requests) {
        this.requests = requests;
    }

    @Override
    public String toString() {
        return "Preferences{" +
                "id=" + id +
                ", type='" + type + "'" +
                ", name='" + name + "'" +
                ", value='" + value + "'" +
                '}';
    }
}
