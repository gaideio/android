/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model.chat;

import java.util.Objects;

public class ChatMsg {
    private String text;

    public ChatMsg(String text) {
        this.text = text;
    }

    public ChatMsg() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ChatMsg{" +
                "text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChatMsg chatMsg = (ChatMsg) o;
        return Objects.equals(text, chatMsg.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}
