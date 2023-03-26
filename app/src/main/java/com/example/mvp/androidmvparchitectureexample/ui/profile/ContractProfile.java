package com.example.mvp.androidmvparchitectureexample.ui.profile;

import android.content.Context;

import com.example.mvp.androidmvparchitectureexample.ui.base.IBaseView;

/**
 * ALL RIGHTS RESERVED - ALEXANDROS KOURTIS
 */

public interface ContractProfile {

    interface ContractPresenter {
        void getProfile(Context context);

        void getProfileFromApi();

        void getProfileFromDb();

        void updateProfile();

        void update();

        void clear();

        void edit();

        void save();
    }

    interface ContractView extends IBaseView {
        void onProfileReady();
    }
}
