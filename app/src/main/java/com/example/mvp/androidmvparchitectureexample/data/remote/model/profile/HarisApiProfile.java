/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.profile;

public class HarisApiProfile {
    private String email;
    private String name;

    public HarisApiProfile(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public HarisApiProfile() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
