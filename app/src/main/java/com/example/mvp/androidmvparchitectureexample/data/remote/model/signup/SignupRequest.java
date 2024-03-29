/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.signup;

public class SignupRequest {
    private String name;
    private String email;

    public SignupRequest(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public SignupRequest() {
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
}
