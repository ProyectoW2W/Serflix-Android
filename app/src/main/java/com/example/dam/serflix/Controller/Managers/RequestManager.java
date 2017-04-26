package com.example.dam.serflix.Controller.Managers;

import android.util.Log;

import com.example.dam.serflix.Controller.Services.RequestService;
import com.example.dam.serflix.Model.MovieRecommendation;
import com.example.dam.serflix.Model.Request;
import com.example.dam.serflix.Model.RequestDTO;
import com.example.dam.serflix.Util.CustomProperties;

import java.util.ArrayList;
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
    private List<Request> users;
    private Retrofit retrofit;
    private RequestService requestService;
    private List<MovieRecommendation> movieRecommendations =  new ArrayList<>();
    private Request request;


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

    public Request  getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public synchronized void createRequest(final RequestCallback requestCallback, Request request) {
        //CAMBIAR A RequestDTO
        Call<RequestDTO> call = requestService.createNewRequest(UserLoginManager.getInstance().getBearerToken(), request);
        call.enqueue(new Callback<RequestDTO>() {
            @Override
            public void onResponse(Call<RequestDTO> call, Response<RequestDTO> response) {
                int code = response.code();

                if (code == 200 || code == 201) {
                    //playerCallback.onSuccess1(apuestas1x2);
                    Log.e("User->", "createUser: OK" + 100);
                    requestCallback.onSuccess(response.body());

                } else {
                    requestCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<RequestDTO> call, Throwable t) {
                Log.e("UserManager->", "createUser: " + t);

                requestCallback.onFailure(t);
            }
        });
    }

    public List<MovieRecommendation> getMovieRecommendations() {
        return movieRecommendations;
    }

    public void setMovieRecommendations(List<MovieRecommendation> movieRecommendations) {
        this.movieRecommendations = movieRecommendations;
    }

    public synchronized void getRecomendations(final RequestCallback requestCallback, long requestId) {
        Call<List<MovieRecommendation>> call = requestService.getRecomendations(requestId,UserLoginManager.getInstance().getBearerToken());
        call.enqueue(new Callback<List<MovieRecommendation>>() {
            @Override
            public void onResponse(Call<List<MovieRecommendation>> call, Response<List<MovieRecommendation>> response) {
                int code = response.code();

                if (code == 200 || code == 201) {
                    Log.e("User->", "createUser: OK" + 100);
                        movieRecommendations = response.body();
                        requestCallback.onSuccessMR(movieRecommendations);

                } else {
                    requestCallback.onFailure(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<List<MovieRecommendation>> call, Throwable t) {
                Log.e("UserManager->", "createUser: " + t);

                requestCallback.onFailure(t);
            }
        });
    }

}
