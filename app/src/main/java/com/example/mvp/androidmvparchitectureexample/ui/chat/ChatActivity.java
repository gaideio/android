package com.example.mvp.androidmvparchitectureexample.ui.chat;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.mvp.androidmvparchitectureexample.GaideioApp;
import com.example.mvp.androidmvparchitectureexample.R;
import com.example.mvp.androidmvparchitectureexample.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * ALL RIGHTS RESERVED - ALEXANDROS KOURTIS
 */

public class ChatActivity extends BaseActivity implements ContractChat.ContractView {

    private static final String TAG = ChatActivity.class.getSimpleName();

    @Inject
    ChatPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_chat_me);
        GaideioApp.getChatComponent().inject(this);
        mPresenter.attachView(this);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    protected void setUp() {

    }
}
