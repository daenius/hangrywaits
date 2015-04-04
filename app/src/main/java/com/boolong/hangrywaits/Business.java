package com.boolong.hangrywaits;

/**
 * Created by dennizhu on 3/30/15.
 */
public class Business {
    private String restaurantName;
    private int waitTime;
    private boolean isFavorite;
    private String phone;
    private String address;

    public Business(String restaurantName, int waitTime, boolean isFavorite, String phone,
                    String address) {
        this.restaurantName = restaurantName;
        this.waitTime = waitTime;
        this.isFavorite = isFavorite;
        this.phone = phone;
        this.address = address;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    @Override
    public String toString() {
        return this.restaurantName + " " + this.waitTime + " " + this.phone + " " + this.address;
    }
}