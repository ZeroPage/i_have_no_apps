package org.zeropage.apps.zeropage.network.utils;

import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

import org.zeropage.apps.zeropage.network.RequestInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestInfoBuilder {
    private String mBaseUrl;
    private String mAdditionalUrl;
    private Map<String, String> mRequestData = new HashMap<>(); // Arguments by parameters

    @NonNull
    public RequestInfoBuilder setBaseUrl(@NonNull String baseUrl) {
        mBaseUrl = baseUrl;
        return this;
    }

    @NonNull
    public RequestInfoBuilder setAdditionalUrl(@NonNull String additionalUrl) {
        mAdditionalUrl = additionalUrl;
        return this;
    }

    @NonNull
    public RequestInfoBuilder addData(@NonNull String param, @NonNull String arg) {
        mRequestData.put(param, arg);
        return this;
    }

    @NonNull
    public RequestInfo createInformation() {
        return new BuiltInformation();
    }

    private class BuiltInformation implements RequestInfo {
        @Override
        @NonNull
        public String getBaseRequestUrl() {
            return mBaseUrl;
        }

        @Override
        @NonNull
        public String getAdditionalUrl() {
            return mAdditionalUrl;
        }

        @Override
        @NonNull
        public List<Pair<String, String>> getRequestData() {
            List<Pair<String, String>> data = new ArrayList<>();

            for (Map.Entry<String, String> entry : mRequestData.entrySet()) {
                data.add(new Pair<>(entry.getKey(), entry.getValue()));
            }

            return data;
        }

        @Override
        @NonNull
        public String toString() {
            StringBuilder messageBuilder = new StringBuilder("RequestInfo created by Builder.\n");
            messageBuilder.append(String.format("BaseUrl = %s, ", getBaseRequestUrl()));
            messageBuilder.append(String.format("AdditionalUrl = %s\n", getAdditionalUrl()));
            messageBuilder.append("Data : \n");

            for (Pair<String, String> data : getRequestData()) {
                messageBuilder.append(String.format("Param = %s, Arg = %s", data.first, data.second));
            }

            return messageBuilder.toString();
        }
    }
}