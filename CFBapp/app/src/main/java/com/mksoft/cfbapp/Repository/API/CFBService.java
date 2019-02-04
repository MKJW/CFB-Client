package com.mksoft.cfbapp.Repository.API;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;


import com.mksoft.cfbapp.DataType.FunctionArray;
import com.mksoft.cfbapp.MainActivity;
import com.mksoft.cfbapp.Repository.API.WebService;
import com.mksoft.cfbapp.ViewAllFunction.FunctionArrayAdapter;

import org.mozilla.javascript.tools.jsc.Main;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CFBService {
    private WebService webService;


    @Inject
    public CFBService(WebService webService){
        this.webService = webService;

    }
    public void getAllFunction(final Context context, final MainActivity activity, final RecyclerView recyclerView) {
        Call<ArrayList<FunctionArray>> call = webService.getAllFunction(activity.getAccess_token());
        
        call.enqueue(new Callback<ArrayList<FunctionArray>>(){

            @Override
            public void onResponse(Call<ArrayList<FunctionArray>> call, Response<ArrayList<FunctionArray>> response) {
                if(response.isSuccessful() ==true && response.body() != null) {
                    FunctionArrayAdapter functionArrayAdapter = new FunctionArrayAdapter(context, response.body(), activity);
                    recyclerView.setAdapter(functionArrayAdapter);

                }
            }

            @Override
            public void onFailure(Call<ArrayList<FunctionArray>> call, Throwable t) {
                Toast.makeText(context.getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                Log.d("testResult", t.getLocalizedMessage());
            }
        });
    }

    public void postFunction(FunctionArray functionArray, final MainActivity mainActivity, final EditText hashTagOfEquationEditText, final EditText nameOfEquationEditText){
        Call<String> call = webService.postFunction(mainActivity.getAccess_token(), functionArray);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()==true && response.body() != null){
                    if(response.body() == "false"){

                        Toast.makeText(mainActivity, "이미 등록된 이름입니다.", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(mainActivity, "저장성공", Toast.LENGTH_SHORT).show();
                        hashTagOfEquationEditText.setText("");
                        nameOfEquationEditText.setText("");
                        mainActivity.onFragmentChange(1, null);//메인버튼페이지로 돌아감

                    }
                }

            }//중복 확인 결과 받아서 처리해주자...

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("server test", t.toString());
                Toast.makeText(mainActivity.getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }

        });
    }


}
