package com.example.dam.serflix.Controller.Managers;

/**
 * Created by DAM on 3/5/17.
 */

public interface RegisterCallback {
    void onSuccessRegister();
    void onFailureRegister(Throwable t);
}
