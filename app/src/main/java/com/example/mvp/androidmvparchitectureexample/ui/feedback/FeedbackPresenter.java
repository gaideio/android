/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.ui.feedback;

import com.example.mvp.androidmvparchitectureexample.data.remote.RemoteDataSource;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.Feedback;
import com.example.mvp.androidmvparchitectureexample.ui.base.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FeedbackPresenter extends BasePresenter<ContractFeedback.ContractView> implements ContractFeedback.ContractPresenter {

    private static final String TAG = FeedbackPresenter.class.getSimpleName();

    RemoteDataSource mRemoteDataSource;

    public FeedbackPresenter(RemoteDataSource mRemoteDataSource) {
        this.mRemoteDataSource = mRemoteDataSource;
    }

    @Override
    public void sendFeedback(Feedback feedback, String jwttoken) {
        getView().showLoading();

        mDisposable = mRemoteDataSource.sendFeedback(feedback, jwttoken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {

                            if (!isViewAttached()) {
                                return;
                            }

                            getView().hideLoading();
                            getView().onFeedbackSent(response.isSuccessful());
                        },
                        throwable -> {
                            getView().hideLoading();
                        });
    }
}
