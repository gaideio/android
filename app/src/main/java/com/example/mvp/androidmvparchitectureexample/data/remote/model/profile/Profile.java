/*
 * ALL RIGHTS RESERVED - ALEXANDROS KOURTIS
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.profile;

import java.util.Objects;

public class Profile {
    private String fullName;
    private String imgUri;

    public Profile() {
    }

    public Profile(String fullName, String imgUri) {
        this.fullName = fullName;
        this.imgUri = imgUri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Profile profile = (Profile) o;
        return Objects.equals(fullName, profile.fullName) && Objects.equals(imgUri, profile.imgUri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, imgUri);
    }

    @Override
    public String toString() {
        return "Profile{" +
                "fullName='" + fullName + '\'' +
                ", imgUri='" + imgUri + '\'' +
                '}';
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }
}
