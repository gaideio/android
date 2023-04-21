/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.chat;

public class HarisApiWriteToChat {
    private String Response;
    private String Error;

    public HarisApiWriteToChat(String response, String error) {
        Response = response;
        Error = error;
    }

    public HarisApiWriteToChat() {
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }
}
