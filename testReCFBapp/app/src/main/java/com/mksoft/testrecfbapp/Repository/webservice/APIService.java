package com.mksoft.testrecfbapp.Repository.webservice;




import com.mksoft.testrecfbapp.Repository.Data.FunctionArray;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {

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
