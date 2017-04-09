package org.zeropage.apps.zeropage.network;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallbackWrapper<T> {
    private OnResponseListener<T> mResponseListener;
    private OnFailureListener<T> mFailureListener;

    public CallbackWrapper(OnResponseListener<T> responseListener, OnFailureListener<T> failureListener) {
        mResponseListener = responseListener;
        mFailureListener = failureListener;
    }

    public Callback<T> getCallback() {
        return new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                mResponseListener.onSuccessful(call, response);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                mFailureListener.onFailure(call, t);
            }
        };
    }
}
