package com.example.mvp.androidmvparchitectureexample.ui.main;

import android.content.Context;

import com.example.mvp.androidmvparchitectureexample.ui.base.IBaseView;

/**
 * ALL RIGHTS RESERVED - ALEXANDROS KOURTIS
 */

public interface ContractMain {

    interface ContractPresenter {
        void logout(Context context);

        void openChat();

        void goPreferences();

        void goProfile();

        void record();
    }

    interface ContractView extends IBaseView {
    }
}
