/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.login;

public class ModelLoginRequest {
    private String googletoken;

    public ModelLoginRequest(String googletoken) {
        this.googletoken = googletoken;
    }

    public String getGoogletoken() {
        return googletoken;
    }

    public void setGoogletoken(String googletoken) {
        this.googletoken = googletoken;
    }
}