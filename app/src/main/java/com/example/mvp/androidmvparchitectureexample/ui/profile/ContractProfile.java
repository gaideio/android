/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.ui.profile;

import android.content.Context;

import com.example.mvp.androidmvparchitectureexample.data.remote.model.profile.Profile;
import com.example.mvp.androidmvparchitectureexample.ui.base.IBaseView;

public interface ContractProfile {

    interface ContractPresenter {
        void getProfile(Context context, String jwttoken);

        void getProfileFromApi(String jwttoken);

        void getProfileFromDb();

        void updateProfile(String jwttoken, String fullname);

        void deleteAccount(String jwttoken);

        void update();

        void clear();

        void edit();

        void save();
    }

    interface ContractView extends IBaseView {
        void onProfileReady(Profile profile);

        void onProfileUpdated(Boolean isUpdateSuccessful);

        void onAccountDeleted(Boolean isAccountDeleted);
    }
}
