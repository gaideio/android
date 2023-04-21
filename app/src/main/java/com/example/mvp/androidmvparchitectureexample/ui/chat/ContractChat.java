/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.ui.chat;

import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.HarisApiNewChat;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.HarisApiWriteToChat;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.Message;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.NewChatRequest;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.getroute.Root;
import com.example.mvp.androidmvparchitectureexample.ui.base.IBaseView;

import java.util.List;

public interface ContractChat {

    interface ContractPresenter {
        // TODO: add context here:
        void getMessages(String jwttoken, String email);

        void getMessagesFromApi(String jwttoken, String email);

        void writeToChat(String jwttoken, Message message);

        void newChat(String jwttoken, NewChatRequest newChatRequest);

        void bigboy();

        void getRoute(String jwttoken);
//        void getMessagesFromDb();
//        void saveArticles(List<ArticleEntity> items);
    }

    interface ContractView extends IBaseView {
        void routeReady(Root root);

        void onChatReady(List<Message> messages);

        void onErrorLoadingChat();

        void onCreateChatResponse(HarisApiNewChat harisApiNewChat);

        void onWriteToChatResponseReceived(HarisApiWriteToChat harisApiWriteToChat);
    }
}
