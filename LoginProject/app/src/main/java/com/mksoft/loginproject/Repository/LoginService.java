package com.mksoft.loginproject.Repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.telecom.Call;
import android.util.Log;
import android.widget.Toast;

import com.mksoft.loginproject.DataType.TokenClass;

import javax.inject.Inject;

import retrofit2.Callback;
import retrofit2.Response;

public class LoginService {
    private OauthService oauthService;
    String Authorization = "Basic dHJ1c3RlZC1hcHA6c2VjcmV0";
    @Inject
    public LoginService(OauthService oauthService) {
        this.oauthService = oauthService;
    }

    public void getToken(final Context context, String grant_type, String username, String password){
        retrofit2.Call<TokenClass> call = oauthService.getToken(Authorization, grant_type, username, password);
        call.enqueue(new Callback<TokenClass>() {
            @Override
            public void onResponse(retrofit2.Call<TokenClass> call, Response<TokenClass> response) {
                if(response.isSuccessful() && response.body()!=null){
                    Toast.makeText(context, response.body().toString(), Toast.LENGTH_LONG).show();//test
                    Log.d("testLogin", response.body().toString());
                    SharedPreferences pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("access token", response.body().getAccess_token());
                    editor.commit();

                }
            }

            @Override
            public void onFailure(retrofit2.Call<TokenClass> call, Throwable t) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();//test
            }
        });
    }
}
