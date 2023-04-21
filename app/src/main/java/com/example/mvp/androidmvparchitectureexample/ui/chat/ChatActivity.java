/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.ui.chat;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvp.androidmvparchitectureexample.GaideioApp;
import com.example.mvp.androidmvparchitectureexample.R;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.HarisApiNewChat;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.HarisApiWriteToChat;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.Message;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.NewChatRequest;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.getroute.Root;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.getroute.Route;
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

    @BindView(R.id.inputthingy)
    EditText inputthingy;

    @Inject
    ChatPresenter mPresenter;

    private ChatAdapter chatAdapter;
    private List<Message> messages;

    private static boolean containsRouter(String text) {
        return text.contains("/router");
    }

    @OnClick(R.id.postadd)
    public void onViewClicked() {
        EditText editText = findViewById(R.id.inputthingy);
        Message message = new Message(editText.getText().toString());
        editText.setText("");

        if (containsRouter(message.getText())) {
            containsrouterflag = 1;
        }

        mPresenter.writeToChat(mPresenter.getStore().getAuthInfo().getJwttoken(), message);
        messages.add(message);
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
        mPresenter.getRoute(mPresenter.getStore().getAuthInfo().getJwttoken());
    }

    private void getChat() {
        mPresenter.getMessages(mPresenter.getStore().getAuthInfo().getJwttoken(), mPresenter.getStore().getAuthInfo().getEmail());
    }

    private void displayChatMessages() {
        chatAdapter = new ChatAdapter(this, new ArrayList<>());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(mLayoutManager);
        mRecyclerview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerview.setAdapter(chatAdapter);
        int lastItemPosition = chatAdapter.getItemCount() - 1;
        mLayoutManager.scrollToPosition(lastItemPosition);
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void routeReady(Root root) {
        StringBuilder sumlocation = new StringBuilder();

        for (Route route : root.getRoute()) {
            sumlocation.append(route.getName()).append(" | ");
        }

        TextView textView = findViewById(R.id.routetext);
        textView.setText(sumlocation.toString());

        mPresenter.bigboy();
        mPresenter.getStore().setRoot(root);
    }

    @Override
    public void onChatReady(List<Message> items) {
        for (Message message :
                items) {
            System.out.println(message);
        }
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
        inputthingy.setVisibility(View.GONE);
        floatingActionButton.hide();
    }

    @OnClick(R.id.newchatbtn)
    public void onNewChatBtnClicked() {
        NewChatRequest newChatRequest = new NewChatRequest();
        newChatRequest.setLocation("Athens, Greece, Syntagma");
        newChatRequest.setTime("2 hours");
        newChatRequest.setTransport("Walking");
        mPresenter.newChat(mPresenter.getStore().getAuthInfo().getJwttoken(), newChatRequest);
        button.setVisibility(View.GONE);
    }

    @Override
    public void onCreateChatResponse(HarisApiNewChat harisApiNewChat) {
        init();
        button.setVisibility(View.GONE);
        inputthingy.setVisibility(View.VISIBLE);
        floatingActionButton.show();
    }

    @Override
    public void onWriteToChatResponseReceived(HarisApiWriteToChat harisApiWriteToChat) {
        Message message = new Message(harisApiWriteToChat.getResponse());
        messages.add(message);
        chatAdapter = new ChatAdapter(this, messages);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(mLayoutManager);
        mRecyclerview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerview.setAdapter(chatAdapter);
        int lastItemPosition = chatAdapter.getItemCount() - 1;
        mLayoutManager.scrollToPosition(lastItemPosition);

        if (containsrouterflag == 1) {
            mPresenter.getRoute(mPresenter.getStore().getAuthInfo().getJwttoken());
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
        displayChatMessages();
        super.onResume();
    }

    @Override
    protected void setUp() {

    }
}
