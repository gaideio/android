/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.profile;

import java.util.Objects;

public class UpdateProfile {
    private String fullname;

    public UpdateProfile(String fullname) {
        this.fullname = fullname;
    }

    public UpdateProfile() {
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UpdateProfile that = (UpdateProfile) o;
        return Objects.equals(fullname, that.fullname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullname);
    }

    @Override
    public String toString() {
        return "UpdateProfile{" +
                "fullname='" + fullname + '\'' +
                '}';
    }
}
