package com.example.mvp.androidmvparchitectureexample.di.module;

import com.example.mvp.androidmvparchitectureexample.data.local.LocalDataSource;
import com.example.mvp.androidmvparchitectureexample.data.remote.RemoteDataSource;
import com.example.mvp.androidmvparchitectureexample.di.scope.NewsScope;
import com.example.mvp.androidmvparchitectureexample.ui.news.NewsPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * ALL RIGHTS RESERVED - ALEXANDROS KOURTIS
 */

@Module
public class NewsModule {
    @Provides
    @NewsScope
    public static NewsPresenter providesNewsPresenter(LocalDataSource localDataSource, RemoteDataSource remoteDataSource) {
        return new NewsPresenter(localDataSource, remoteDataSource);
    }
}
