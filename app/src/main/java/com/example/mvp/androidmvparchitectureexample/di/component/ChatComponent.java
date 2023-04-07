package com.example.mvp.androidmvparchitectureexample.di.component;

import com.example.mvp.androidmvparchitectureexample.di.module.ChatModule;
import com.example.mvp.androidmvparchitectureexample.di.scope.ChatScope;
import com.example.mvp.androidmvparchitectureexample.ui.chat.ChatActivity;

import dagger.Component;

/**
 * ALL RIGHTS RESERVED
 */

@ChatScope
@Component(modules = {ChatModule.class}, dependencies = {AppComponent.class})
public interface ChatComponent {
    // TODO
    void inject(ChatActivity view);
}
