package com.example.dam.serflix.Model;

import com.example.dam.serflix.Model.enumeration.RecomendationResult;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class MovieRecommendation implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private RecomendationResult recomendationResult;

    private Movie movieDTO;

    private RequestDTO request;

    private Set<Preferences> preferences = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RecomendationResult getRecomendationResult() {
        return recomendationResult;
    }

    public MovieRecommendation recomendationResult(RecomendationResult recomendationResult) {
        this.recomendationResult = recomendationResult;
        return this;
    }

    public void setRecomendationResult(RecomendationResult recomendationResult) {
        this.recomendationResult = recomendationResult;
    }

    public Movie getMovieDTO() {
        return movieDTO;
    }

    public MovieRecommendation movieDTO(Movie movie) {
        this.movieDTO = movie;
        return this;
    }

    public void setMovieDTO(Movie movie) {
        this.movieDTO = movie;
    }

    public RequestDTO getRequest() {
        return request;
    }

    public MovieRecommendation request(RequestDTO request) {
        this.request = request;
        return this;
    }

    public void setRequest(RequestDTO request) {
        this.request = request;
    }

    public Set<Preferences> getPreferences() {
        return preferences;
    }

    public MovieRecommendation preferences(Set<Preferences> preferences) {
        this.preferences = preferences;
        return this;
    }

    public void setPreferences(Set<Preferences> preferences) {
        this.preferences = preferences;
    }

    @Override
    public String toString() {
        return "MovieRecommendation{" +
                "id=" + id +
                ", recomendationResult='" + recomendationResult + "'" +
                '}';
    }
}
