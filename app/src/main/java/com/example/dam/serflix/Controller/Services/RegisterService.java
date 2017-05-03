package com.example.dam.serflix.Controller.Services;

import com.example.dam.serflix.Model.UserDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by DAM on 3/5/17.
 */

public interface RegisterService {
    @POST("/api/users")
    Call<Void> registerAccount(
            @Body UserDTO userDTO
    );
}
