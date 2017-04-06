package com.example.dam.serflix.Controller.Services;

import com.example.dam.serflix.Model.MovieRecommendation;
import com.example.dam.serflix.Model.Request;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by Nando on 3/4/17.
 */

public interface RecommendationService {
    @GET("api/requests/{id}/recommendations/movie")
    Call<List<MovieRecommendation>> getMovieRecommendation(
            @Header("Authorization") String Authorization,
            @Body Request request);
}
