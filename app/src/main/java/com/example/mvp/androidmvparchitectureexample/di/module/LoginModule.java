package com.example.mvp.androidmvparchitectureexample.di.module;

import com.example.mvp.androidmvparchitectureexample.data.remote.RemoteDataSource;
import com.example.mvp.androidmvparchitectureexample.di.scope.LoginScope;
import com.example.mvp.androidmvparchitectureexample.ui.login.LoginPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * ALL RIGHTS RESERVED - ALEXANDROS KOURTIS
 */

@Module
public class LoginModule {
    @Provides
    @LoginScope
    public static LoginPresenter providesProfilePresenter(RemoteDataSource remoteDataSource) {
        return new LoginPresenter(remoteDataSource);
    }
}
