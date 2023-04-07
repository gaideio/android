package com.example.mvp.androidmvparchitectureexample.ui.login;

import com.example.mvp.androidmvparchitectureexample.data.remote.model.profile.Profile;
import com.example.mvp.androidmvparchitectureexample.ui.base.IBaseView;

/**
 * ALL RIGHTS RESERVED
 */

public interface ContractLogin {

    interface ContractPresenter {
        void login(String googletoken);

        void checkjwtforautologin(String jwttoken);
    }

    interface ContractView extends IBaseView {
        void onLoginDone(Profile profile);

        void oncheckjwtforautologin(Boolean status);
    }
}
