/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.profile;

public class Profile {
    private Boolean isnew;
    private String name;
    private String imgUri;
    private String access_token;

    public Profile() {
    }

    public Profile(Boolean isnew, String name, String imgUri, String access_token) {
        this.isnew = isnew;
        this.name = name;
        this.imgUri = imgUri;
        this.access_token = access_token;
    }

    public Boolean getIsnew() {
        return isnew;
    }

    public void setIsnew(Boolean isnew) {
        this.isnew = isnew;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
