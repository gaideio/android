/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.di.component;

import com.example.mvp.androidmvparchitectureexample.di.module.MainModule;
import com.example.mvp.androidmvparchitectureexample.di.scope.MainScope;
import com.example.mvp.androidmvparchitectureexample.ui.main.MainActivity;

import dagger.Component;

@MainScope
@Component(modules = {MainModule.class}, dependencies = {AppComponent.class})
public interface MainComponent {
    void inject(MainActivity view);
}
