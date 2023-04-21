/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.ui.feedback;

import com.example.mvp.androidmvparchitectureexample.data.remote.model.Feedback;
import com.example.mvp.androidmvparchitectureexample.ui.base.IBaseView;

public interface ContractFeedback {

    interface ContractPresenter {
        void sendFeedback(Feedback feedback, String jwttoken);
    }

    interface ContractView extends IBaseView {
        void onFeedbackSent(boolean status);
    }
}
