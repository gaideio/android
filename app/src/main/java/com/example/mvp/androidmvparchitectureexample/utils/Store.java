/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.utils;

import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.HarisApiChat;
import com.example.mvp.androidmvparchitectureexample.data.remote.model.chat.getroute.Root;

public class Store {

    private static Store single_instance = null;
    private Root root;
    private HarisApiChat harisApiChat;
    private AuthInfo authInfo;

    private Store() {
        root = new Root();
        harisApiChat = new HarisApiChat();
        authInfo = AuthInfo.getInstance();
    }

    public static Store getSingle_instance() {
        return single_instance;
    }

    public static void setSingle_instance(Store single_instance) {
        Store.single_instance = single_instance;
    }

    public static synchronized Store getInstance() {
        if (single_instance == null) {
            single_instance = new Store();
        }

        return single_instance;
    }

    public Root getRoot() {
        return root;
    }

    public void setRoot(Root root) {
        this.root = root;
    }

    public HarisApiChat getHarisApiChat() {
        return harisApiChat;
    }

    public void setHarisApiChat(HarisApiChat harisApiChat) {
        this.harisApiChat = harisApiChat;
    }

    public AuthInfo getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(AuthInfo authInfo) {
        this.authInfo = authInfo;
    }
}
