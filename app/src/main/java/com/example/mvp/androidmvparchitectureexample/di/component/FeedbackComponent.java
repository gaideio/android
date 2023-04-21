/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.di.component;

import com.example.mvp.androidmvparchitectureexample.di.module.FeedbackModule;
import com.example.mvp.androidmvparchitectureexample.di.scope.FeedbackScope;
import com.example.mvp.androidmvparchitectureexample.ui.feedback.FeedbackActivity;

import dagger.Component;

@FeedbackScope
@Component(modules = {FeedbackModule.class}, dependencies = {AppComponent.class})
public interface FeedbackComponent {
    void inject(FeedbackActivity feedbackActivity);
}
