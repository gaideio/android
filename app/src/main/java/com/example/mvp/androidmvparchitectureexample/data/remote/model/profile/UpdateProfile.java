/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.profile;

public class UpdateProfile {
    private String name;

    public UpdateProfile(String name) {
        this.name = name;
    }

    public UpdateProfile() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
