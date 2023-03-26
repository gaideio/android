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
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * ALL RIGHTS RESERVED - ALEXANDROS KOURTIS
 */

public interface RemoteService {
    @GET(".")
    Flowable<Response<News>> getArticleFroimApi();

    @POST("/login")
    Flowable<Response<Profile>> login();

    @POST("/signup")
    Flowable<Response<SignupResponse>> signUp();

    @GET("/profile")
    Flowable<Response<ProfileResponse>> getProfile();

    @GET("/chat")
    Flowable<Response<ChatResponse>> getChat();

    @POST("/newchat")
    Flowable<Response<NewChatResponse>> newChat();

    @POST("/chat")
    Flowable<Response<SendToChatResponse>> sendToChat();

    //    @POST("/router")
    //    Flowable<Response<PostRouteResponse>> setRoute();

    //    @GET("/route")
    //    Flowable<Response<GetRouteResponse>> getRoute();
}
