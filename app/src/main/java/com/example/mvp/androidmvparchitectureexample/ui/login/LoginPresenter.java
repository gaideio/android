/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.ui.login;

import android.util.Log;

import com.example.mvp.androidmvparchitectureexample.data.remote.RemoteDataSource;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.login.ModelLoginRequest;
import com.example.mvp.androidmvparchitectureexample.ui.base.BasePresenter;

import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<ContractLogin.ContractView> implements ContractLogin.ContractPresenter {

    private static final String TAG = LoginPresenter.class.getSimpleName();

    RemoteDataSource mRemoteDataSource;

    public LoginPresenter(RemoteDataSource mRemoteDataSource) {
        this.mRemoteDataSource = mRemoteDataSource;
    }

    @Override
    public void login(String googletoken) {
        getView().showLoading();

        ModelLoginRequest modelLoginRequest = new ModelLoginRequest(googletoken);

        mDisposable = mRemoteDataSource.login(modelLoginRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {

                            if (!isViewAttached()) {
                                return;
                            }

                            getView().hideLoading();
                            if (response.isSuccessful()) {
                                getView().onLoginDone(Objects.requireNonNull(response.body()));
                            }
                        },
                        throwable -> {
                            getView().hideLoading();
                            Log.e(TAG, throwable.getMessage());
                        });
    }

    @Override
    public void checkjwtforautologin(String jwttoken) {
        getView().showLoading();

        mDisposable = mRemoteDataSource.checkjwtforautologin(jwttoken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {

                            if (!isViewAttached()) {
                                return;
                            }

                            getView().hideLoading();
                            if (response.isSuccessful()) {
                                getView().oncheckjwtforautologin(true);
                                return;
                            }

                            getView().oncheckjwtforautologin(false);
                        },
                        throwable -> {
                            getView().hideLoading();
                            Log.e(TAG, throwable.getMessage());
                        });
    }
}
