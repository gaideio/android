/*
 * ALL RIGHTS RESERVED - ALEXANDROS KOURTIS
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.chat;

public class SendToChatRequest {

    private Message message;

    public SendToChatRequest(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
