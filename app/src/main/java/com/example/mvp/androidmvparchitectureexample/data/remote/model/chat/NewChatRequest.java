/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.chat;

public class NewChatRequest {

    private String location;
    private String transport;
    private String time;

    public NewChatRequest() {
    }

    public NewChatRequest(String location, String transport, String time) {
        this.location = location;
        this.transport = transport;
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
