/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.ui.profile;

import android.content.Context;
import android.util.Log;

import com.example.mvp.androidmvparchitectureexample.data.local.LocalDataSource;
import com.example.mvp.androidmvparchitectureexample.data.remote.RemoteDataSource;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.profile.Profile;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.profile.UpdateProfile;
import com.example.mvp.androidmvparchitectureexample.ui.base.BasePresenter;
import com.example.mvp.androidmvparchitectureexample.utils.NetworkUtil;

import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class ProfilePresenter extends BasePresenter<ContractProfile.ContractView> implements ContractProfile.ContractPresenter {

    private static final String TAG = ProfilePresenter.class.getSimpleName();

    LocalDataSource mLocalDataSource;
    RemoteDataSource mRemoteDataSource;

    public ProfilePresenter(LocalDataSource mLocalDataSource, RemoteDataSource mRemoteDataSource) {
        this.mLocalDataSource = mLocalDataSource;
        this.mRemoteDataSource = mRemoteDataSource;
    }

    @Override
    public void getProfile(Context context, String jwttoken) {
        if (NetworkUtil.isNetworkConnected(context)) {
            getProfileFromApi(jwttoken);
        }
//        else {
//            getProfileFromDb();
//        }
    }

    @Override
    public void getProfileFromApi(String jwttoken) {
        getView().showLoading();

        mDisposable = mRemoteDataSource.getProfile(jwttoken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {

                            if (!isViewAttached()) {
                                return;
                            }

                            getView().hideLoading();
                            if (response.isSuccessful()) {
                                Profile profile = new Profile();
                                profile.setFullname(Objects.requireNonNull(response.body()).getFullname());
                                getView().onProfileReady(profile);
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
    public void updateProfile(String jwttoken, String fullname) {
        getView().showLoading();

        UpdateProfile updateProfile = new UpdateProfile();
        updateProfile.setFullname(fullname);

        mDisposable = mRemoteDataSource.updateProfile(jwttoken, updateProfile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            if (!isViewAttached()) {
                                return;
                            }

                            getView().onProfileUpdated(response.isSuccessful());

                            getView().hideLoading();
                        },
                        throwable -> {
                            getView().hideLoading();
                            Log.e(TAG, throwable.getMessage());
                        });
    }

    @Override
    public void deleteAccount(String jwttoken) {
        getView().showLoading();

        mDisposable = mRemoteDataSource.deleteAccount(jwttoken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            if (!isViewAttached()) {
                                return;
                            }

                            getView().onAccountDeleted(response.isSuccessful());

                            getView().hideLoading();
                        },
                        throwable -> {
                            getView().hideLoading();
                            Log.e(TAG, throwable.getMessage());
                        });
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
