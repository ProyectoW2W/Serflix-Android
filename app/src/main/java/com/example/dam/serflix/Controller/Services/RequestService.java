package com.example.dam.serflix.Controller.Services;

import com.example.dam.serflix.Model.MovieRecommendation;
import com.example.dam.serflix.Model.Request;
import com.example.dam.serflix.R;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by DAM on 23/2/17.
 */

public interface RequestService {

    @POST("api/newrequest")
    Call<Request> createNewRequest(
            @Header("Authorization") String Authorization,
            @Body Request request);

    @GET("/requests/{id}/recommendations")
    Call<MovieRecommendation> getRecomendations(
            @Path("id") Long id,
            @Header("Authorization") String Authorization);

}
