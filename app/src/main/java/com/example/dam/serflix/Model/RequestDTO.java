package com.example.dam.serflix.Model;

/**
 * Created by DAM on 26/4/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestDTO {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("viewDate")
    @Expose
    private String viewDate;
    @SerializedName("creationDate")
    @Expose
    private String creationDate;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("userRequester")
    @Expose
    private UserRequester userRequester;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("userGuests")
    @Expose
    private List<Object> userGuests = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public UserRequester getUserRequester() {
        return userRequester;
    }

    public void setUserRequester(UserRequester userRequester) {
        this.userRequester = userRequester;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Object> getUserGuests() {
        return userGuests;
    }

    public void setUserGuests(List<Object> userGuests) {
        this.userGuests = userGuests;
    }

}