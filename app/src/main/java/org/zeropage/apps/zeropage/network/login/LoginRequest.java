package org.zeropage.apps.zeropage.network.login;

import android.support.annotation.NonNull;

import com.annimon.stream.function.FunctionalInterface;

import org.zeropage.apps.zeropage.network.common.RequestParameter;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

@FunctionalInterface
public interface LoginRequest {
    String BASE_URL = "http://zerobot.herokuapp.com/hubot/otp/";

    @POST("{username}/")
    @FormUrlEncoded
    Call<String> login(@Path("username") @NonNull String username,
                       @Field(RequestParameter.TOKEN) @NonNull String token);
}