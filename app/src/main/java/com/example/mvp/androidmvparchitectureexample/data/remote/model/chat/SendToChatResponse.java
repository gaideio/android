/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.chat;

public class SendToChatResponse {
    private String response;
    private String error;

    public SendToChatResponse(String response, String error) {
        this.response = response;
        this.error = error;
    }

    public SendToChatResponse() {
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
