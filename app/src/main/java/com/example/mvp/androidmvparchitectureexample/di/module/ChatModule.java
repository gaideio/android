/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.di.module;

import com.example.mvp.androidmvparchitectureexample.data.local.LocalDataSource;
import com.example.mvp.androidmvparchitectureexample.data.remote.RemoteDataSource;
import com.example.mvp.androidmvparchitectureexample.di.scope.ChatScope;
import com.example.mvp.androidmvparchitectureexample.ui.chat.ChatPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ChatModule {
    @Provides
    @ChatScope
    public static ChatPresenter providesChatPresenter(LocalDataSource localDataSource, RemoteDataSource remoteDataSource) {
        return new ChatPresenter(localDataSource, remoteDataSource);
    }
}
