/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.getroute;

import java.util.ArrayList;

public class Route {
    //    res: list[dict[str, list] | dict[str, list]] = [{"itinerary": []}, {"coordinates": []}]
    public ArrayList<Itinerary> itinerary;
    public ArrayList<Coordinate> coordinates;

    public Route(ArrayList<Itinerary> itinerary, ArrayList<Coordinate> coordinates) {
        this.itinerary = itinerary;
        this.coordinates = coordinates;
    }

    public Route() {
    }

    public ArrayList<Itinerary> getItinerary() {
        return itinerary;
    }

    public void setItinerary(ArrayList<Itinerary> itinerary) {
        this.itinerary = itinerary;
    }

    public ArrayList<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }
}
