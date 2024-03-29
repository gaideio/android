/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.data.remote;

import com.example.mvp.androidmvparchitectureexample.data.remote.model.Feedback;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.Messages;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.News;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.HarisApiChat;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.HarisApiNewChat;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.HarisApiWriteToChat;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.Message;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.NewChatRequest;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.getroute.Root;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.login.ModelLoginRequest;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.profile.HarisApiUpdateProfile;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.profile.Profile;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.profile.ProfileResponse;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.profile.UpdateProfile;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.signup.SignupResponse;

import io.reactivex.Flowable;
import retrofit2.Response;

public class RemoteDataSource {
    private final RemoteService mRemoteService;

    public RemoteDataSource(RemoteService remoteService) {
        mRemoteService = remoteService;
    }

    public Flowable<Response<News>> getArticlesFromApi() {
        return mRemoteService.getArticleFroimApi();
    }

    public Flowable<Response<Messages>> getMessagesFromApi(String jwttoken, String email) {
        return mRemoteService.getMessagesFromApi(jwttoken, email);
    }

    public Flowable<Response<Object>> checkjwtforautologin(String jwttoken) {
        return mRemoteService.checkjwtforautologin(jwttoken);
    }

    public Flowable<Response<Profile>> login(ModelLoginRequest modelLoginRequest) {
        return mRemoteService.login(modelLoginRequest);
    }

    public Flowable<Response<SignupResponse>> signUp() {
        return mRemoteService.signUp();
    }

    public Flowable<Response<ProfileResponse>> getProfile(String jwttoken) {
        return mRemoteService.getProfile(jwttoken);
    }

    public Flowable<Response<Object>> deleteAccount(String jwttoken) {
        return mRemoteService.deleteAccount(jwttoken);
    }

    public Flowable<Response<HarisApiUpdateProfile>> updateProfile(String jwttoken, UpdateProfile updateProfile) {
        return mRemoteService.updateProfile(jwttoken, updateProfile);
    }

    public Flowable<Response<HarisApiChat>> getChat(String jwttoken) {
        return mRemoteService.getChat(jwttoken);
    }

    public Flowable<Response<HarisApiNewChat>> newChat(String jwttoken, NewChatRequest newChatRequest) {
        return mRemoteService.newChat(jwttoken, newChatRequest);
    }

    public Flowable<Response<Object>> sendFeedback(Feedback feedback, String jwttoken) {
        return mRemoteService.sendFeedback(jwttoken, feedback);
    }

    public Flowable<Response<Object>> deleteChat(String jwttoken) {
        return mRemoteService.deletechat(jwttoken);
    }

    public Flowable<Response<Root>> getRoute(String jwttoken) {
        return mRemoteService.getRoute(jwttoken);
    }

    public Flowable<Response<HarisApiWriteToChat>> writeToChat(String jwttoken, Message message) {
        return mRemoteService.writeToChat(jwttoken, message);
    }
}
