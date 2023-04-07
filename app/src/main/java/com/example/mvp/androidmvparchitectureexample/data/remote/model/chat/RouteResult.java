/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.chat;

public class RouteResult {

    //    {
//        "route":[
//        {
//            "itinerary":[
//            {
//                "location":"route"
//            }
//         ]
//        },
//        {
//            "coordinates":[
//            {
//                "location":[
//                {
//                    "x":"37.96794182615158"
//                },
//                {
//                    "y":"23.746805339197554"
//                }
//               ]
//            }
//         ]
//        }
//   ],
//        "error":"None"
//    }
//
    private Route route;
    private String error;

    public RouteResult() {
    }

    public RouteResult(Route route, String error) {
        this.route = route;
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
