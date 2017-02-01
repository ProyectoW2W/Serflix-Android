package com.example.dam.serflix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.dam.serflix.R.id.sign_btn;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login_btn = (Button) findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, RequestActivity.class);
                startActivity(intent);
            }
        });

        TextView sign_btn = (TextView) findViewById(R.id.sign_btn);
        //sign_btn.setMovementMethod(LinkMovementMethod.getInstance());

        sign_btn.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, SignActivity.class);
                startActivity(intent);
            }
        });
    }
}
