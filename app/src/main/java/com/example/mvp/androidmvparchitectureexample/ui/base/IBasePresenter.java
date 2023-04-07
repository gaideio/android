package com.example.mvp.androidmvparchitectureexample.ui.base;

/**
 * ALL RIGHTS RESERVED
 */

public interface IBasePresenter<V extends IBaseView> {

    void attachView(V view);

    void detachView();

    boolean isViewAttached();
}
