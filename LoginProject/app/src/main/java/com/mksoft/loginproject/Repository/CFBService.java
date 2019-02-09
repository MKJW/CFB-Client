package com.mksoft.loginproject.Repository;




import com.mksoft.loginproject.DataType.FunctionArray;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
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
