package com.mksoft.loginproject.DI;


import com.mksoft.loginproject.Repository.WebService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class CFBServiceModule {

    @Provides
    WebService provideWebService(){
        return new Retrofit.Builder()
                .baseUrl("http://114.202.9.170:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(WebService.class);
    }
}
