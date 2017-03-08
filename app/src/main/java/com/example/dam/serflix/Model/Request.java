package com.example.dam.serflix.Model;

import com.example.dam.serflix.Model.enumeration.Company;
import com.example.dam.serflix.Model.enumeration.Type;

import java.util.Date;

/**
 * Created by DAM on 23/2/17.
 */

public class Request {

    private Type type;
    private Date viewDate;
    private Date creationDate;
    private Company company;

    private UserToken userRequester;

    private Location location;
    //añadir  userGuest


    public Request(Type type, Date viewDate, Date creationDate, Company company, UserToken userRequester, Location location) {
        this.type = type;
        this.viewDate = viewDate;
        this.creationDate = creationDate;
        this.company = company;
        this.userRequester = userRequester;
        this.location = location;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Date getViewDate() {
        return viewDate;
    }

    public void setViewDate(Date viewDate) {
        this.viewDate = viewDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public UserToken getUserRequester() {
        return userRequester;
    }

    public void setUserRequester(UserToken userRequester) {
        this.userRequester = userRequester;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
