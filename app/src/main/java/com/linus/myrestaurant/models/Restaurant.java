package com.linus.myrestaurant.models;

import org.parceler.Parcel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Parcel
public class Restaurant implements Serializable {
    public String name;
    public String phone;
    public String website;
    public double rating;
    public String imageUrl;
    public List<String> address = new ArrayList<>();
    public double latitude;
    public double longitude;
    public List<String> categories = new ArrayList<>();
    private String pushId;

    public Restaurant(){}

    public Restaurant(String name, String phone, String website, double rating, String imageUrl, ArrayList<String> address, double latitude, double longitude, ArrayList<String> categories) {
        this.name = name;
        this.phone = phone;
        this.website = website;
        this.rating =rating;
        this.imageUrl = imageUrl;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
