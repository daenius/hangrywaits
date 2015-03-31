package com.boolong.hangrywaits;

/**
 * Created by dennizhu on 3/30/15.
 */
public class HomeListItem {
    private String restaurantName;
    private int waitTime;
    private boolean isFavorite;

    public HomeListItem(String restaurantName, int waitTime, boolean isFavorite) {
        this.restaurantName = restaurantName;
        this.waitTime = waitTime;
        this.isFavorite = isFavorite;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
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