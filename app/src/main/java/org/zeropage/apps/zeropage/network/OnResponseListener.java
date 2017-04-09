package org.zeropage.apps.zeropage.network;

import retrofit2.Call;
import retrofit2.Response;

@FunctionalInterface
public interface OnResponseListener<T> {
    void onSuccessful(Call<T> call, Response<T> response);
}
