package com.example.mvp.androidmvparchitectureexample.ui.profile;

import android.content.Context;
import android.util.Log;

import com.example.mvp.androidmvparchitectureexample.data.local.LocalDataSource;
import com.example.mvp.androidmvparchitectureexample.data.remote.RemoteDataSource;
import com.example.mvp.androidmvparchitectureexample.ui.base.BasePresenter;
import com.example.mvp.androidmvparchitectureexample.utils.NetworkUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * ALL RIGHTS RESERVED - ALEXANDROS KOURTIS
 */

public class ProfilePresenter extends BasePresenter<ContractProfile.ContractView> implements ContractProfile.ContractPresenter {

    private static final String TAG = ProfilePresenter.class.getSimpleName();

    LocalDataSource mLocalDataSource;
    RemoteDataSource mRemoteDataSource;

    public ProfilePresenter(LocalDataSource mLocalDataSource, RemoteDataSource mRemoteDataSource) {
        this.mLocalDataSource = mLocalDataSource;
        this.mRemoteDataSource = mRemoteDataSource;
    }

    @Override
    public void getProfile(Context context) {
        if (NetworkUtil.isNetworkConnected(context)) {
            getProfileFromApi();
        } else {
            getProfileFromDb();
        }
    }

    @Override
    public void getProfileFromApi() {
        getView().showLoading();

        mDisposable = mRemoteDataSource.getProfile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {

                            if (!isViewAttached()) {
                                return;
                            }

                            getView().hideLoading();
                            if (response.isSuccessful()) {
//                                saveArticles(response.body().getmArticles());
//                                getView().onArtilesReady(response.body().getmArticles());
                            }
                        },
                        throwable -> {
                            getView().hideLoading();
                            Log.e(TAG, throwable.getMessage());
                        });
    }

    @Override
    public void getProfileFromDb() {

    }

    @Override
    public void updateProfile() {

    }

    @Override
    public void update() {

    }

    @Override
    public void clear() {

    }

    @Override
    public void edit() {

    }

    @Override
    public void save() {

    }
}
