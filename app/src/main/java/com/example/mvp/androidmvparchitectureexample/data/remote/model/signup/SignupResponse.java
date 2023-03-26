/*
 * ALL RIGHTS RESERVED - ALEXANDROS KOURTIS
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.signup;

public class SignupResponse {
    private String confirmation;
    private String error;

    public SignupResponse(String confirmation, String error) {
        this.confirmation = confirmation;
        this.error = error;
    }

    public SignupResponse() {
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
