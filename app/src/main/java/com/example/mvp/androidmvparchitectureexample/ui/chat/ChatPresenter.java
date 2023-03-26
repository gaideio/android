package com.example.mvp.androidmvparchitectureexample.ui.chat;

import com.example.mvp.androidmvparchitectureexample.data.local.LocalDataSource;
import com.example.mvp.androidmvparchitectureexample.data.remote.RemoteDataSource;
import com.example.mvp.androidmvparchitectureexample.ui.base.BasePresenter;

/**
 * ALL RIGHTS RESERVED - ALEXANDROS KOURTIS
 */

public class ChatPresenter extends BasePresenter<ContractChat.ContractView> implements ContractChat.ContractPresenter {

    private static final String TAG = ChatPresenter.class.getSimpleName();

    LocalDataSource mLocalDataSource;
    RemoteDataSource mRemoteDataSource;

    public ChatPresenter(LocalDataSource mLocalDataSource, RemoteDataSource mRemoteDataSource) {
        this.mLocalDataSource = mLocalDataSource;
        this.mRemoteDataSource = mRemoteDataSource;
    }

    @Override
    public void say() {
        
    }
}
