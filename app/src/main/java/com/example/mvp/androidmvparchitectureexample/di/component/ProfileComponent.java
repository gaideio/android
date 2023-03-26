package com.example.mvp.androidmvparchitectureexample.di.component;

import com.example.mvp.androidmvparchitectureexample.di.module.ProfileModule;
import com.example.mvp.androidmvparchitectureexample.di.scope.ProfileScope;
import com.example.mvp.androidmvparchitectureexample.ui.profile.ProfileActivity;

import dagger.Component;

/**
 * ALL RIGHTS RESERVED - ALEXANDROS KOURTIS
 */

@ProfileScope
@Component(modules = {ProfileModule.class}, dependencies = {AppComponent.class})
public interface ProfileComponent {
    void inject(ProfileActivity view);
}
