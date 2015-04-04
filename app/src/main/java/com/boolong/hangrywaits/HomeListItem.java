package com.boolong.hangrywaits;

/**
 * Created by dennizhu on 3/30/15.
 */
public class HomeListItem {
    private String restaurantName;
    private String phoneNumber;
    private int waitTime;
    private String address;
    private boolean isFavorite;

    public HomeListItem(String restaurantName, String phoneNumber, String address, int waitTime, boolean isFavorite) {
        this.restaurantName = restaurantName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.waitTime = waitTime;
        this.isFavorite = isFavorite;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }
}