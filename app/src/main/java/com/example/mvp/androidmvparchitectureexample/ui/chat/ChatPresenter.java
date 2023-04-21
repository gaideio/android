/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.ui.chat;

import android.util.Log;

import com.example.mvp.androidmvparchitectureexample.data.local.LocalDataSource;
import com.example.mvp.androidmvparchitectureexample.data.remote.RemoteDataSource;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.Message;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.NewChatRequest;
import com.example.mvp.androidmvparchitectureexample.ui.base.BasePresenter;

import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ChatPresenter extends BasePresenter<ContractChat.ContractView> implements ContractChat.ContractPresenter {

    private static final String TAG = ChatPresenter.class.getSimpleName();

    LocalDataSource mLocalDataSource;
    RemoteDataSource mRemoteDataSource;

    public ChatPresenter(LocalDataSource mLocalDataSource, RemoteDataSource mRemoteDataSource) {
        this.mLocalDataSource = mLocalDataSource;
        this.mRemoteDataSource = mRemoteDataSource;
    }

    @Override
    public void getMessages(String jwttoken, String email) {
        // TODO
        // add context to switch to local repo if no network connection
        getMessagesFromApi(jwttoken, email);
    }

    @Override
    public void getMessagesFromApi(String jwttoken, String email) {
        getView().showLoading();

        mDisposable = mRemoteDataSource.getChat(jwttoken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            if (!isViewAttached()) {
                                return;
                            }

                            getView().hideLoading();

                            if (response.isSuccessful()) {
                                getView().onChatReady(Objects.requireNonNull(response.body()).getChat());
                            } else {
                                getView().onErrorLoadingChat();
                            }
                        },
                        throwable -> {
                            getView().hideLoading();
                            Log.e(TAG, throwable.getMessage());
                        });
    }

    @Override
    public void writeToChat(String jwttoken, Message message) {
        getView().showLoading();

        mDisposable = mRemoteDataSource.writeToChat(jwttoken, message)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            if (!isViewAttached()) {
                                return;
                            }

                            getView().onWriteToChatResponseReceived(response.body());

                            getView().hideLoading();
                        },
                        throwable -> {
                            getView().hideLoading();
                            Log.e(TAG, throwable.getMessage());
                        });
    }


    @Override
    public void bigboy() {

    }

    @Override
    public void newChat(String jwttoken, NewChatRequest newChatRequest) {
        getView().showLoading();

        mDisposable = mRemoteDataSource.newChat(jwttoken, newChatRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            if (!isViewAttached()) {
                                return;
                            }

                            getView().onCreateChatResponse(response.body());

                            getView().hideLoading();
                        },
                        throwable -> {
                            getView().hideLoading();
                            Log.e(TAG, throwable.getMessage());
                        });
    }

    @Override
    public void getRoute(String jwttoken) {
        mDisposable = mRemoteDataSource.getRoute(jwttoken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            if (!isViewAttached()) {
                                return;
                            }

                            getStore().setRoot(response.body());
                            getView().routeReady(response.body());
                        },
                        throwable -> {
                            Log.e(TAG, throwable.getMessage());
                        });
    }
}
