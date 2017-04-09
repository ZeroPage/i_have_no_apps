package org.zeropage.apps.zeropage.network;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class NetworkRequest extends StringRequest {
    private RequestInfo mRequestInfo;

    public NetworkRequest(@NonNull RequestInfo requestInfo,
                          @Nullable Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        super(Request.Method.POST, requestInfo.getBaseRequestUrl() + requestInfo.getAdditionalUrl(),
                listener, errorListener);

        mRequestInfo = requestInfo;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        if (mRequestInfo.getRequestData() == null) {
            return null;
        }

        Map<String, String> requestParams = new HashMap<>();
        for (Pair<String, String> data : mRequestInfo.getRequestData()) {
            requestParams.put(data.first, data.second);
        }

        return requestParams;
    }
}