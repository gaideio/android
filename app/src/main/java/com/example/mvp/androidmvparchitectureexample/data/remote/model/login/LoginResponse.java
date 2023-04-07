/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.login;

public class LoginResponse {
    private String confirmation;
    private String error;

    public LoginResponse(String confirmation, String error) {
        this.confirmation = confirmation;
        this.error = error;
    }

    public LoginResponse() {
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
