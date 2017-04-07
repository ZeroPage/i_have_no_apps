package org.zeropage.apps.zeropage.network.function;

import android.support.annotation.NonNull;

public enum FunctionType {
    AUTHENTICATE("Authenticate"), SET_MEMBER("SetMember");

    String mFunctionName;

    FunctionType(String functionName) {
        mFunctionName = functionName;
    }

    @NonNull
    public String getFunctionName() {
        return mFunctionName;
    }
}
