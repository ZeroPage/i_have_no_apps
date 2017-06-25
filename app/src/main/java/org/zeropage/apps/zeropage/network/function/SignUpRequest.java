package org.zeropage.apps.zeropage.network.function;


import android.support.annotation.NonNull;

import com.annimon.stream.function.FunctionalInterface;

import org.zeropage.apps.zeropage.network.common.RequestParameter;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

@FunctionalInterface
public interface SignUpRequest {
    String BASE_URL = "https://us-central1-zapp-c562d.cloudfunctions.net/";

    @POST(FunctionType.SIGN_UP)
    @FormUrlEncoded
    Call<String> execute(@Field(RequestParameter.USERNAME) @NonNull String username,
                         @Field(RequestParameter.TOKEN) @NonNull String fcmToken);
}