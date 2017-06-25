package org.zeropage.apps.zeropage.network.common;

import android.support.annotation.NonNull;
import android.util.Log;

import java.lang.reflect.Method;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public final class RequestSender<T> {
    private static final String TAG = RequestSender.class.getSimpleName();
    private static final String EXCEPTION_MESSAGE
            = "The class isn't a proper class for the request.";

    private Class<T> mRequestClass;

    public RequestSender(@NonNull Class<T> requestClass) {
        if (!RequestHelper.isProperRequestClass(requestClass)) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        mRequestClass = requestClass;
    }

    public void sendRequest(@NonNull CallbackWrapper<String> callback, Object... requestArgs) {
        String baseUrl = RequestHelper.getBaseUrl(mRequestClass);
        T request = makeRequest(baseUrl);
        Call<String> queue = executeRequest(request, requestArgs);

        queue.enqueue(callback.getCallback());
    }

    private T makeRequest(String baseUrl) {
        Log.i(TAG, "Making request. BaseUrl = " + baseUrl + ", Class = " + mRequestClass.getSimpleName());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        return retrofit.create(mRequestClass);
    }

    private Call<String> executeRequest(T request, Object... requestArgs) {
        try {
            Method executeMethod = mRequestClass.getMethods()[0];

            @SuppressWarnings("unchecked")
            Call<String> queue = (Call<String>) executeMethod.invoke(request, requestArgs);
            return queue;
        } catch (Exception exception) {
            Log.e(TAG, "RequestExecute has been failed.");
            throw new UnsupportedOperationException();
        }
    }
}