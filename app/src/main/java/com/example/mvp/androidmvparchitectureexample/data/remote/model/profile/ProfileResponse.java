/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.profile;

public class ProfileResponse {
    private HarisApiProfile Profile;
    private String Error;

    public ProfileResponse(HarisApiProfile profile, String error) {
        Profile = profile;
        Error = error;
    }

    public ProfileResponse() {
    }

    public HarisApiProfile getProfile() {
        return Profile;
    }

    public void setProfile(HarisApiProfile profile) {
        Profile = profile;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }
}
