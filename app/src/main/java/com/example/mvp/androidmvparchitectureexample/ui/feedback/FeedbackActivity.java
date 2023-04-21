/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.ui.feedback;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mvp.androidmvparchitectureexample.GaideioApp;
import com.example.mvp.androidmvparchitectureexample.R;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.Feedback;
import com.example.mvp.androidmvparchitectureexample.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedbackActivity extends BaseActivity implements ContractFeedback.ContractView {

    @Inject
    FeedbackPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        GaideioApp.getFeedbackComponent().inject(this);
        mPresenter.attachView(this);
        ButterKnife.bind(this);
        onResume();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.sendbtn)
    public void onViewClicked() {
        String subject = ((TextView) findViewById(R.id.subject)).getText().toString();
        String message = ((TextView) findViewById(R.id.message)).getText().toString();

        Feedback feedback = new Feedback();
        feedback.setSubject(subject);
        feedback.setMessage(message);

        mPresenter.sendFeedback(feedback, mPresenter.getStore().getAuthInfo().getJwttoken());
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onFeedbackSent(boolean status) {
        if (status) {
            Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error! Message not sent", Toast.LENGTH_SHORT).show();
        }
    }
}

