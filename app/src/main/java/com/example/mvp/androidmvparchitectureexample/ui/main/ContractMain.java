/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.ui.main;

import android.content.Context;

import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.getroute.Root;
import com.example.mvp.androidmvparchitectureexample.ui.base.IBaseView;

public interface ContractMain {

    interface ContractPresenter {
        void logout(Context context);

        void deleteChat(String jwttoken);

        void openChat();

        void goPreferences();

        void getRoute(String jwttoken);

        void goProfile();

        void record();
    }

    interface ContractView extends IBaseView {
        void onDeleteChat(boolean successful);

        void routeReady(Root root);
    }
}
