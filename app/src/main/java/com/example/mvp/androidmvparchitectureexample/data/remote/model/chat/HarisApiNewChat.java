/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.chat;

public class HarisApiNewChat {

    private String Confirmation;
    private String Error;

    public HarisApiNewChat() {
    }

    public HarisApiNewChat(String confirmation, String error) {
        Confirmation = confirmation;
        Error = error;
    }

    public String getConfirmation() {
        return Confirmation;
    }

    public void setConfirmation(String confirmation) {
        Confirmation = confirmation;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }
}
