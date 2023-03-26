package com.example.mvp.androidmvparchitectureexample.ui.login;

import com.example.mvp.androidmvparchitectureexample.ui.base.IBaseView;

/**
 * ALL RIGHTS RESERVED - ALEXANDROS KOURTIS
 */

public interface ContractLogin {

    interface ContractPresenter {
        void login();
    }

    interface ContractView extends IBaseView {
    }
}
