package org.zeropage.apps.zeropage.network;

import android.support.annotation.NonNull;

public enum RequestParameter {
    TOKEN("token"), USERNAME("username");

    String mParam;

    RequestParameter(String param) {
        mParam = param;
    }

    @Override
    @NonNull
    public String toString() {
        return mParam;
    }
}