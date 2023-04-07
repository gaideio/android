/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.chat;

import java.util.HashMap;
import java.util.List;

public class Route {
    private List<HashMap<String, String>> itinerary;
    private List<HashMap<HashMap<String, String>, HashMap<String, String>>> coordinates;

    public Route() {
    }

    public Route(List<HashMap<String, String>> itinerary, List<HashMap<HashMap<String, String>, HashMap<String, String>>> coordinates) {
        this.itinerary = itinerary;
        this.coordinates = coordinates;
    }

    public List<HashMap<String, String>> getItinerary() {
        return itinerary;
    }

    public void setItinerary(List<HashMap<String, String>> itinerary) {
        this.itinerary = itinerary;
    }

    public List<HashMap<HashMap<String, String>, HashMap<String, String>>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<HashMap<HashMap<String, String>, HashMap<String, String>>> coordinates) {
        this.coordinates = coordinates;
    }
}
