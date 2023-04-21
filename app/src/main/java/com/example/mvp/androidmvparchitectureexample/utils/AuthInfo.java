/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.mvp.androidmvparchitectureexample.GaideioApp;

public class AuthInfo {
    private static AuthInfo single_instance = null;
    private final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(GaideioApp.getAppContext());
    private String imgURI;
    private String name;
    private String email;
    private String jwttoken;
    private boolean loggedin;
    private boolean darkmode;
    private String pref1;
    private String pref2;

    private AuthInfo() {
        imgURI = getValueFromSharedPrefsByKey("imgURI");
        name = getValueFromSharedPrefsByKey("name");
        email = getValueFromSharedPrefsByKey("email");
        jwttoken = getJWTTokenFromSharedPreferences();
        loggedin = getBooleanValueFromSharedPrefsByKey("loggedin");
        darkmode = getBooleanValueFromSharedPrefsByKey("darkmode");
        pref1 = pref1;
        pref2 = pref2;
    }

    public static AuthInfo getSingle_instance() {
        return single_instance;
    }

    public static void setSingle_instance(AuthInfo single_instance) {
        AuthInfo.single_instance = single_instance;
    }

    public static synchronized AuthInfo getInstance() {
        if (single_instance == null) {
            single_instance = new AuthInfo();
        }

        return single_instance;
    }

    public static boolean isLoggedin() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(GaideioApp.getAppContext());
        sharedPreferences.edit().putBoolean("loggedin", true).apply();
        return sharedPreferences.getBoolean("loggedin", true);
    }

    public void setLoggedin(boolean loggedin) {
        setValueFromSharedPrefsByKey("loggedin", String.valueOf(loggedin));
        this.loggedin = loggedin;
    }

    private String getJWTTokenFromSharedPreferences() {
        return "Bearer " + sharedPreferences.getString("jwttoken", null);
    }

    private boolean getBooleanValueFromSharedPrefsByKey(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    private String getValueFromSharedPrefsByKey(String key) {
        return sharedPreferences.getString(key, "");
    }

    private void setValueFromSharedPrefsByKey(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public void clear() {
        sharedPreferences.edit().clear().apply();
        imgURI = "";
        name = "";
        email = "";
        jwttoken = "";
        loggedin = false;
        darkmode = false;
        pref1 = "";
        pref2 = "";
    }

    public String getImgURI() {
        return imgURI;
    }

    public void setImgURI(String imgURI) {
        setValueFromSharedPrefsByKey("imgURI", imgURI);
        this.imgURI = imgURI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        setValueFromSharedPrefsByKey("name", name);
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        setValueFromSharedPrefsByKey("email", email);
        this.email = email;
    }

    public String getJwttoken() {
        return jwttoken;
    }

    public void setJwttoken(String jwttoken) {
        setValueFromSharedPrefsByKey("jwttoken", jwttoken);
        this.jwttoken = jwttoken;
    }

    public boolean isDarkmode() {
        return darkmode;
    }

    public void setDarkmode(boolean darkmode) {
        setValueFromSharedPrefsByKey("darkmode", String.valueOf(darkmode));
        this.darkmode = darkmode;
    }

    public String getPref1() {
        return pref1;
    }

    public void setPref1(String pref1) {
        this.pref1 = pref1;
    }

    public String getPref2() {
        return pref2;
    }

    public void setPref2(String pref2) {
        this.pref2 = pref2;
    }

    @Override
    public String toString() {
        return "AuthInfo{" +
                "imgURI='" + imgURI + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", jwttoken='" + jwttoken + '\'' +
                ", loggedin=" + loggedin +
                ", darkmode=" + darkmode +
                ", pref1='" + pref1 + '\'' +
                ", pref2='" + pref2 + '\'' +
                '}';
    }
}
