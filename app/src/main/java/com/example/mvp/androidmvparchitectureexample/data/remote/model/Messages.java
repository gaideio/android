/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.data.remote.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.Message;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Messages implements Parcelable {

    public static final Creator<Messages> CREATOR = new Creator<>() {
        @Override
        public Messages createFromParcel(Parcel source) {
            return new Messages(source);
        }

        @Override
        public Messages[] newArray(int size) {
            return new Messages[size];
        }
    };

    @Expose
    @SerializedName("messages")
    private List<Message> mMessages;


    public Messages() {
    }

    protected Messages(Parcel in) {
        mMessages = new ArrayList<>();
        in.readList(mMessages, Message.class.getClassLoader());
    }

    public List<Message> getmMessages() {
        return mMessages;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(mMessages);
    }
}
