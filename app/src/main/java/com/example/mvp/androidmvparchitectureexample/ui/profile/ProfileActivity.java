package com.example.mvp.androidmvparchitectureexample.ui.profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.annotation.Nullable;

import com.example.mvp.androidmvparchitectureexample.GaideioApp;
import com.example.mvp.androidmvparchitectureexample.R;
import com.example.mvp.androidmvparchitectureexample.ui.base.BaseActivity;
import com.example.mvp.androidmvparchitectureexample.ui.login.LoginActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ALL RIGHTS RESERVED - ALEXANDROS KOURTIS
 */

public class ProfileActivity extends BaseActivity implements ContractProfile.ContractView {

    private static final String TAG = ProfileActivity.class.getSimpleName();

    @Inject
    ProfilePresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);
        GaideioApp.getProfileComponent().inject(this);
        mPresenter.attachView(this);

        setUp();
    }

    @Override
    protected void setUp() {
    }

    @OnClick(R.id.logout)
    public void onViewClicked() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().putBoolean("loggedin", false).apply();
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void onProfileReady() {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
