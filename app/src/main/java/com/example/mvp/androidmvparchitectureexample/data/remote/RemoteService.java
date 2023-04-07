/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.data.remote;

import com.example.mvp.androidmvparchitectureexample.data.remote.model.Messages;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.News;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.ChatResponse;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.Message;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.NewChatRequest;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.NewChatResponse;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.SendToChatResponse;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.getroute.Root;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.login.ModelLoginRequest;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.profile.Profile;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.profile.ProfileResponse;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.profile.UpdateProfile;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.signup.SignupResponse;

import io.reactivex.Flowable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RemoteService {

    @GET("/checkjwtforautologin")
    Flowable<Response<Object>> checkjwtforautologin(@Header("Authorization") String jwttoken);

    @GET(".")
    Flowable<Response<News>> getArticleFroimApi();

    @GET("/{email}/chat")
    Flowable<Response<Messages>> getMessagesFromApi(@Header("Authorization") String jwttoken, @Path("email") String email);

    @POST("/writetochat")
    @Headers("Content-Type: application/json")
    Flowable<Response<SendToChatResponse>> writeToChat(@Header("Authorization") String jwttoken, @Body Message message);

    @POST("/login")
    @Headers("Content-Type: application/json")
    Flowable<Response<Profile>> login(@Body ModelLoginRequest modelLoginRequest);

    @POST("/signup")
    Flowable<Response<SignupResponse>> signUp();

    @GET("/profile")
    @Headers("Content-Type: application/json")
    Flowable<Response<ProfileResponse>> getProfile(@Header("Authorization") String jwttoken);

    @POST("/updateprofile")
    @Headers("Content-Type: application/json")
    Flowable<Response<Object>> updateProfile(@Header("Authorization") String jwttoken, @Body UpdateProfile updateProfile);

    @DELETE("/deleteaccount")
    @Headers("Content-Type: application/json")
    Flowable<Response<Object>> deleteAccount(@Header("Authorization") String jwttoken);

    @DELETE("/deletechat")
    @Headers("Content-Type: application/json")
    Flowable<Response<Object>> deletechat(@Header("Authorization") String jwttoken);

    @GET("/route")
    @Headers("Content-Type: application/json")
    Flowable<Response<Root>> getRoute(@Header("Authorization") String jwttoken);

    @GET("/chat")
    Flowable<Response<ChatResponse>> getChat();

    @POST("/newchat")
    @Headers("Content-Type: application/json")
    Flowable<Response<NewChatResponse>> newChat(@Header("Authorization") String jwttoken, @Body NewChatRequest newChatRequest);
}
