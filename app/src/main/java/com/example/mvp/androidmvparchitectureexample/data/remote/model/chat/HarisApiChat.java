/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.chat;

import java.util.List;

public class HarisApiChat {
    private List<Message> Chat;
    private String Error;

    public HarisApiChat() {
    }

    public HarisApiChat(List<Message> chat, String error) {
        Chat = chat;
        Error = error;
    }

    public List<Message> getChat() {
        return Chat;
    }

    public void setChat(List<Message> chat) {
        Chat = chat;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }
}
