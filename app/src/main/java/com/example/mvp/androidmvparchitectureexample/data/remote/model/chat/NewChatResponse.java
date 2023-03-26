/*
 * ALL RIGHTS RESERVED - ALEXANDROS KOURTIS
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.chat;

public class NewChatResponse {

    private String confirmation;
    private String error;

    public NewChatResponse(String confirmation, String error) {
        this.confirmation = confirmation;
        this.error = error;
    }

    public NewChatResponse() {
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}