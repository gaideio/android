/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.profile;

import java.util.Objects;

public class Profile {
    private Boolean isnew;
    private String fullname;
    private String imgUri;
    private String access_token;

    public Profile(String fullname, String imgUri, String access_token, Boolean isnew) {
        this.fullname = fullname;
        this.imgUri = imgUri;
        this.access_token = access_token;
        this.isnew = isnew;
    }

    public Profile() {
    }

    public Boolean getIsnew() {
        return isnew;
    }

    public void setIsnew(Boolean isnew) {
        this.isnew = isnew;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "fullname='" + fullname + '\'' +
                ", imgUri='" + imgUri + '\'' +
                ", access_token='" + access_token + '\'' +
                ", isnew='" + isnew + '\'' +
                '}';
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
        return Objects.equals(fullname, profile.fullname) && Objects.equals(imgUri, profile.imgUri) && Objects.equals(access_token, profile.access_token) && Objects.equals(isnew, profile.isnew);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullname, imgUri, access_token, isnew);
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }
}
