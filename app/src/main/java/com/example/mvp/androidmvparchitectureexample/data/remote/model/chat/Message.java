/*
 * ALL RIGHTS RESERVED - ALEXANDROS KOURTIS
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.chat;

public class Message {
    private String text;

    public Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}