/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.example.mvp.androidmvparchitectureexample.di.component.ChatComponent;
import com.example.mvp.androidmvparchitectureexample.di.component.DaggerAppComponent;
import com.example.mvp.androidmvparchitectureexample.di.component.DaggerChatComponent;
import com.example.mvp.androidmvparchitectureexample.di.component.DaggerFeedbackComponent;
import com.example.mvp.androidmvparchitectureexample.di.component.DaggerLoginComponent;
import com.example.mvp.androidmvparchitectureexample.di.component.DaggerMainComponent;
import com.example.mvp.androidmvparchitectureexample.di.component.DaggerNewsComponent;
import com.example.mvp.androidmvparchitectureexample.di.component.DaggerProfileComponent;
import com.example.mvp.androidmvparchitectureexample.di.component.FeedbackComponent;
import com.example.mvp.androidmvparchitectureexample.di.component.LoginComponent;
import com.example.mvp.androidmvparchitectureexample.di.component.MainComponent;
import com.example.mvp.androidmvparchitectureexample.di.component.NewsComponent;
import com.example.mvp.androidmvparchitectureexample.di.component.ProfileComponent;
import com.example.mvp.androidmvparchitectureexample.di.module.AppModule;
import com.example.mvp.androidmvparchitectureexample.di.module.ChatModule;
import com.example.mvp.androidmvparchitectureexample.di.module.FeedbackModule;
import com.example.mvp.androidmvparchitectureexample.di.module.LoginModule;
import com.example.mvp.androidmvparchitectureexample.di.module.MainModule;
import com.example.mvp.androidmvparchitectureexample.di.module.NewsModule;
import com.example.mvp.androidmvparchitectureexample.di.module.ProfileModule;

public class GaideioApp extends Application {

    private static NewsComponent newsComponent;
    private static ProfileComponent profileComponent;
    private static LoginComponent loginComponent;
    private static ChatComponent chatComponent;
    private static MainComponent mainComponent;
    private static FeedbackComponent feedbackComponent;
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    public static MainComponent getMainComponent() {
        return mainComponent;
    }

    public static ChatComponent getChatComponent() {
        return chatComponent;
    }

    public static ProfileComponent getProfileComponent() {
        return profileComponent;
    }

    public static LoginComponent getLoginComponent() {
        return loginComponent;
    }

    public static NewsComponent getNewsComponent() {
        return newsComponent;
    }

    public static FeedbackComponent getFeedbackComponent() {
        return feedbackComponent;
    }

    public static Context getAppContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        initializeDagger();
    }

    private void initializeDagger() {
        newsComponent = DaggerNewsComponent.builder().appComponent(DaggerAppComponent.builder()
                        .appModule(new AppModule(this))
                        .build()).newsModule(new NewsModule()).
                build();

        loginComponent = DaggerLoginComponent.builder().appComponent(DaggerAppComponent.builder()
                        .appModule(new AppModule(this))
                        .build()).loginModule(new LoginModule()).
                build();

        profileComponent = DaggerProfileComponent.builder().appComponent(DaggerAppComponent.builder()
                        .appModule(new AppModule(this))
                        .build()).profileModule(new ProfileModule()).
                build();

        chatComponent = DaggerChatComponent.builder().appComponent(DaggerAppComponent.builder()
                        .appModule(new AppModule(this))
                        .build()).chatModule(new ChatModule()).
                build();

        mainComponent = DaggerMainComponent.builder().appComponent(DaggerAppComponent.builder()
                        .appModule(new AppModule(this))
                        .build()).mainModule(new MainModule()).
                build();

        feedbackComponent = DaggerFeedbackComponent.builder().appComponent(DaggerAppComponent.builder()
                        .appModule(new AppModule(this))
                        .build()).feedbackModule(new FeedbackModule()).
                build();
    }
}
