package com.mksoft.mainbutton;


import com.mksoft.mainbutton.DataType.FunctionArray;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
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

}
