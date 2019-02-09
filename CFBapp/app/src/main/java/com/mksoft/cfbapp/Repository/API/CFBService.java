package com.mksoft.cfbapp.Repository.API;



import com.mksoft.cfbapp.DataType.FunctionArray;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CFBService {

    @GET("/equation/{function}")
    Call<FunctionArray> getFunction(
            @Header("Authorization") String authorization,
            @Path("function") String equation);

    @GET("/equations")
    Call<ArrayList<FunctionArray>> getAllFunction(
            @Header("Authorization") String authorization
    );



    @POST("/save")
    Call<String> postFunction(
            @Header("Authorization") String authorization,
            @Body FunctionArray functionArray
    );




}
