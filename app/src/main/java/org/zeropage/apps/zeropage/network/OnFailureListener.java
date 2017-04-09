package org.zeropage.apps.zeropage.network;

import retrofit2.Call;

@FunctionalInterface
public interface OnFailureListener<T> {
    void onFailure(Call<T> call, Throwable throwable);
}
