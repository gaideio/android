/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.getroute;

public class GetRouteResultPOJO {
    private Route route;
    private String error;

    public GetRouteResultPOJO() {
    }

    public GetRouteResultPOJO(Route route, String error) {
        this.route = route;
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "RouteResult{" +
                "route=" + route +
                ", error='" + error + '\'' +
                '}';
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
