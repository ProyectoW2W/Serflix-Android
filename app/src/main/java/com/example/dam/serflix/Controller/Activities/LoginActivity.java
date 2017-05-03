package com.example.dam.serflix.Controller.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
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
import com.example.dam.serflix.Model.MovieRecommendation;
import com.example.dam.serflix.Model.Request;
import com.example.dam.serflix.Model.UserToken;
import com.example.dam.serflix.Model.enumeration.Company;
import com.example.dam.serflix.Model.enumeration.Type;
import com.example.dam.serflix.R;

import java.util.Date;
import java.util.List;


public class LoginActivity extends AppCompatActivity implements LoginCallback {
    EditText username;
    EditText pass;

    private LocationManager locationManager;
    private LocationListener locationListener;
    String latlon = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        username = (EditText)findViewById(R.id.editEmail);
        pass = (EditText)findViewById(R.id.editPass);

        //Obtenemos latitud y longitud al iniciar la aplicacion para poderla obtener mas rapidamente y poder pasarla correctamente a RequestActivity!!!!!!!
        //HAY QUE LLAMAR DOS VECES A ObtainLatLon PARA QUE RELLENE CON LATLON Y NO PETE EL LOCATION MANAGER
        ObtainLatLon();
        locationManager.requestLocationUpdates("gps", 5000, 0, locationListener);
        ObtainLatLon();

        Button login_btn = (Button) findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v){
                if (username.getText().toString().equals("")|| pass.getText().toString().equals("") )
                    System.out.println("LOGIN NULL");
                else {
                    attemptLogin();
                }
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

                //PASAMOS A REQUEST ACTIVITY LOS DATOS!!!!!!
                Intent intent = new Intent(LoginActivity.this, RequestActivity.class);
                intent.putExtra("latlon", latlon);
                startActivity(intent);
            }
        });
    }

    private void attemptLogin() {
        String usu = username.getText().toString();
        String password = pass.getText().toString();
        UserLoginManager.getInstance().performLogin(usu, password, LoginActivity.this);
    }


    @Override
    public void onSuccess(UserToken userToken) {
        Intent intent = new Intent(LoginActivity.this, RequestActivity.class);
        Toast.makeText(this, "Correcto! Iniciando sesion", Toast.LENGTH_LONG).show();
        intent.putExtra("latlon", latlon);
        startActivity(intent);
        finish();
    }


    @Override
    public void onFailure(Throwable t) {
        Log.e("LoginActivity->", "performLogin->onFailure ERROR " + t.getMessage());
        Toast.makeText(this, "Credenciales incorectos", Toast.LENGTH_LONG).show();
    }



    private void ObtainLatLon() {

        locationManager = (LocationManager) getSystemService((LOCATION_SERVICE));
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(android.location.Location location) {
                Log.d("Coordenadas", "Latitud = " +location.getLatitude() + " , Longitud = " + location.getLongitude());
                latlon = String.valueOf(location.getLatitude()+","+location.getLongitude());
                System.out.println(latlon + " --------- latlon1");
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.INTERNET
                }, 10);
            }
            return;
        }else{
            //configureButton();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 10:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    //configureButton();
                return;
        }
    }
}
