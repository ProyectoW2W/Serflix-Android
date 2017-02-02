package com.example.dam.serflix.Controller.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dam.serflix.Controller.Managers.LoginCallback;
import com.example.dam.serflix.Controller.Managers.UserLoginManager;
import com.example.dam.serflix.Model.UserToken;
import com.example.dam.serflix.R;

public class LoginActivity extends AppCompatActivity implements LoginCallback {
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
        //sign_btn.setMovementMethod(LinkMovementMethod.getInstance());

        sign_btn.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
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
    }


    @Override
    public void onSuccess(UserToken userToken) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFailure(Throwable t) {
        Log.e("LoginActivity->", "performLogin->onFailure ERROR " + t.getMessage());
    }
}
