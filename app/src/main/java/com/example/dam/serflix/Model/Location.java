package com.example.dam.serflix.Model;

/**
 * Created by DAM on 23/2/17.
 */

public class Location {
    private double latitude;
    private double longituded;


    public Location(double latitude, double longituded) {
        this.latitude = latitude;
        this.longituded = longituded;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongituded() {
        return longituded;
    }

    public void setLongituded(double longituded) {
        this.longituded = longituded;
    }
}
