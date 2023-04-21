/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.getroute;

import java.util.ArrayList;

public class Root {
    public ArrayList<Route> Route;
    public String Error;

    public Root() {
    }

    public Root(ArrayList<com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.getroute.Route> route, String error) {
        Route = route;
        Error = error;
    }

    public ArrayList<com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.getroute.Route> getRoute() {
        return Route;
    }

    public void setRoute(ArrayList<com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.getroute.Route> route) {
        Route = route;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }
}
