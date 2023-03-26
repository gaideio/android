package com.example.mvp.androidmvparchitectureexample.ui.base;

import androidx.annotation.StringRes;

/**
 * ALL RIGHTS RESERVED - ALEXANDROS KOURTIS
 */

public interface IBaseView {
    void showLoading();

    void hideLoading();

    void showError(String errorMessage);

    void showError(@StringRes int errorId);
}
