package com.mksoft.cfbapp.Repository;


import com.mksoft.cfbapp.DataType.TokenClass;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface OauthService {
    @FormUrlEncoded
    @POST("/login")
    Call<String> insertUser(
            @Field("user") String user,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("/oauth/token")
    Call<TokenClass> getToken(
            @Header("Authorization") String authorization,
            @Field("grant_type") String grant_type,
            @Field("username") String user,
            @Field("password") String password
    );
}
