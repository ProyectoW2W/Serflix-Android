package com.example.dam.serflix.Controller.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dam.serflix.Controller.Managers.LoginCallback;
import com.example.dam.serflix.Controller.Managers.RequestCallback;
import com.example.dam.serflix.Controller.Managers.RequestManager;
import com.example.dam.serflix.Controller.Managers.UserLoginManager;
import com.example.dam.serflix.Model.Location;
import com.example.dam.serflix.Model.Request;
import com.example.dam.serflix.Model.UserToken;
import com.example.dam.serflix.Model.enumeration.Company;
import com.example.dam.serflix.Model.enumeration.Type;
import com.example.dam.serflix.R;

import java.util.Date;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements LoginCallback, RequestCallback {
    EditText username;
    EditText pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText)findViewById(R.id.editEmail);
        pass = (EditText)findViewById(R.id.editPass);



        Button login_btn = (Button) findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v){
                if (username.getText().toString().equals("")|| pass.getText().toString().equals("") )
                    System.out.println("LOGIN NULL");
                else
                    attemptLogin();
            }
        });

        TextView sign_btn = (TextView) findViewById(R.id.signUp_btn);

        sign_btn.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        Button request_btn = (Button) findViewById(R.id.request_btn);

        request_btn.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, RequestActivity.class);
                startActivity(intent);
            }
        });
    }

    private void attemptLogin() {
        String usu = username.getText().toString();
        String password = pass.getText().toString();
        usu = "admin";
        password = "admin";
        UserLoginManager.getInstance().performLogin(usu, password, LoginActivity.this);

        Request request =
                new Request(Type.MOVIE,new Date(2017,02,24),new Date(2017,02,25), Company.ALONE,null,new Location(41.5933302,1.835487));

        RequestManager.getInstance().createRequest(LoginActivity.this,request);
    }


    @Override
    public void onSuccess(UserToken userToken) {
        Intent intent = new Intent(LoginActivity.this, RequestActivity.class);
        Toast.makeText(this, "Correcto! Iniciando sesion", Toast.LENGTH_LONG).show();
        startActivity(intent);
        finish();
    }

    @Override
    public void onSuccess(List<Request> requestsList) {
        System.out.println("success request");
    }

    @Override
    public void onSucces() {

    }

    @Override
    public void onFailure(Throwable t) {
        Log.e("LoginActivity->", "performLogin->onFailure ERROR " + t.getMessage());
        Toast.makeText(this, "Credenciales incorectos", Toast.LENGTH_LONG).show();
    }
}
