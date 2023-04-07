package com.example.mvp.androidmvparchitectureexample.di.component;

import com.example.mvp.androidmvparchitectureexample.data.local.LocalDataSource;
import com.example.mvp.androidmvparchitectureexample.data.remote.RemoteDataSource;
import com.example.mvp.androidmvparchitectureexample.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * ALL RIGHTS RESERVED
 */

@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {
    LocalDataSource roomDataSource();

    RemoteDataSource remoteDataSource();
}
