package com.example.dam.serflix.Controller.Managers;

import com.example.dam.serflix.Model.Request;

import java.util.List;

/**
 * Created by DAM on 23/2/17.
 */

public interface RequestCallback {
    void onSuccess(List<Request> requestsList);
    void onSucces();

    void onFailure(Throwable t);
}
