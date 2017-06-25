package org.zeropage.apps.zeropage.network.common;

import retrofit2.Call;

@FunctionalInterface
public interface OnFailureListener<T> {
    void onFailure(Call<T> call, Throwable throwable);
}
