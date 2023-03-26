/*
 * ALL RIGHTS RESERVED - ALEXANDROS KOURTIS
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.chat;

import java.util.List;

public class ChatResponse {

    private List<Message> messages;
    private String error;

    public ChatResponse(List<Message> messages, String error) {
        this.messages = messages;
        this.error = error;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
