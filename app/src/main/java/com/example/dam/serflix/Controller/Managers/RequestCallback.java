package com.example.dam.serflix.Controller.Managers;

import com.example.dam.serflix.Model.MovieRecommendation;
import com.example.dam.serflix.Model.Request;
import com.example.dam.serflix.Model.RequestDTO;

import java.util.List;

/**
 * Created by DAM on 23/2/17.
 */

public interface RequestCallback {
    void onSuccess(List<Request> requestsList);
    void onSuccess(RequestDTO request);
    void onSuccessMR(List<MovieRecommendation>  movieRecommendations);
    //void onSuccessRDTO(RequestDTO requestDTO);

    void onFailure(Throwable t);
}
