/*
 * ALL RIGHTS RESERVED - ALEXANDROS KOURTIS
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.route;

public class GetRouteResponse {

    private String route;
    private String error;

    public GetRouteResponse(String route, String error) {
        this.route = route;
        this.error = error;
    }

    public GetRouteResponse() {
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
