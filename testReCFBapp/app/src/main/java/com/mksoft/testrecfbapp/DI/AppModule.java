package com.mksoft.testrecfbapp.DI;


import android.app.Application;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mksoft.testrecfbapp.Repository.APIRepo;
import com.mksoft.testrecfbapp.Repository.LoginRepo;
import com.mksoft.testrecfbapp.Repository.webservice.APIService;
import com.mksoft.testrecfbapp.Repository.webservice.LoginService;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class AppModule {


// --- NETWORK INJECTION ---

    private static String BASE_URL = "http://114.202.9.170:8080";

    @Provides
    Gson provideGson() { return new GsonBuilder().create(); }

    @Provides
    Retrofit provideRetrofit(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    APIService provideApiWebservice(Retrofit restAdapter) {
        return restAdapter.create(APIService.class);
    }
    @Provides
    @Singleton
    LoginService provideLoginWebservice(Retrofit restAdapter) {
        return restAdapter.create(LoginService.class);
    }


    // --- REPOSITORY INJECTION ---
    @Provides
    @Singleton
    APIRepo provideAPIRepository(APIService webservice) {
        return new APIRepo(webservice);
    }

    @Provides
    @Singleton
    LoginRepo provideLoginRepository(LoginService webservice) {
        return new LoginRepo(webservice);
    }

}
