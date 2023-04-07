/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.getroute;

import java.util.ArrayList;

public class Root {
    public ArrayList<Route> route;
    public String error;

    public Root() {
    }

    public Root(ArrayList<Route> route, String error) {
        this.route = route;
        this.error = error;
    }

    public ArrayList<Route> getRoute() {
        return route;
    }

    public void setRoute(ArrayList<Route> route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return "Root{" +
                "route=" + route +
                ", error='" + error + '\'' +
                '}';
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
