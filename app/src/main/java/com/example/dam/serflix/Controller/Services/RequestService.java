package com.example.dam.serflix.Controller.Services;

import com.example.dam.serflix.Model.Request;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by DAM on 23/2/17.
 */

public interface RequestService {

    @POST("api/requests")
    Call<Request> createRequest(
            @Header("Authorization") String Authorization,
            @Body Request request);

}
