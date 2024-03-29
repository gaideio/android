/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.route;

public class PostRouteResponse {

    private String route;
    private String error;

    public PostRouteResponse(String route, String error) {
        this.route = route;
        this.error = error;
    }

    public PostRouteResponse() {
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
