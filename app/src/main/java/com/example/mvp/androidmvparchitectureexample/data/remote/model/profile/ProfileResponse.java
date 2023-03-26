/*
 * ALL RIGHTS RESERVED - ALEXANDROS KOURTIS
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.profile;

public class ProfileResponse {
    private String name;
    private String email;
    private String error;

    public ProfileResponse(String name, String email, String error) {
        this.name = name;
        this.email = email;
        this.error = error;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}