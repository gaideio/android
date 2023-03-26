package com.example.mvp.androidmvparchitectureexample.data.remote;

import com.example.mvp.androidmvparchitectureexample.data.remote.model.News;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.ChatResponse;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.NewChatResponse;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.SendToChatResponse;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.profile.Profile;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.profile.ProfileResponse;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.signup.SignupResponse;

import io.reactivex.Flowable;
import retrofit2.Response;

/**
 * ALL RIGHTS RESERVED - ALEXANDROS KOURTIS
 */

public class RemoteDataSource {
    private final RemoteService mRemoteService;

    public RemoteDataSource(RemoteService remoteService) {
        mRemoteService = remoteService;
    }

    public Flowable<Response<News>> getArticlesFromApi() {
        return mRemoteService.getArticleFroimApi();
    }

    public Flowable<Response<Profile>> login() {
        return mRemoteService.login();
    }

    public Flowable<Response<SignupResponse>> signUp() {
        return mRemoteService.signUp();
    }

    public Flowable<Response<ProfileResponse>> getProfile() {
        return mRemoteService.getProfile();
    }

    public Flowable<Response<ChatResponse>> getChat() {
        return mRemoteService.getChat();
    }

    public Flowable<Response<NewChatResponse>> newChat() {
        return mRemoteService.newChat();
    }

    public Flowable<Response<SendToChatResponse>> sendToChat() {
        return mRemoteService.sendToChat();
    }
}
