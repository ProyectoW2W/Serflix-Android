package com.example.dam.serflix.Controller.Managers;

import com.example.dam.serflix.Model.UserToken;

public interface LoginCallback {
    void onSuccess(UserToken userToken);
    void onFailure(Throwable t);
}
