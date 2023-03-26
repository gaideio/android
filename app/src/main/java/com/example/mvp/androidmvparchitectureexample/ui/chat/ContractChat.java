package com.example.mvp.androidmvparchitectureexample.ui.chat;

import com.example.mvp.androidmvparchitectureexample.ui.base.IBaseView;

/**
 * ALL RIGHTS RESERVED - ALEXANDROS KOURTIS
 */

public interface ContractChat {

    interface ContractPresenter {
        void say();
    }

    interface ContractView extends IBaseView {
    }
}
