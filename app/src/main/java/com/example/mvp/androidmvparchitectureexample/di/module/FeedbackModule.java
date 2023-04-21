/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.di.module;

import com.example.mvp.androidmvparchitectureexample.data.remote.RemoteDataSource;
import com.example.mvp.androidmvparchitectureexample.di.scope.FeedbackScope;
import com.example.mvp.androidmvparchitectureexample.ui.feedback.FeedbackPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class FeedbackModule {
    @Provides
    @FeedbackScope
    public static FeedbackPresenter providesFeedbackPresenter(RemoteDataSource remoteDataSource) {
        return new FeedbackPresenter(remoteDataSource);
    }
}
