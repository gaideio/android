/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.getroute;

public class Itinerary {
    public String location;

    public Itinerary(String location) {
        this.location = location;
    }

    public Itinerary() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
