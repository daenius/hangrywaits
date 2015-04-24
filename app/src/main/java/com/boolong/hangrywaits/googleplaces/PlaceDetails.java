package com.boolong.hangrywaits.googleplaces;

import com.google.api.client.util.Key;

import java.io.Serializable;

/**
 * Created by jpowang on 4/11/15.
 *
 *  Implement this class from "Serializable"
 * So that you can pass this class Object to another using Intents
 * Otherwise you can't pass to another actitivy
 * */
public class PlaceDetails implements Serializable {

    @Key
    public String status;

    @Key
    public Place result;

    @Override
    public String toString() {
        if (result!=null) {
            return result.toString();
        }
        return super.toString();
    }
}