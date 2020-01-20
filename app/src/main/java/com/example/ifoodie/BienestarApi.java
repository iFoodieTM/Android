package com.example.ifoodie;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BienestarApi {

    @FormUrlEncoded
    @POST("register")
    Call<String> postRegister(@Field("name") String name, @Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("login")
    Call<String> postLogin(@Field("email") String email, @Field("password") String password);
}
