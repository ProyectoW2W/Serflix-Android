package com.example.dam.serflix.Controller.Activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
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
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.Date;
import java.util.List;


public class LoginActivity extends AppCompatActivity implements LoginCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    EditText username;
    EditText pass;

    private LocationManager locationManager;
    private LocationListener locationListener;
    protected GoogleApiClient mGoogleApiClient;
    protected android.location.Location mLastLocation;
    String latlon = "";
    private static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 101;
    private static final int MY_PERMISSIONS_REQUEST_COARSE_LOCATION = 102;
    private boolean permissionIsGranted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.editEmail);
        pass = (EditText) findViewById(R.id.editPass);
        statusCheck();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_FINE_LOCATION);
            }else{
                permissionIsGranted = true;
            }
        }
        if (permissionIsGranted){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 0, locationListener);
        }

            locationManager = (LocationManager) getSystemService((LOCATION_SERVICE));
            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(android.location.Location location) {
                    Log.d("Coordenadas", "Latitud = " + location.getLatitude() + " , Longitud = " + location.getLongitude());
                    latlon = String.valueOf(location.getLatitude() + "," + location.getLongitude());
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {
                    boolean gps_enabled = false;
                    boolean network_enabled = false;

                    try {
                        gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                    } catch (Exception ex) {
                    }

                    try {
                        network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
                    } catch (Exception ex) {
                    }

                    if (!gps_enabled && !network_enabled) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }
                }
            };

        Button login_btn = (Button) findViewById(R.id.login_btn);
        TextView sign_btn = (TextView) findViewById(R.id.signUp_btn);


        sign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                intent.putExtra("latlon", latlon);
                startActivity(intent);
            }
        });

        /**
         * Login desde el boton
         */
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("") || pass.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Login is null", Toast.LENGTH_LONG).show();
                    System.out.println("LOGIN NULL");
                } else {
                    attemptLogin();
                }
            }
        });

        /**
         * login desde la tecla intro del teclado del movil
         */
        pass.setImeActionLabel("Pass?", KeyEvent.KEYCODE_ENTER);
        pass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_NULL || (keyEvent != null && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (id == EditorInfo.IME_ACTION_DONE)) {
                    if (username.getText().toString().equals("") || pass.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "Login is null", Toast.LENGTH_LONG).show();
                        System.out.println("LOGIN NULL");
                    } else {
                        attemptLogin();
                        return true;
                    }
                }
                return false;
            }
        });

        TextView link = (TextView) findViewById(R.id.textLink);

        sign_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                intent.putExtra("latlon", latlon);
                startActivity(intent);
            }
        });


        //link web
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://178.33.138.17/passflix/";
                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                } catch (ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                }
            }
        });


        if (latlon.isEmpty()) {
            buildGoogleApiClient();
        }
    }

    public void statusCheck() {
        locationManager = (LocationManager) getSystemService(getApplicationContext().LOCATION_SERVICE);

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled. Do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }


    private void attemptLogin() {
        String usu = username.getText().toString();
        String password = pass.getText().toString();
        UserLoginManager.getInstance().performLogin(usu, password, LoginActivity.this);

        ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Login, please wait...");
        progressDialog.show();
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (permissionIsGranted){
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (permissionIsGranted) {
            if (mGoogleApiClient.isConnected()) {
                mGoogleApiClient.disconnect();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (permissionIsGranted) {
            if (mGoogleApiClient.isConnected()) {
                mGoogleApiClient.disconnect();
            }
        }
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            latlon = String.valueOf(mLastLocation.getLatitude() + "," + mLastLocation.getLongitude());
            //Toast.makeText(this, latlon, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Last connection not finded.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.i("LoginActivity", "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }


    @Override
    public void onConnectionSuspended(int cause) {
        Log.i("LoginActivity", "Connection suspended");
        if (permissionIsGranted) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onSuccess(UserToken userToken) {
        Intent intent = new Intent(LoginActivity.this, RequestActivity.class);
        intent.putExtra("latlon", latlon);
        startActivity(intent);
        finish();
    }


    @Override
    public void onFailure(Throwable t) {
        Log.e("LoginActivity->", "performLogin->onFailure ERROR " + t.getMessage());
        Toast.makeText(this, "Incorrect User", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    permissionIsGranted = true;
                }else {
                    permissionIsGranted = false;
                    Toast.makeText(getApplicationContext(), "This app requires location permission to be granted", Toast.LENGTH_SHORT).show();
                }
                break;
            case MY_PERMISSIONS_REQUEST_COARSE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    permissionIsGranted = true;
                }else {
                    permissionIsGranted = false;
                    Toast.makeText(getApplicationContext(), "This app requires location permission to be granted", Toast.LENGTH_SHORT).show();
                }
                break;
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    permissionIsGranted = true;
                break;
        }
    }
}
