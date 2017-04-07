package com.example.dam.serflix.Controller.Managers;

import android.util.Log;

import com.example.dam.serflix.Controller.Services.RequestService;
import com.example.dam.serflix.Model.Request;
import com.example.dam.serflix.Util.CustomProperties;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DAM on 23/2/17.
 */

public class RequestManager {
    private static RequestManager ourInstance;
    private List<Request> players;
    private Retrofit retrofit;
    private RequestService requestService;

    private RequestManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(CustomProperties.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())

                .build();

        requestService = retrofit.create(RequestService.class);
    }

    public static RequestManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new RequestManager();
        }

        return ourInstance;
    }

    public synchronized void createRequest(final RequestCallback requestCallback,Request request) {
        Call<Request> call = requestService.createNewRequest(UserLoginManager.getInstance().getBearerToken(), request);
        call.enqueue(new Callback<Request>() {
            @Override
            public void onResponse(Call<Request> call, Response<Request> response) {
                int code = response.code();

                if (code == 200 || code == 201) {
                    //playerCallback.onSuccess1(apuestas1x2);
                    Log.e("Player->", "createPlayer: OK" + 100);

                } else {
                    requestCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<Request> call, Throwable t) {
                Log.e("PlayerManager->", "createPlayer: " + t);

                requestCallback.onFailure(t);
            }
        });
    }

}
