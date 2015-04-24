package com.boolong.hangrywaits;

/**
 * Class represents each business with its business information.
 */
public class Business {
    private String id;
    private String googlePlaceId;
    private String restaurantName;
    private int waitTime;
    private boolean isFavorite;
    private String phone;
    private String address;

    public Business(String id, String googlePlaceId, String restaurantName, int waitTime, boolean isFavorite, String phone,
                    String address) {
        this.id = id;
        this.googlePlaceId = googlePlaceId;
        this.restaurantName = restaurantName;
        this.waitTime = waitTime;
        this.isFavorite = isFavorite;
        this.phone = phone;
        this.address = address;
    }

    public Business( String restaurantName, int waitTime, boolean isFavorite, String phone,
                    String address) {
        this.restaurantName = restaurantName;
        this.waitTime = waitTime;
        this.isFavorite = isFavorite;
        this.phone = phone;
        this.address = address;
    }

    /**
     * Get the ID of the business.
     *
     * @return ID
     */
    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    /**
     * Get the ID of the result return from Google Place API.
     * @return Google Place ID
     */
    public String getGooglePlaceId(){
        return googlePlaceId;
    }

    public void setGooglePlaceId(String googlePlaceId){
        this.googlePlaceId = googlePlaceId;
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