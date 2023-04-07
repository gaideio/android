/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.getroute;

import java.util.ArrayList;

public class Coordinate {
    public ArrayList<Location> location;

    public Coordinate(ArrayList<Location> location) {
        this.location = location;
    }

    public Coordinate() {
    }

    public ArrayList<Location> getLocation() {
        return location;
    }

    public void setLocation(ArrayList<Location> location) {
        this.location = location;
    }
}
