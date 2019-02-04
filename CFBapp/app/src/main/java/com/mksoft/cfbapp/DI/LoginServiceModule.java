package com.mksoft.cfbapp.DI;


import com.mksoft.cfbapp.Repository.OauthService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class LoginServiceModule {

    @Provides
    OauthService provideOauthService(){
        return new Retrofit.Builder()
                .baseUrl("http://114.202.9.170:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(OauthService.class);
    }
}
