package com.example.mvp.androidmvparchitectureexample.di.component;

import com.example.mvp.androidmvparchitectureexample.di.module.NewsModule;
import com.example.mvp.androidmvparchitectureexample.di.scope.NewsScope;
import com.example.mvp.androidmvparchitectureexample.ui.news.NewsActivity;

import dagger.Component;

/**
 * ALL RIGHTS RESERVED
 */

@NewsScope
@Component(modules = {NewsModule.class}, dependencies = {AppComponent.class})
public interface NewsComponent {
    void inject(NewsActivity view);
}
