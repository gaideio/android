/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.ui.main;

import android.content.Context;
import android.util.Log;

import com.example.mvp.androidmvparchitectureexample.data.local.LocalDataSource;
import com.example.mvp.androidmvparchitectureexample.data.remote.RemoteDataSource;
import com.example.mvp.androidmvparchitectureexample.ui.base.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<ContractMain.ContractView> implements ContractMain.ContractPresenter {

    private static final String TAG = MainPresenter.class.getSimpleName();

    LocalDataSource mLocalDataSource;
    RemoteDataSource mRemoteDataSource;

    public MainPresenter(LocalDataSource mLocalDataSource, RemoteDataSource mRemoteDataSource) {
        this.mLocalDataSource = mLocalDataSource;
        this.mRemoteDataSource = mRemoteDataSource;
    }

    public MainPresenter() {
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

    @Override
    public void logout(Context context) {

    }

    @Override
    public void deleteChat(String jwttoken) {
        getView().showLoading();

        // delete chat

        mDisposable = mRemoteDataSource.deleteChat(jwttoken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            if (!isViewAttached()) {
                                return;
                            }

                            getView().onDeleteChat(response.isSuccessful());

                            getView().hideLoading();
                        },
                        throwable -> {
                            getView().hideLoading();
                            Log.e(TAG, throwable.getMessage());
                        });
    }

    @Override
    public void openChat() {

    }

    @Override
    public void goPreferences() {

    }

    @Override
    public void goProfile() {

    }

    @Override
    public void record() {

    }
}
