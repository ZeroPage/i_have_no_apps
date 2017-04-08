package org.zeropage.apps.zeropage.network;

import android.support.annotation.NonNull;

public enum NetworkUrl {
    LOGIN("http://zerobot.herokuapp.com/hubot/otp/"),
    FUNCTION("https://us-central1-friendlychat-cc915.cloudfunctions.net/");

    String mBaseUrl;
    NetworkUrl(String baseUrl) {
        mBaseUrl = baseUrl;
    }

    @Override
    @NonNull
    public String toString() {
        return mBaseUrl;
    }
}
