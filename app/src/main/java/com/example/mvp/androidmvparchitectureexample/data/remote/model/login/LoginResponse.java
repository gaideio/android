/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.login;

public class LoginResponse {
    private String Confirmation;
    private String Error;

    public LoginResponse() {
    }

    public LoginResponse(String confirmation, String error) {
        Confirmation = confirmation;
        Error = error;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }

    public String getConfirmation() {
        return Confirmation;
    }

    public void setConfirmation(String confirmation) {
        Confirmation = confirmation;
    }
}
