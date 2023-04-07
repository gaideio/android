/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.di.module;

import com.example.mvp.androidmvparchitectureexample.data.local.LocalDataSource;
import com.example.mvp.androidmvparchitectureexample.data.remote.RemoteDataSource;
import com.example.mvp.androidmvparchitectureexample.di.scope.MainScope;
import com.example.mvp.androidmvparchitectureexample.ui.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    @Provides
    @MainScope
    public static MainPresenter providesMainPresenter(LocalDataSource localDataSource, RemoteDataSource remoteDataSource) {
        return new MainPresenter(localDataSource, remoteDataSource);
    }
}
