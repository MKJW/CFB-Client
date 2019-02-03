package com.mksoft.loginproject.Repository;



import com.mksoft.loginproject.DataType.FunctionArray;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebService {

    @GET("/equation/{function}")
    Call<FunctionArray> getFunction(@Path("function") String equation);

    @GET("/equations")
    Call<ArrayList<FunctionArray>> getAllFunction();



    @POST("/save")
    Call<String> postFunction(@Body FunctionArray functionArray);

    @FormUrlEncoded
    @POST("/login")
    Call<ResponseBody> insertUser(
            @Field("user") String user,
            @Field("password") String password);


}
