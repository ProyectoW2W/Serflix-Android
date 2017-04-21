package com.example.dam.serflix.Model;

import com.example.dam.serflix.Model.enumeration.Company;
import com.example.dam.serflix.Model.enumeration.Type;

/**
 * Created by DAM on 23/2/17.
 */

public class Request {

    private Long id;
    private Type type;
    private String viewDate;
    private String creationDate;
    private Company company;

    private String location;
    //añadir  userGuest


    public Request(Type type, String viewDate, String creationDate, Company company, String location) {
        this.type = type;
        this.viewDate = viewDate;
        this.creationDate = creationDate;
        this.company = company;
        this.location = location;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getViewDate() {
        return viewDate;
    }

    public void setViewDate(String viewDate) {
        this.viewDate = viewDate;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
