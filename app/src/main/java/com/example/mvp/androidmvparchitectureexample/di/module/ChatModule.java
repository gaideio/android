package com.example.mvp.androidmvparchitectureexample.di.module;

import com.example.mvp.androidmvparchitectureexample.data.local.LocalDataSource;
import com.example.mvp.androidmvparchitectureexample.data.remote.RemoteDataSource;
import com.example.mvp.androidmvparchitectureexample.di.scope.ChatScope;
import com.example.mvp.androidmvparchitectureexample.ui.chat.ChatPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * ALL RIGHTS RESERVED - ALEXANDROS KOURTIS
 */

@Module
public class ChatModule {
    @Provides
    @ChatScope
    public static ChatPresenter providesProfilePresenter(LocalDataSource localDataSource, RemoteDataSource remoteDataSource) {
        return new ChatPresenter(localDataSource, remoteDataSource);
    }
}
