/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.ui.chat;

import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvp.androidmvparchitectureexample.GaideioApp;
import com.example.mvp.androidmvparchitectureexample.R;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.Message;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.NewChatRequest;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.NewChatResponse;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.SendToChatResponse;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.getroute.Itinerary;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.getroute.Root;
import com.example.mvp.androidmvparchitectureexample.ui.base.BaseActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends BaseActivity implements ContractChat.ContractView {

    private static int containsrouterflag = 0;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @BindView(R.id.content_error)
    RelativeLayout mErrorContainer;
    @BindView(R.id.postadd)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.newchatbtn)
    Button button;
    @Inject
    ChatPresenter mPresenter;
    private ChatAdapter chatAdapter;
    private List<Message> messages;

    private static boolean containsRouter(String text) {
        return text.contains("/router");
    }

    private String getJWTTokenFromSharedPreferences() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        return "Bearer " + prefs.getString("jwttoken", null);
    }

    @OnClick(R.id.postadd)
    public void onViewClicked() {
        EditText editText = findViewById(R.id.inputthingy);
        Message message = new Message(editText.getText().toString());
        editText.setText("");

        if (containsRouter(message.getText())) {
            containsrouterflag = 1;
        }

        mPresenter.writeToChat(getJWTTokenFromSharedPreferences(), message);
        messages.add(message);
        chatAdapter = new ChatAdapter(this, messages);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(mLayoutManager);
        mRecyclerview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerview.setAdapter(chatAdapter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        GaideioApp.getChatComponent().inject(this);
        mPresenter.attachView(this);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        getChat();
        displayChatMessages();
    }

    private void getChat() {
        mPresenter.getMessages(getJWTTokenFromSharedPreferences(), getEmailFromSharedPreferences());
    }

    private String getEmailFromSharedPreferences() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        return prefs.getString("email", null);
    }

    private void displayChatMessages() {
        chatAdapter = new ChatAdapter(this, new ArrayList<>());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(mLayoutManager);
        mRecyclerview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerview.setAdapter(chatAdapter);
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void routeReady(Root root) {
        String sumlocation = "";

        for (Itinerary itinerary : root.getRoute().get(0).getItinerary()) {
            sumlocation = itinerary.getLocation() + " ";
        }

        Toast.makeText(this, sumlocation, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChatReady(List<Message> items) {
        messages = items;

        if (items != null && !items.isEmpty()) {
            hideErrorContainer();
            chatAdapter.setItems(items);
        } else {
//            showErrorContainer();
            showNewChatBtn();
        }
    }

    @Override
    public void onErrorLoadingChat() {
        showNewChatBtn();
    }

    private void showNewChatBtn() {
        button.setVisibility(View.VISIBLE);
    }

    private void preprompt() {

    }

    @OnClick(R.id.newchatbtn)
    public void onNewChatBtnClicked() {
        NewChatRequest newChatRequest = new NewChatRequest();
        newChatRequest.setLocation("Athens, Greece, Syntagma");
        newChatRequest.setTime("2 hours");
        newChatRequest.setTransport("Walking");
        mPresenter.newChat(getJWTTokenFromSharedPreferences(), newChatRequest);
        button.setVisibility(View.GONE);
    }

    @Override
    public void onCreateChatResponse(NewChatResponse newChatResponse) {
        init();
        button.setVisibility(View.GONE);
    }

    @Override
    public void onWriteToChatResponseReceived(SendToChatResponse sendToChatResponse) {
        Message message = new Message(sendToChatResponse.getResponse());
        messages.add(message);
        chatAdapter = new ChatAdapter(this, messages);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(mLayoutManager);
        mRecyclerview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerview.setAdapter(chatAdapter);

        if (containsrouterflag == 1) {
            mPresenter.getRoute(getJWTTokenFromSharedPreferences());
        }

        containsrouterflag = 0;
    }

    private void showErrorContainer() {
        mErrorContainer.setVisibility(View.VISIBLE);
    }

    private void hideErrorContainer() {
        mErrorContainer.setVisibility(View.GONE);

    }

    @Override
    protected void onResume() {
        Window window = getWindow();
        Point size = new Point();
        assert window != null;
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        window.setLayout((int) (size.x * 0.9), (int) (size.y * 0.75));
        window.setGravity(Gravity.CENTER);
        super.onResume();
    }

    @Override
    protected void setUp() {

    }
}
