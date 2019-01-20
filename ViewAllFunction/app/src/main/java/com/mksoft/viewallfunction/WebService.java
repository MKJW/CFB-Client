package com.mksoft.viewallfunction;


import java.util.ArrayList;
import java.util.List;

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
    Call<FunctionArray> postFunction(@Body FunctionArray functionArray);

}
