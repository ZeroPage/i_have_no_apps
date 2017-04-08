package org.zeropage.apps.zeropage.network;


import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class NetworkQueue {
    private static NetworkQueue sInstance;

    private RequestQueue mRequestQueue;

    @NonNull
    public synchronized static NetworkQueue getInstance(@NonNull Context context) {
        if (sInstance == null) {
            sInstance = new NetworkQueue(context);
        }

        return sInstance;
    }

    private NetworkQueue(@NonNull Context context) {
        mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public <T> void addRequestToQueue(@NonNull Request<T> request) {
        mRequestQueue.add(request);
    }
}