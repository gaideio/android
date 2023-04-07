package com.example.mvp.androidmvparchitectureexample.di.component;

import com.example.mvp.androidmvparchitectureexample.di.module.LoginModule;
import com.example.mvp.androidmvparchitectureexample.di.scope.LoginScope;
import com.example.mvp.androidmvparchitectureexample.ui.login.LoginActivity;

import dagger.Component;

/**
 * ALL RIGHTS RESERVED
 */

@LoginScope
@Component(modules = {LoginModule.class}, dependencies = {AppComponent.class})
public interface LoginComponent {
    void inject(LoginActivity view);
}
