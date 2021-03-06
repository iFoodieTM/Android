package com.example.ifoodie;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BienestarApi {

    @FormUrlEncoded
    @POST("store")
    Call<String> postRegister(@Field("email") String email, @Field("user_name") String userName, @Field("password") String password);

    @FormUrlEncoded
    @POST("login")
    Call<String> postLogin(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("recoverPassword")
    Call<String> postRecover(@Field("email") String email);
}
