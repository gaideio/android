/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.ui.base;

import com.example.mvp.androidmvparchitectureexample.utils.Store;

public interface IBasePresenter<V extends IBaseView> {
    void attachView(V view);

    void detachView();

    Store getStore();

    boolean isViewAttached();
}
