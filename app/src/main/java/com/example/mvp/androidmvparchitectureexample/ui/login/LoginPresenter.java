package com.example.mvp.androidmvparchitectureexample.ui.login;

import com.example.mvp.androidmvparchitectureexample.data.remote.RemoteDataSource;
import com.example.mvp.androidmvparchitectureexample.ui.base.BasePresenter;

/**
 * ALL RIGHTS RESERVED - ALEXANDROS KOURTIS
 */

public class LoginPresenter extends BasePresenter<ContractLogin.ContractView> implements ContractLogin.ContractPresenter {

    private static final String TAG = LoginPresenter.class.getSimpleName();

    RemoteDataSource mRemoteDataSource;

    public LoginPresenter(RemoteDataSource mRemoteDataSource) {
        this.mRemoteDataSource = mRemoteDataSource;
    }

    @Override
    public void login() {
        
    }
}
