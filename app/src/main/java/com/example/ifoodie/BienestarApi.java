package com.example.ifoodie;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BienestarApi {

    @FormUrlEncoded
    @POST("register")
    Call<String> postRegister(@Field("user") String user, @Field("email") String mail, @Field("password") String pass);
}
