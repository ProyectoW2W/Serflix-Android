package com.example.dam.serflix.Controller.Managers;

import android.content.Context;
import android.widget.Toast;

import com.example.dam.serflix.Controller.Services.RegisterService;
import com.example.dam.serflix.Model.UserDTO;
import com.example.dam.serflix.Util.CustomProperties;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DAM on 3/5/17.
 */

public class RegisterManager {

    private static RegisterManager ourInstance;
    private Retrofit retrofit;
    private Context context;
    private RegisterService registerService;

    private RegisterManager(Context cntxt) {
        context = cntxt;
        retrofit = new Retrofit.Builder()
                .baseUrl(CustomProperties.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())

                .build();

        registerService = retrofit.create(RegisterService.class);
    }

    public static RegisterManager getInstance(Context cntxt) {
        if (ourInstance == null) {
            ourInstance = new RegisterManager(cntxt);
        }

        ourInstance.context = cntxt;

        return ourInstance;
    }

    /* POST - REGISTER ACCOUNT */

    public synchronized void registerAccount(final RegisterCallback registerCallback, UserDTO userDTO) {
        Call<ResponseBody> call = registerService.registerAccount(userDTO);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int code = response.code();

                if (code == 200 || code == 201) {
                    Toast.makeText(context, "User created", Toast.LENGTH_LONG);
                    registerCallback.onSuccessRegister();

                } else {
                    registerCallback.onFailureRegister(new Throwable("ERROR" + code + ", " + response.raw().message()));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                registerCallback.onFailureRegister(t);
            }
        });
    }
}
