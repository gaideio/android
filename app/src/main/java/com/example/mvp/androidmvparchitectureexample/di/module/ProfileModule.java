package com.example.mvp.androidmvparchitectureexample.di.module;

import com.example.mvp.androidmvparchitectureexample.data.local.LocalDataSource;
import com.example.mvp.androidmvparchitectureexample.data.remote.RemoteDataSource;
import com.example.mvp.androidmvparchitectureexample.di.scope.ProfileScope;
import com.example.mvp.androidmvparchitectureexample.ui.profile.ProfilePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * ALL RIGHTS RESERVED - ALEXANDROS KOURTIS
 */

@Module
public class ProfileModule {
    @Provides
    @ProfileScope
    public static ProfilePresenter providesProfilePresenter(LocalDataSource localDataSource, RemoteDataSource remoteDataSource) {
        return new ProfilePresenter(localDataSource, remoteDataSource);
    }
}
